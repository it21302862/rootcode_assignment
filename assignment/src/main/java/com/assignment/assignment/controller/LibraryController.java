package com.assignment.assignment.controller;

import com.assignment.assignment.DTO.RequestDTO.BorrowRequestDTO;
import com.assignment.assignment.DTO.ResponseDTO.BookResponseDTO;
import com.assignment.assignment.DTO.ResponseDTO.BorrowResponseDTO;
import com.assignment.assignment.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books")
    public List<BookResponseDTO> browseBooks() {
        return libraryService.getAvailableBooks();
    }

    @GetMapping("/books/search")
    public List<BookResponseDTO> searchBooks(@RequestParam(required = false) String author,
                                          @RequestParam(required = false) Integer year) {
        return libraryService.searchBooks(author, year);
    }

    @PostMapping("/borrow")
    public ResponseEntity<BorrowResponseDTO> borrow(@RequestBody BorrowRequestDTO req, Principal principal) {
        return ResponseEntity.ok(libraryService.borrowBook(principal.getName(), req.getBookId()));
    }

    @PostMapping("/return")
    public ResponseEntity<BorrowResponseDTO> returnBook(@RequestBody BorrowRequestDTO req, Principal principal) {
        return ResponseEntity.ok(libraryService.returnBook(principal.getName(), req.getBookId()));
    }

    @GetMapping("/history")
    public List<String> history(Principal principal) {
        return libraryService.viewHistory(principal.getName());
    }
}
