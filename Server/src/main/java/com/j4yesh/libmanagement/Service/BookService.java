package com.j4yesh.libmanagement.Service;

import com.j4yesh.libmanagement.Model.Book;
import com.j4yesh.libmanagement.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepo bookrepo;

    public Book addBook(Book book) {
        return bookrepo.save(book);
    }

    public List<Book> getAllBooks(String uploaderId) {
        return bookrepo.findByUploaderId(uploaderId);
    }

    public Optional<Book> getBookById(String id) {
        return bookrepo.findById(id);
    }

    public List<Book> findByTitle(String title) {
        return bookrepo.findByTitle(title);
    }

    public Book updateBook(String id, Book updatedBook) {
        Optional<Book> optionalBook = bookrepo.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setGenre(updatedBook.getGenre());
            book.setAvailabilityStatus(updatedBook.getAvailabilityStatus());

            return bookrepo.save(book);
        }

        return null;
    }


    public boolean deleteBook(String id) {
        if (bookrepo.existsById(id)) {
            bookrepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    public List<Book> findByUploaderIdAndTitle(String username, String title) {
        return bookrepo.findByUploaderIdAndTitle(username,title);
    }
}
