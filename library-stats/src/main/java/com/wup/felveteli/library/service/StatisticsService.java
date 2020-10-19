package com.wup.felveteli.library.service;

import com.wup.felveteli.library.dto.AuthorCountsDto;
import com.wup.felveteli.library.dto.BookDto;
import com.wup.felveteli.library.dto.PublisherCountsDto;
import com.wup.felveteli.library.persistance.Book;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
    List<AuthorCountsDto> getAuthorBookCounts();

    List<PublisherCountsDto> getPublisherCounts();

    int getAvgBookAge();

    List<BookDto> getLatestOldest();

    List<Map.Entry<String, Double>> getAvgEntryToLibraryByAuthor();

    List<Book> getThirdBookCounts();

    List<Map.Entry<String, Integer>> getBookCountsBeforeApplicationProperty();
}
