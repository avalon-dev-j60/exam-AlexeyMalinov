package ru.avalon.java.blog.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import ru.avalon.java.blog.entities.User;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "Blog-PU")
    EntityManager em;

    public void create(User user) {
        em.persist(user);
        em.flush(); //Чтобы сразу попал в БД
    }

    public User findByEmail(String email) {
        try {
            return em.createNamedQuery("find-user-by-email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
