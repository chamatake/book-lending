package bwg.tutorial.book_lending.service;

import bwg.tutorial.book_lending.dto.BookCreateRequest;
import bwg.tutorial.book_lending.dto.BookDTO;
import bwg.tutorial.book_lending.entity.Book;
import bwg.tutorial.book_lending.exception.BookLendingStatusException;
import bwg.tutorial.book_lending.exception.BookNotFoundException;
import bwg.tutorial.book_lending.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO add(BookCreateRequest request) {
        Book bookAdded = this.bookRepository.save(new Book(
                null,
                request.getTitle(),
                request.getAuthor(),
                request.getYearPublished(),
                false)
        );
        return BookMapper.toDTO(bookAdded);
    }

    public BookDTO findById(String id) {
        Book existingBook = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Unable to find a book with id " + id));
        return BookMapper.toDTO(existingBook);
    }

    public BookDTO lendBook(String id) {
        Book existingBook = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("The book you requested to lend out could not be found: id = " + id));
        if(existingBook.isLentOut()) {
            throw new BookLendingStatusException("Book is already lent out. ID = " + id);
        }

        // update lending status
        existingBook.setLentOut(true);
        Book lentOutBook = this.bookRepository.save(existingBook);
        return BookMapper.toDTO(lentOutBook);
    }

    public BookDTO returnBook(String id) {
        Book existingBook = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("The book you requested to return could not be found: id = " + id));
        if(!existingBook.isLentOut()) {
            throw new BookLendingStatusException("Book is not currently lent out. ID = " + id);
        }

        // update lending status
        existingBook.setLentOut(false);
        Book returnedBook = this.bookRepository.save(existingBook);
        return BookMapper.toDTO(returnedBook);
    }

}
