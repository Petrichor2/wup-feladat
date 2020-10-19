package com.wup.felveteli.library.persistance;

import java.util.Date;
import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findBook(String title, String author, String publisher, Date publishedAt);
}
