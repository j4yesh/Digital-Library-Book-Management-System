package com.j4yesh.libmanagement.Repository;

import com.j4yesh.libmanagement.Model.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepo extends MongoRepository<AuthUser, String> {
    Optional<AuthUser> findByUsername(String username);
}
