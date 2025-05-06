package com.assignment.assignment.service;

import com.assignment.assignment.DTO.ResponseDTO.BorrowResponseDTO;
import com.assignment.assignment.entity.Book;
import com.assignment.assignment.entity.BorrowRecord;
import com.assignment.assignment.entity.User;
import com.assignment.assignment.exception.BookNotAvailableException;
import com.assignment.assignment.repository.BookRepository;
import com.assignment.assignment.repository.BorrowRecordRepository;
import com.assignment.assignment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibraryServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowRecordRepository recordRepository;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    private User testUser;
    private Book testBook;
    private BorrowRecord testRecord;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User();
        testUser.setId(1);
        testUser.setEmail("test@example.com");

        testBook = new Book();
        testBook.setId(1);
        testBook.setTitle("Sample Book");
        testBook.setAuthor("Author");
        testBook.setPublishedYear(2020);
        testBook.setAvailableCopies(3);

        testRecord = new BorrowRecord();
        testRecord.setId(1);
        testRecord.setUser(testUser);
        testRecord.setBook(testBook);
        testRecord.setBorrowedAt(LocalDateTime.now());
    }

    @Test
    void borrowBook_Success() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(testUser));
        when(bookRepository.findById(1)).thenReturn(Optional.of(testBook));
        when(recordRepository.save(any(BorrowRecord.class))).thenReturn(testRecord);

        BorrowResponseDTO response = libraryService.borrowBook("test@example.com", 1);

        assertNotNull(response);
        assertEquals("Sample Book", response.getBookTitle());
    }

    @Test
    void borrowBook_Failure_WhenNoCopies() {
        testBook.setAvailableCopies(0);
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(testUser));
        when(bookRepository.findById(1)).thenReturn(Optional.of(testBook));

        assertThrows(BookNotAvailableException.class, () -> libraryService.borrowBook("test@example.com", 1));
    }

    @Test
    void viewHistory_ShouldReturnHistory() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(testUser));
        when(recordRepository.findByUserId(1)).thenReturn(List.of(testRecord));

        List<String> history = libraryService.viewHistory("test@example.com");

        assertFalse(history.isEmpty());
        assertTrue(history.get(0).contains("Sample Book"));
    }
}
