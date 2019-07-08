package ru.avalon.java.blog.services;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import ru.avalon.java.blog.entities.Publication;

@Stateless
public class PublicationService {

    @PersistenceContext(unitName = "Blog-PU")
    EntityManager em;

    public void create(Publication publication) {
        em.persist(publication);
    }

    public void update(Publication publcation) {
        em.merge(publcation);
    }

    public void delete(Publication publication) {
        em.remove(publication);
    }

    public Publication find(long id) {
        try {
            return em.createNamedQuery("find-publication-by-id", Publication.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Publication> list() {
        try {
            return em.createNamedQuery("list-publication", Publication.class)
                    .getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }
}
