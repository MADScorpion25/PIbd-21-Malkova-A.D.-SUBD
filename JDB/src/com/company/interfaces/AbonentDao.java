package com.company.interfaces;

import java.util.Collection;
import java.util.Optional;

public interface AbonentDao<T, I> {
    Optional<T> get(String email);
    Collection<T> getAll();
    Optional<I> save(T t);
    void update(T t);
    void delete(String email);
    Collection<T> getByMask(String mask);
    Collection<T> getSorted();
}
