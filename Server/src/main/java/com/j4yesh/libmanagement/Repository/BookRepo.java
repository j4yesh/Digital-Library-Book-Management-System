package com.j4yesh.libmanagement.Repository;

import com.j4yesh.libmanagement.Model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends MongoRepository<Book, String> {
    List<Book> findByTitle(String title);
    List<Book> findByUploaderId(String uploaderId);
    List<Book> findByUploaderIdAndTitle(String uploaderId, String title);
}
