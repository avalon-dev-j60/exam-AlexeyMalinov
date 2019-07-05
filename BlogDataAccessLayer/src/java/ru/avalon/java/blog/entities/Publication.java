package ru.avalon.java.blog.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
    @NamedQuery(
            name = "list-publication",
            query = "SELECT p FROM Publication AS p"
    )
    ,
    @NamedQuery(
            name = "find-publications-by-user",
            query = "SELECT p FROM Publication AS p WHERE p.author = :user"
    )
    ,
        @NamedQuery(
            name = "find-publication-by-id",
            query = "SELECT p FROM Publication AS p WHERE p.id = :id"
    )
})
@Entity
public class Publication implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false) //обязательное поле призаписи
    private String title;

    @Lob
    @Column(columnDefinition = "blob")
    private byte[] content;

    @ManyToOne //выбираем, что делать с связными объектами (cascade = CascadeType.ALL). В данном случаи не нужно ничего.
    @JoinColumn(nullable = false) //обязательное поле из внешней таблице
    private User author;

    @Temporal(TemporalType.TIMESTAMP) //Для того чтобы база сама указывала дату при создании записи
    private Date created;

    protected Publication() {
    }

    public Publication(String title, byte[] content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        created = new Date();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return new String(content);
    }

    public User getAuthor() {
        return author;
    }

    public Date getCreated() {
        return created;
    }

    public long getId() {
        return id;
    }
}
