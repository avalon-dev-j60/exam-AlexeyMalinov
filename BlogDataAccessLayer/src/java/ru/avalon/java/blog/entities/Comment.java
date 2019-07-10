package ru.avalon.java.blog.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@NamedQueries({
    @NamedQuery(
            name = "find-comment-by-publication",
            query = "SELECT item FROM Comment AS item WHERE item.publication = :publication"
    )
    ,
    @NamedQuery(
            name = "find-comment-by-id",
            query = "SELECT item FROM Comment AS item WHERE item.id = :id"
    )
})
@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, columnDefinition = "long varchar")
    private String text;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Publication publication;

    @JoinColumn(nullable = false)
    private User author;

    private Date created;

    protected Comment() {
    }

    public Comment(String text, Publication publication, User user) {
        this.text = text;
        this.publication = publication;
        this.author = user;
        this.created = new Date();
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Publication getPublication() {
        return publication;
    }

    public User getUser() {
        return author;
    }

    public Date getCreated() {
        return created;
    }

    public User getAuthor() {
        return author;
    }

}
