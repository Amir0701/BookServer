package com.book.bookshareserver.data.repository;

import com.book.bookshareserver.data.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
