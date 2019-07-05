package ru.avalon.java.blog.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import ru.avalon.java.blog.entities.Publication;
import ru.avalon.java.blog.entities.User;

@Stateless
public class PublicationService {

    @PersistenceContext(unitName = "Blog-PU")
    EntityManager em;

    public void create(Publication publication) {
        em.persist(publication);
        em.flush();
    }

    public void update(Publication publication){
        em.getTransaction().begin();
        em.merge(publication);
        em.getTransaction().commit();
    }
    
    public void remove(Publication publication){
        em.getTransaction().begin();
        em.remove(publication);
        em.getTransaction().commit();
    }
    
    public List<Publication> findByAuthor(User author) {
        try {
            return em.createNamedQuery("find-publications-by-user", Publication.class)
                    .setParameter("user", author)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Publication> getAllPublications() {
        try {
            return em.createNamedQuery("list-publication", Publication.class).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Publication getPublication(long id) {
        try {
            return em.createNamedQuery("find-publication-by-id", Publication.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    
}
