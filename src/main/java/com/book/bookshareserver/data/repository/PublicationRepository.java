package com.book.bookshareserver.data.repository;

import com.book.bookshareserver.data.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> getPublicationsByCategoryId(Long categoryId);

    List<Publication> getPublicationsByUserId(Long userId);

    List<Publication> getPublicationsByCityId(Long cityId);

    List<Publication> getPublicationsByCityAndCategory(Long cityId, Long categoryId);

    @Query(value = "SELECT DISTINCT * FROM publication INNER JOIN favorite ON publication.id = favorite.publication_id WHERE favorite.user_id = :userId", nativeQuery = true)
    List<Publication> getPublicationsByChoosenAsFavoriteByAndUserId(Long userId);

    @Query(value = "SELECT p FROM Publication p WHERE upper(p.name) LIKE CONCAT('%', upper(:name), '%') ")
    List<Publication> getPublicationsByName(@Param("name") String name);

}
