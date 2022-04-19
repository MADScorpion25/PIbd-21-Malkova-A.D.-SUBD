package com.company.interfaces;

import java.sql.Date;
import java.util.Collection;
import java.util.Optional;

public interface PhoneDao<T, I> {
    Optional<T> get(String phone);
    Collection<T> getAll();
    Optional<I> save(T t);
    void update(T t);
    void delete(String phone);
    Collection<T> getByDate(Date filter);
}
