package org.example.repositories.abstract_repositories;

import org.example.models.Author;

import javax.persistence.EntityManager;
import java.util.List;

public interface Repository<T> {
    void create(T t);
    T findById(int id);
    List<T> findByName(String name);

}
