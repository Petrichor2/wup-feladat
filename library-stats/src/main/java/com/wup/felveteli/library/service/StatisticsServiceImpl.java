package com.wup.felveteli.library.service;

import com.wup.felveteli.library.dto.AuthorCountsDto;
import com.wup.felveteli.library.dto.BookDto;
import com.wup.felveteli.library.dto.PublisherCountsDto;
import com.wup.felveteli.library.persistance.Author;
import com.wup.felveteli.library.persistance.Book;
import com.wup.felveteli.library.persistance.BookRepository;
import com.wup.felveteli.library.persistance.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final BookRepository bookRepository;

    @Value("${statistics.book.count.until}")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateUntil;

    @Override
    public List<AuthorCountsDto> getAuthorBookCounts() {
        return bookRepository.getAuthorBookCounts()
                .stream()
                .map(tuple -> new AuthorCountsDto(
                        ((Author)tuple.get(0)).getName(),
                        Integer.parseInt(tuple.get(2).toString()),
                        Integer.parseInt(tuple.get(1).toString())
                )).collect(Collectors.toList());
    }

    @Override
    public List<PublisherCountsDto> getPublisherCounts() {
        return bookRepository.getPublisherCounts()
                .stream()
                .map(tuple -> new PublisherCountsDto(
                        ((Publisher)tuple.get(0)).getName(),
                        Integer.parseInt(tuple.get(2).toString()),
                        Integer.parseInt(tuple.get(1).toString())
                )).collect(Collectors.toList());
    }

    @Override
    public int getAvgBookAge() {
        return bookRepository.getAvgBookAge();
    }

    @Override
    public List<BookDto> getLatestOldest() {
        return bookRepository.findLatestOldest()
                .stream()
                .map(BookDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Map.Entry<String, Double>> getAvgEntryToLibraryByAuthor() {
        return bookRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Book::getAuthor))
                .entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(
                        entry.getKey().getName(),
                        entry.getValue()
                                .stream()
                                .mapToLong(book ->
                                        ChronoUnit.DAYS.between(
                                                book.getReleaseDate().toInstant(),
                                                book.getDateOfEntry().toInstant()
                                        )
                                ).average().orElse(0)
                        )
                ).collect(Collectors.toList());
    }

    @Override
    public List<Book> getThirdBookCounts() {
        Map<Author, List<Book>> authorBooks = bookRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
        List<Book> thirdBooks = new LinkedList<>();
        authorBooks.forEach((key, value) -> {
            if (value.size() < 3) {
                return;
            }
            value.sort(Comparator.comparing(Book::getReleaseDate));
            thirdBooks.add(value.get(2));
        });
        return thirdBooks;
    }

    @Override
    public List<Map.Entry<String, Integer>> getBookCountsBeforeApplicationProperty() {
        return bookRepository.booksCountUntil(dateUntil).stream().map(tuple -> new AbstractMap.SimpleEntry<>(
                ((Author)tuple.get(0)).getName(),
                Integer.parseInt(tuple.get(1).toString())
        )).collect(Collectors.toList());
    }


}
