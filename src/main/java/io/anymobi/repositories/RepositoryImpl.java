package io.anymobi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class RepositoryImpl<T, ID extends Serializable> implements Repository<T, ID> {
    private final Class<T> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public RepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Optional<T> findById(ID var1) {
        Optional<T> entityOptional = Optional.ofNullable(entityManager.find(entityClass, var1));
        return entityOptional;
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        criteriaQuery.from(entityClass);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Page<T> findAll(Pageable var1) {
        return null;
    }

    @Override
    public <S extends T> S save(S var1) {

        return entityManager.merge(var1);
    }

    @Override
    public <S extends T> S saveAndFlush(S var1) {
        return null;
    }

    @Override
    public void deleteById(ID var1) {
        Optional<T> temp = findById(var1);
        if (temp.get() == null) {
            return;
        }
        delete(temp.get());
    }

    @Override
    public void delete(T var1) {
        entityManager.remove(var1);
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean existsById(ID var1) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public List<T> query(Specification<T> specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // use specification.getType() to create a Root<T> instance
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        // get predicate from specification
        Predicate predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
        // set predicate and execute query
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


}
