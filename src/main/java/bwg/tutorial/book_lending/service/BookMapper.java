package bwg.tutorial.book_lending.service;

import bwg.tutorial.book_lending.dto.BookDTO;
import bwg.tutorial.book_lending.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public static BookDTO toDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYearPublished(),
                book.isLentOut()
        );
    }
}
