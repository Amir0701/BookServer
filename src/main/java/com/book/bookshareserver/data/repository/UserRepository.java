package com.book.bookshareserver.data.repository;

import com.book.bookshareserver.data.model.Publication;
import com.book.bookshareserver.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "insert into favorite (user_id, publication_id) values (:userId, :publicationId)", nativeQuery = true)
    void markPublicationAsFavorite(@Param("userId") Long userId, @Param("publicationId") Long publicationId);
}
