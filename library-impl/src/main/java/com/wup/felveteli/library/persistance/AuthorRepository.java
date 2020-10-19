package com.wup.felveteli.library.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameAndAndBornAt(String name, Date bornAt);
}
