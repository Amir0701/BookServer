package com.book.bookshareserver.data.repository;

import com.book.bookshareserver.data.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
