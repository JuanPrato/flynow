package com.flycorp.fly;

import com.flycorp.lib.fly.RequestCreateFly;
import com.flycorp.fly.service.FlyService;
import com.flycorp.lib.fly.ResponseGetFlies;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/fly")
public record FlyController(FlyService flyService) {

    @GetMapping(value = { "", "{query}" })
    public ResponseGetFlies getFlies(@PathVariable(required = false) String query) {
        log.info("search request with query {}", query);
        ResponseGetFlies response = new ResponseGetFlies();
        response.setFlies(flyService.getFliesByQuery(query));
        return response;
    }

    @PostMapping()
    public Integer saveFly(@RequestBody RequestCreateFly requestCreateFly) {
        return flyService.createNewFly(requestCreateFly);
    }

}
