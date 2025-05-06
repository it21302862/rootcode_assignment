package com.assignment.assignment.service;

import com.assignment.assignment.DTO.RequestDTO.BookRequestDTO;
import com.assignment.assignment.DTO.ResponseDTO.BookResponseDTO;
import com.assignment.assignment.entity.Book;
import com.assignment.assignment.exception.BookNotFoundException;
import com.assignment.assignment.mapper.BookMapper;
import com.assignment.assignment.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) {
        Book book = new Book();
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setTitle(bookRequestDTO.getTitle());
        book.setAvailableCopies(bookRequestDTO.getAvailableCopies());
        book.setPublishedYear(bookRequestDTO.getPublishedYear());

        bookRepository.save(book);
        logger.info("book successfully saved");
        return BookMapper.bookToBookResponseDto(book);
    }


    @Override
    public BookResponseDTO getBookById(Integer bookId) {
        return BookMapper.bookToBookResponseDto(getBook(bookId));
    }

    @Override
    public Book getBook(Integer bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> {
            logger.warn("Book with ID {} not found", bookId);
            return new BookNotFoundException(
                    "book with id: " + bookId + " could not be found");
        });
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = StreamSupport
                .stream(bookRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return BookMapper.bookToBookResponseDtos(books);
    }

    @Override
    public BookResponseDTO deleteBook(Integer bookId) {
        Book book = getBook(bookId);
        bookRepository.delete(book);
        logger.info("book successfully deleted for the book Id{}:", bookId);
        return BookMapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDTO updateBook(Integer bookId, BookRequestDTO bookRequestDTO) {
        Book book = getBook(bookId);
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setTitle(bookRequestDTO.getTitle());
        book.setAvailableCopies(bookRequestDTO.getAvailableCopies());
        book.setPublishedYear(bookRequestDTO.getPublishedYear());

        bookRepository.save(book);
        logger.info("book successfully updated");
        return BookMapper.bookToBookResponseDto(book);
    }
}
