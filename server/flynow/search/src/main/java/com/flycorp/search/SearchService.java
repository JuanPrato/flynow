package com.flycorp.search;

import com.flycorp.lib.fly.FlyClient;
import com.flycorp.lib.fly.ResponseGetFlies;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class SearchService {

    private RestTemplate restTemplate;

    public List<String> searchAll() throws Exception {
        ResponseGetFlies response = FlyClient.getFlies(restTemplate);
        if (response == null || response.getFlies() == null) {
            throw new Exception();
        }
        return response.getFlies().stream().map(f -> f.getTo().getLocation()).collect(Collectors.toList());
    }

    public List<String> searchByQuery(String query) {
        ResponseGetFlies response = FlyClient.getFlies(restTemplate);

        return response.getFlies()
                .stream()
                .filter(i -> i.getFrom().getLocation().contains(query))
                .map(i -> i.getFrom().getLocation())
                .collect(Collectors.toList());
    }

}
