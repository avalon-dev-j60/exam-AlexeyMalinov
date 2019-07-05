package ru.avalon.java.blog.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import ru.avalon.java.blog.entities.Publication;
import ru.avalon.java.blog.entities.User;
import ru.avalon.java.blog.exceptions.ValidationException;
import static ru.avalon.java.blog.helpers.Validation.*;

@Stateless
public class ProjectService {

    @EJB
    UserService userService;

    @EJB
    PublicationService publicationService;

    @Inject
    HttpSession session;

    public void create(String title, String content) throws ValidationException {
        requireNonNull(title, "Title is required");
        requireNonNull(content, "Content is required");
        String email = (String) session.getAttribute("user");
        User author = userService.findByEmail(email);
        Publication publication = new Publication(title, content.getBytes(), author);
        publicationService.create(publication);
    }

    public List<Publication> getAllPublications() {
        return publicationService.getAllPublications();
    }

    public void update(String title, String content, String id) throws ValidationException {
        requireNonNull(title, "Title is required");
        requireNonNull(content, "Content is required");
        requireNonNull(id, "Id is required");
        Publication publication = publicationService.getPublication(Long.getLong(id));
        requireNonNull(publication, "Publication not found");
        publication.setTitle(title);
        publication.setContent(content.getBytes());
        publicationService.update(publication);
    }
    
    public void remove(String id) throws ValidationException{
        requireNonNull(id, "Id is required");
        Publication publication = publicationService.getPublication(Long.getLong(id));
        requireNonNull(publication, "Publication not found");
        publicationService.remove(publication);
    }

}
