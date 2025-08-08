package bwg.tutorial.book_lending.repository;

import bwg.tutorial.book_lending.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
    Book findByTitleAuthorYear(String title, String author, Integer yearPublished);
}
