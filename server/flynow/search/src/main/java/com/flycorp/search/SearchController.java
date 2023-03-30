package com.flycorp.search;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/search")
@AllArgsConstructor
public class SearchController {

    private SearchService searchService;

    @GetMapping(path = {"", "{query}"})
    public List<String> search(@PathVariable(required = false) String query) {
        try {
            if (query == null) {
                return searchService.searchAll();
            }
            return searchService.searchByQuery(query);
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
