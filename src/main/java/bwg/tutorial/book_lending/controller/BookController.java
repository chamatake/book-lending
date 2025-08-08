package bwg.tutorial.book_lending.controller;

import bwg.tutorial.book_lending.dto.BookCreateRequest;
import bwg.tutorial.book_lending.dto.BookDTO;
import bwg.tutorial.book_lending.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public BookDTO add(BookCreateRequest request) {
        return this.bookService.add(request);
    }

    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable String id) {
        return this.bookService.findById(id);
    }

    @PutMapping("/{id}/lend")
    public BookDTO lendById(@PathVariable String id) {
        return this.bookService.lendBook(id);
    }

    @PutMapping("{id}/return")
    public BookDTO returnById(@PathVariable String id) {
        return this.bookService.returnBook(id);
    }
}
