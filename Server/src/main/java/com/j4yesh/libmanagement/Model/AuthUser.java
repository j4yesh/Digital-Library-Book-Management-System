package com.j4yesh.libmanagement.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Data
@Document(collection = "users")
public class AuthUser {
    @Id
    private String username;
    private String password;
//    private String role;


    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }
//    public void setRole(String role){
//        this.role=role;
//    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

//    public String getRole(String role){
//        return this.role;
//    }
}
