package ru.avalon.java.blog.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
    @NamedQuery(
            name = "list-publication",
            query = "SELECT p FROM Publication AS p"
    ),
    @NamedQuery(
            name = "find-publications-by-user",
            query = "SELECT p FROM Publication AS p WHERE p.authot = :user"
    )
})
@Entity
public class Publication implements Serializable{
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(nullable = false) //обязательное поле призаписи
    private String title;
    
    private String content;
    
    @ManyToOne //выбираем, что делать с связными объектами (cascade = CascadeType.ALL). В данном случаи не нужно ничего.
    @JoinColumn(nullable = false) //обязательное поле из внешней таблице
    private User authot;
    
    @Temporal(TemporalType.TIMESTAMP) //Для того чтобы база сама указывала дату при создании записи
    private Date created;
  
    protected Publication(){}

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
}
