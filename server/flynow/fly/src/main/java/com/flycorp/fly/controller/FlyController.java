package com.flycorp.fly.controller;

import com.flycorp.fly.dto.FlyDto;
import com.flycorp.fly.dto.RequestCreateFlyDto;
import com.flycorp.fly.service.FlyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/fly")
public record FlyController(FlyService flyService) {

    @GetMapping(value = { "/search", "/search/{query}" })
    public List<FlyDto> search(@PathVariable(required = false) String query) {
        log.info("search request with query {}", query);
        return flyService.getFliesByQuery(query);
    }

    @PostMapping()
    public Integer saveFly(@RequestBody RequestCreateFlyDto requestCreateFly) {
        return flyService.createNewFly(requestCreateFly);
    }

}
