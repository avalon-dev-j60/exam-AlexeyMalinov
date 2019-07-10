package ru.avalon.java.blog.services;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import ru.avalon.java.blog.entities.Comment;
import ru.avalon.java.blog.entities.Publication;

@Stateless
public class CommentService {

    @PersistenceContext(unitName = "Blog-PU")
    EntityManager em;

    public void create(Comment comment) {
        em.persist(comment);
    }

    public void update(Comment comment) {
        em.merge(comment);
    }

    public void delete(Comment comment) {
        em.remove(em.merge(comment));
    }

    public List<Comment> findByPublication(Publication publication) {
        try {
            return em.createNamedQuery("find-comment-by-publication", Comment.class)
                    .setParameter("publication", publication)
                    .getResultList();
        } catch (NoResultException e) {
            return Collections.EMPTY_LIST;
        }
    }
    
    public Comment find(long id){
        try{
            return em.createNamedQuery("find-comment-by-id", Comment.class)
                    .setParameter("id", id)
                    .getSingleResult()
                    ;
        } catch (NoResultException e){
            return null;
        }
    }

}
