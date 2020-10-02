package edu.escuelaing.arep.secureapp.components.persistence;

public class User {

    private String username,password;
    public static final String USERNAME_STATIC = "orlando";
    public static final String PASSWORD_STATIC = "655e786674d9d3e77bc05ed1de37b4b6bc89f788829f9f3c679e7687b410c89b";

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
