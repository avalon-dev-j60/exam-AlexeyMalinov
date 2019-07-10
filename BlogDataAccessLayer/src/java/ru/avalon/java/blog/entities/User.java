package ru.avalon.java.blog.entities;

import java.io.*;
import javax.persistence.*;

// Именованный запрос
@NamedQueries({
    @NamedQuery(
            name = "find-user-by-email",
            query = "SELECT user FROM Users AS user WHERE user.email = :email"
    )
})

@Entity(name = "Users")
public class User implements Serializable{
    
    @Id
    private String email;
    
    private String password;

    protected User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
