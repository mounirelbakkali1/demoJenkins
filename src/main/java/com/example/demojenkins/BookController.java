package com.example.demojenkins;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private List<Book> books ;

    public BookController() {
        books = List.of(
          new Book("book 1","ez-76890"),
          new Book("book 2","ez-76891"),
          new Book("book 3","ez-76892")
        );
    }

    @GetMapping
    public List<Book> getBooks(){
        return books;
    }
}
