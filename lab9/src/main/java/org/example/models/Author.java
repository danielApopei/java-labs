package org.example.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="authors")
@NamedQueries({
        @NamedQuery(name="Author.findAll",
        query="select e from Author e order by e.name")
})
public class Author implements Serializable {
    String name;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="id")
    @Column(name="id")
    private Integer id;

    public Author(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public Author() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
