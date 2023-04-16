package com.book.bookshareserver.domain.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.book.bookshareserver.data.model.Image;
import com.book.bookshareserver.data.model.Publication;
import com.book.bookshareserver.data.repository.ImageRepository;
import com.book.bookshareserver.data.repository.PublicationRepository;
import com.book.bookshareserver.representation.dto.ImageDto;
import com.book.bookshareserver.representation.dto.converter.ImageDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageServiceImpl implements ImageService{
    private final String root = "E:\\ShareBook\\";
    private final ImageRepository imageRepository;
    private final PublicationRepository publicationRepository;
    private final ImageDtoConverter imageDtoConverter;
    private final AmazonS3 amazonS3;

    @Value("${bucketName}")
    private String bucketName;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository,
                            PublicationRepository publicationRepository,
                            ImageDtoConverter imageDtoConverter,
                            AmazonS3 amazonS3){
        this.imageRepository = imageRepository;
        this.publicationRepository = publicationRepository;
        this.imageDtoConverter = imageDtoConverter;
        this.amazonS3 = amazonS3;
    }


    @Override
    public List<Image> addImage(MultipartFile[] multipartFiles,
                                Publication publication) {
        List<Image> images = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles){
            Path path = Paths.get(root);
            File file = new File(path.resolve(LocalDateTime.now() + "_" + multipartFile.getOriginalFilename()).toUri());
            file.mkdirs();

            Image image = new Image();
            image.setPath(file.getAbsolutePath());
            image.setPublication(publication);
            imageRepository.saveAndFlush(image);
            images.add(image);
        }
        return images;
    }

    @Override
    public void addImage(MultipartFile[] multipartFiles,
                                   Long publicationId) {
            //List<Image> images = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles){
                Path path = Paths.get(root);
                File file = new File(path.resolve(LocalDateTime.now().getYear()
                        + "-" + LocalDateTime.now().getMonthValue()
                        + "-" + LocalDateTime.now().getDayOfMonth()
                        + "-" + LocalDateTime.now().getMinute()
                        + "_" + multipartFile.getOriginalFilename()).toUri());
                file.mkdirs();
                try {
                    multipartFile.transferTo(file.getAbsoluteFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Image image = new Image();
                image.setPath(file.getAbsolutePath());
                image.setPublication(publicationRepository.findById(publicationId).get());
                imageRepository.saveAndFlush(image);
                //images.add(image);
            }
//            return images.stream()
//                    .map(imageDtoConverter::toImageDto)
//                    .collect(Collectors.toList());
    }

    @Override
    public void addImages(MultipartFile[] multipartFiles, Long publicationId) {
        for (MultipartFile file: multipartFiles){
            File convertedFile = convertMultipartFileToFile(file);
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            amazonS3.putObject(bucketName, fileName, convertedFile);
            Image image = new Image();
            image.setPath(fileName);
            image.setPublication(publicationRepository.findById(publicationId).get());
            imageRepository.saveAndFlush(image);
            convertedFile.delete();
        }
    }

    @Override
    public byte[] downloadImage(String fileName) {
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        return null;
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile){
        File file = new File(multipartFile.getOriginalFilename());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
