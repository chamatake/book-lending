package bwg.tutorial.book_lending.dto;

public record BookDTO (
        String id,
        String title,
        String author,
        Integer yearPublished,
        boolean isLentOut
) {}
