package com.example.course_registration.common;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

public interface CoreEntityService<E>
{
    void saveAll(Iterable<E> entities);

    E update(E entity);

    E create(E entity);

    void delete(E entity);

    default void deleteById(Long id) {
        delete(getById(id));
    }

    Optional<E> findById(Long id);

    E getById(Long id);

    List<E> findAll();

    long count();

    boolean isNew(E entity);

    default E saveOrUpdate(E entity) {
        if (isNew(entity)) {
            return create(entity);
        } else {
            return update(entity);
        }
    }

    // 코드 기반 조회
    Optional<E> findByCode(String code);

    default E findByCodeOrThrow(String code)
    {
        return findByCode(code)
            .orElseThrow(() -> new EntityNotFoundException("Entity with code " + code + " not found"));
    }
}
