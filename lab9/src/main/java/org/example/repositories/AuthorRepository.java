package org.example.repositories;

import org.example.models.Author;
import org.example.utils.JPAEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AuthorRepository {
    private EntityManagerFactory entityManagerFactory;

    public AuthorRepository() {
        this.entityManagerFactory = JPAEntityManagerFactory.getInstance().getEntityManagerFactory();
    }

    public void create(Author author) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
        em.close();
    }

    public Author findById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Author author = em.find(Author.class, id);
        em.close();
        return author;
    }

    public List<Author> findByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        String query = "SELECT a FROM Author a WHERE a.name = :name";
        List<Author> authors = em.createQuery(query, Author.class)
                .setParameter("name", name)
                .getResultList();
        em.close();
        return authors;
    }

}
