package com.assignment.assignment.controller;

import com.assignment.assignment.DTO.RequestDTO.BookRequestDTO;
import com.assignment.assignment.DTO.ResponseDTO.BookResponseDTO;
import com.assignment.assignment.exception.BadRequestException;
import com.assignment.assignment.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponseDTO> addBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        try {
            logger.info("Received request to save books", bookRequestDTO);
            BookResponseDTO bookResponseDto = bookService.addBook(bookRequestDTO);
            return ResponseEntity.ok(bookResponseDto);
        } catch (Exception e) {
            throw new BadRequestException("Failed to add student: " + e.getMessage());
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable Integer bookId) {
        logger.info("Received request to fetch book with ID: {}", bookId);
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        logger.info("Received request to fetch books");
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<BookResponseDTO> deleteBook(@PathVariable Integer bookId) {
        logger.info("Received request to delete student ID: {} ", bookId);
        BookResponseDTO deletedStudent = bookService.deleteBook(bookId);
        return ResponseEntity.ok(deletedStudent);
    }

    @PutMapping("/edit/{bookId}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Integer bookId,
                                                      @Valid @RequestBody BookRequestDTO bookRequestDTO) {
        try {
            logger.info("Received request to update books", bookRequestDTO, "book with ID: {}", bookId);
            BookResponseDTO bookResponseDto = bookService.updateBook(bookId,bookRequestDTO);
            return ResponseEntity.ok(bookResponseDto);
        }catch (Exception e){
            throw new BadRequestException("Failed to add student: " + e.getMessage());
        }
    }






}
