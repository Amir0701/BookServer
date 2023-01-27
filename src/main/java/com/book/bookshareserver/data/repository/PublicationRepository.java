package com.book.bookshareserver.data.repository;

import com.book.bookshareserver.data.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> getPublicationsByCategoryId(Long categoryId);

    List<Publication> getPublicationsByUserId(Long userId);

    List<Publication> getPublicationsByCityId(Long cityId);
}
