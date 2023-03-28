package com.flycorp.search;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    List<String> results = List.of(new String[]{"1", "2"});

    public List<String> searchAll() {
        return results;
    }

    public List<String> searchByQuery(String query) {
        return results
                .stream()
                .filter(i -> i.contains(query))
                .collect(Collectors.toList());
    }

}
