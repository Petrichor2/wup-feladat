package com.wup.felveteli.library.controller;

import com.wup.felveteli.library.dto.BookDto;
import com.wup.felveteli.library.model.BookModel;
import com.wup.felveteli.library.service.LibraryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api("Library Controller")
@RequiredArgsConstructor
@Slf4j
public class LibraryController {

    private final LibraryService libraryService;

    @PutMapping("/api/add")
    @ApiOperation("Add new book to library")
    public void addBook(@RequestBody BookModel book) {
        libraryService.addBook(book);
    }

    @GetMapping("/api/find")
    @ApiOperation("Find books")
    public List<BookDto> findBooks(@RequestParam(required = false) String author, @RequestParam(required = false) String title, @RequestParam(required = false) String publisher, @RequestParam(required = false) Date publishedAt) {
        return libraryService.findBook(title, author, publisher, publishedAt);
    }
}
