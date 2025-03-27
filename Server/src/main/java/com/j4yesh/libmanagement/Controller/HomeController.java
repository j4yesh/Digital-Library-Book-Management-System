package com.j4yesh.libmanagement.Controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name="Homepage APIs")
public class HomeController {
    @GetMapping("/greet")
    public ResponseEntity<?> greet(){
        return ResponseEntity.ok("Hello from LibManagement server. ");
    }
}
