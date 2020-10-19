package com.wup.felveteli.library.controller;

import com.wup.felveteli.library.dto.AuthorCountsDto;
import com.wup.felveteli.library.dto.BookDto;
import com.wup.felveteli.library.dto.PublisherCountsDto;
import com.wup.felveteli.library.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@Api("Controller with on the fly statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @PostMapping("/update-all")
    @ApiOperation("Update stored statistics")
    public String updateStatistics() {
        // Időhiány miatt nem implementált
        throw new NotImplementedException();
    }

    @GetMapping("/author-counts")
    @ApiOperation("Gets authors with count of titles available in the library, and with total number")
    public List<AuthorCountsDto> getAuthorBookCounts() {
        return statisticsService.getAuthorBookCounts();
    }

    @GetMapping("/publisher-counts")
    @ApiOperation("Gets publishers with count of titles available in the library, and with total number")
    public List<PublisherCountsDto> getPublisherCounts() {
        return statisticsService.getPublisherCounts();
    }

    @GetMapping("/average-age")
    @ApiOperation("Gets the average age of available titles (not counting in ones with multiple issues)")
    public int getBootAverageAge() {
        return statisticsService.getAvgBookAge();
    }

    @GetMapping("/average-entry-to-library-by-author")
    @ApiOperation("Gets the average length of days after a book is entered to the library")
    public List<Map.Entry<String, Double>> getAvgEntryToLibraryByAuthor() {
        return statisticsService.getAvgEntryToLibraryByAuthor();
    }

    @GetMapping("/third-books-counts")
    @ApiOperation("Gets how many issues are present for the third book for each Author")
    public List<BookDto> getThirdBookCounts() {
        return statisticsService.getThirdBookCounts().stream().map(BookDto::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/book-count-until")
    @ApiOperation("Gets the number of books for all Authors released after the property set by environment.properties")
    public List<Map.Entry<String, Integer>> getBooksUntilProperty() {
        return statisticsService.getBookCountsBeforeApplicationProperty();
    }
}
