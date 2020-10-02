package edu.escuelaing.arep.secureapp.components.persistence;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class ImplPersistence {
    private User user;

    public ImplPersistence(){
        user = new User(User.USERNAME_STATIC,User.PASSWORD_STATIC);
    }

    public boolean login(String username, String password){
        return username.equals(user.getUsername()) && Hashing.sha256().
                hashString(password, StandardCharsets.UTF_8).toString().equals(user.getPassword());
    }

}
