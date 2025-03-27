package com.j4yesh.libmanagement.Controller;

import com.j4yesh.libmanagement.Model.AuthUser;
import com.j4yesh.libmanagement.Model.Book;
import com.j4yesh.libmanagement.Service.AuthUserDetailService;
import com.j4yesh.libmanagement.Service.BookService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name="Book APIs")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private  AuthUserDetailService authUserDetailService;

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            if (book.getAuthor() == null || book.getAuthor().isEmpty() ||
                    book.getTitle() == null || book.getTitle().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("author and title are required.");
            }
//            String userId = authUserDetailService.getUsername();
//            book.setUploaderId(userId);
            return ResponseEntity.ok(bookService.addBook(book));
        } catch (Exception e) {
            System.out.println("error adding book: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to add.");
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks() {
        try {
//            String username=authUserDetailService.getUsername();
//            List<Book> books = bookService.getAllBooks(username);
            List<Book> books=bookService.getAllBooks();
            if (books.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no book found.");
            }
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            System.out.println("error retrieving books: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to retrieve books.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id) {
        try {
            Optional<Book> book = bookService.getBookById(id);

            if (book.isPresent()) {
//                String username=authUserDetailService.getUsername();
//                if(!username.equals(book.get().getUploaderId())){
//                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
//                }
                return ResponseEntity.ok(book.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("book not found.");
            }
        } catch (Exception e) {
            System.out.println("error retrieving book: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to retrieve book.");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBookByTitle(@RequestParam String title) {
        try {
//            String username=authUserDetailService.getUsername();
//            List<Book> books = bookService.findByUploaderIdAndTitle(username,title);
            List<Book> books = bookService.findByTitle(title);
            if (books.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no books found.");
            }
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            System.out.println("error searching: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to search.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable String id, @RequestBody Book book) {
        try {
//            String username=authUserDetailService.getUsername();
//            if(!username.equals(bookService.getBookById(id).get().getUploaderId())){
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
//            }
            Book updatedBook = bookService.updateBook(id, book);
            if (updatedBook != null) {
                return ResponseEntity.ok(updatedBook);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("book not found.");
            }
        } catch (Exception e) {
            System.out.println("error updating : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to update.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        try {
//            String username=authUserDetailService.getUsername();
//            if(!username.equals(bookService.getBookById(id).get().getUploaderId())){
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
//            }
            boolean isDeleted = bookService.deleteBook(id);
            if (isDeleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("book not found.");
            }
        } catch (Exception e) {
            System.out.println("error deleting book: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to delete.");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        System.out.println("error: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong.");
    }
}

