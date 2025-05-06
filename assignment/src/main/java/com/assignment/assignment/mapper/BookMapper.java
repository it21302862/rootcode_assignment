package com.assignment.assignment.mapper;

import com.assignment.assignment.DTO.ResponseDTO.BookResponseDTO;
import com.assignment.assignment.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {

    public static BookResponseDTO bookToBookResponseDto(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPublishedYear(book.getPublishedYear());
        dto.setAvailableCopies(book.getAvailableCopies());

        return dto;
    }

    public static List<BookResponseDTO> bookToBookResponseDtos(List<Book> books) {
        List<BookResponseDTO> bookResponseDtos = new ArrayList<>();
        for(Book book:books){
            bookResponseDtos.add(bookToBookResponseDto(book));
        }
        return bookResponseDtos;
    }
}
