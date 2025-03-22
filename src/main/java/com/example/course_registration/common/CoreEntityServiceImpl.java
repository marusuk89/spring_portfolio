package com.example.course_registration.common;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class CoreEntityServiceImpl<E extends BaseEntity> 
        implements CoreEntityService<E>
{
    private final JpaRepository<E, Long> repository;

    protected CoreEntityServiceImpl(JpaRepository<E, Long> repository) {
        this.repository = repository;
    }

    protected JpaRepository<E, Long> getRepository() {
        return repository;
    }

    @Override
    public void saveAll(Iterable<E> entities) {
        repository.saveAll(entities);
    }

    @Override
    public E update(E entity) {
        return repository.save(entity);
    }

    @Override
    public E create(E entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(E entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public E getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean isNew(E entity) {
        if (entity == null) {
            return false;
        }
        return entity.getId() == null;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Optional<E> findByCode(String code) {
        throw new UnsupportedOperationException("findByCode is not implemented. Override this method in a subclass if needed.");
    }

    @Override
    public E findByCodeOrThrow(String code) {
        return findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Entity with code " + code + " not found"));
    }
}
