package ru.avalon.java.blog.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@NamedQueries({
    @NamedQuery(
            name = "list-publication",
            query = "SELECT item FROM Publication AS item"
    )
    ,
    @NamedQuery(
            name = "find-publications-by-user",
            query = "SELECT item FROM Publication AS item WHERE item.authot = :user"
    )
    ,
    @NamedQuery(
            name = "find-publication-by-id",
            query = "SELECT item FROM Publication AS item WHERE item.id = :id"
    )
})
@Entity
public class Publication implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false) //обязательное поле призаписи
    private String title;

    @Column(columnDefinition = "long varchar") 
    private String content;

    @ManyToOne //выбираем, что делать с связными объектами (cascade = CascadeType.ALL). В данном случаи не нужно ничего.
    @JoinColumn(nullable = false) //обязательное поле из внешней таблице
    private User authot;

    @Temporal(TemporalType.TIMESTAMP) //Для того чтобы база сама указывала дату при создании записи
    private Date created;

    protected Publication() {
    }

    public Publication(String title, String content, User authot) {
        this.title = title;
        this.content = content;
        this.authot = authot;
        created = new Date();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getAuthot() {
        return authot;
    }

    public Date getCreated() {
        return created;
    }
    
    
}
