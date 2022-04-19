package com.company.interfaces;

import java.util.Collection;
import java.util.Optional;

public interface RateDao<T, I> {
    Optional<T> get(String name);
    Collection<T> getAll();
    Optional<I> save(T t);
    void update(T t);
    void delete(String name);
}
