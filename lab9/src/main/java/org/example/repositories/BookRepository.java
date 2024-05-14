package org.example.repositories;

import org.example.models.Book;
import org.example.repositories.abstract_repositories.Repository;
import org.example.utils.JPAEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class BookRepository implements Repository<Book> {
    private EntityManagerFactory entityManagerFactory;

    public BookRepository(){
        this.entityManagerFactory = JPAEntityManagerFactory.getInstance().getEntityManagerFactory();
    }

    public void create(Book book){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public Book findById(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Book book = entityManager.find(Book.class, id);
        entityManager.close();
        return book;
    }

    public List<Book> findByName(String name){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String query = "SELECT b FROM book b WHERE b.name = :name";
        List<Book> books = entityManager.createQuery(query, Book.class)
                .setParameter("name", name)
                .getResultList();
        entityManager.close();
        return books;
    }
}
