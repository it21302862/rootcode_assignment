package com.assignment.assignment.service;

import com.assignment.assignment.DTO.ResponseDTO.BookResponseDTO;
import com.assignment.assignment.DTO.ResponseDTO.BorrowResponseDTO;
import com.assignment.assignment.entity.Book;
import com.assignment.assignment.entity.BorrowRecord;
import com.assignment.assignment.entity.User;
import com.assignment.assignment.exception.BookNotAvailableException;
import com.assignment.assignment.mapper.BorrowBookMapper;
import com.assignment.assignment.repository.BookRepository;
import com.assignment.assignment.repository.BorrowRecordRepository;
import com.assignment.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
public class LibraryServiceImpl implements LibraryService{

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowRecordRepository recordRepository;

    @Autowired
    public LibraryServiceImpl(UserRepository userRepository, BookRepository bookRepository, BorrowRecordRepository recordRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.recordRepository = recordRepository;
    }


    @Override
    public List<BookResponseDTO> getAvailableBooks() {
        return bookRepository.findByAvailableCopiesGreaterThan(0)
            .stream().map(this::mapBook).toList();
    }

    @Override
    public List<BookResponseDTO> searchBooks(String author, Integer year) {
        Stream<Book> books = bookRepository.findByAvailableCopiesGreaterThan(0).stream();

        if (author != null && !author.trim().isEmpty()) {
            books = books.filter(b -> b.getAuthor().equalsIgnoreCase(author));
        }

        if (year != null) {
            books = books.filter(b -> b.getPublishedYear() == year);
        }

        return books.map(this::mapBook).toList();
    }


    @Override
    public BorrowResponseDTO borrowBook(String email, Integer bookId) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        if (book.getAvailableCopies() <= 0) throw new BookNotAvailableException("Book with ID " + bookId + " is not available");

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        BorrowRecord record = new BorrowRecord();
        record.setBook(book);
        record.setUser(user);
        record.setBorrowedAt(LocalDateTime.now());

        recordRepository.save(record);
        bookRepository.save(book);
        return BorrowBookMapper.toBorrowResponse(record, "Book borrowed successfully.");
    }

    @Override
    public BorrowResponseDTO returnBook(String email, Integer bookId) {
        User user = userRepository.findByEmail(email).orElseThrow();
        List<BorrowRecord> records = recordRepository.findByUserId(user.getId());

        BorrowRecord latest = records.stream()
                .filter(r -> r.getBook().getId().equals(bookId) && r.getReturnedAt() == null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No active borrow"));

        latest.setReturnedAt(LocalDateTime.now());
        latest.getBook().setAvailableCopies(latest.getBook().getAvailableCopies() + 1);
        recordRepository.save(latest);
        bookRepository.save(latest.getBook());

        return BorrowBookMapper.toBorrowResponse(latest, "Book returned successfully.");
    }


    @Override
    public List<String> viewHistory(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return recordRepository.findByUserId(user.getId())
                .stream().map(r -> r.getBook().getTitle() + " on " + r.getBorrowedAt()).toList();
    }

    private BookResponseDTO mapBook(Book b) {
        BookResponseDTO res = new BookResponseDTO();
        res.setId(b.getId());
        res.setTitle(b.getTitle());
        res.setAuthor(b.getAuthor());
        res.setPublishedYear(b.getPublishedYear());
        res.setAvailableCopies(b.getAvailableCopies());
        return res;
    }
}
