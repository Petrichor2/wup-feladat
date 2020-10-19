package com.wup.felveteli.library.service;

import com.wup.felveteli.library.dto.BookDto;
import com.wup.felveteli.library.model.BookModel;

import java.util.Date;
import java.util.List;

public interface LibraryService {

    void addBook(BookModel book);

    List<BookDto> findBook(String title, String author, String publisher, Date publishedAt);
}
