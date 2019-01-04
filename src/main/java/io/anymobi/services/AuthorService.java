package io.anymobi.services;

import io.anymobi.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);

    List<Author> findAll();

    boolean isExist(Author author);

    Author save(Author author);

    void deleteById(Long id);
}
