package io.anymobi.services;

import io.anymobi.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(Long id);

    List<Book> findAll();

    Page<Book> findAll(Pageable pageable);

    Book save(Book book);

    boolean isExist(Book book);

    void deleteById(Long id);
}
