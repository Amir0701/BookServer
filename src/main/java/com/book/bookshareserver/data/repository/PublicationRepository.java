package com.book.bookshareserver.data.repository;

import com.book.bookshareserver.data.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
