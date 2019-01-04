package io.anymobi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    Optional<T> findById(ID var1);

    List<T> findAll();

    Page<T> findAll(Pageable var1);

    <S extends T> S save(S var1);

    <S extends T> S saveAndFlush(S var1);

    void deleteById(ID var1);

    void delete(T var1);

    long count();

    boolean existsById(ID var1);

    void flush();

    List<T> query(Specification<T> specification);

}
