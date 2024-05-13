package org.example.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAEntityManagerFactory {
    private static final String PERSISTENCE_UNIT_NAME = "YourPersistenceUnit";
    private static JPAEntityManagerFactory instance;
    private EntityManagerFactory entityManagerFactory;

    private JPAEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static synchronized JPAEntityManagerFactory getInstance() {
        if (instance == null) {
            instance = new JPAEntityManagerFactory();
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }}
