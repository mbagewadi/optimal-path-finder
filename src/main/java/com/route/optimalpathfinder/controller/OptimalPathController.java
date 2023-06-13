package com.route.optimalpathfinder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.route.optimalpathfinder.payload.request.RouteRequest;
import com.route.optimalpathfinder.payload.response.RouteResponse;
import com.route.optimalpathfinder.service.OptimalPathService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OptimalPathController {

    private final OptimalPathService optimalPathService;

    @GetMapping("/route")
    public ResponseEntity<RouteResponse> calculateLandRoute(@RequestBody RouteRequest routeRequest) {

        log.info("OptimalPathController | calculateLandRoute is working");

        String from = routeRequest.getFrom();
        String to = routeRequest.getTo();

        log.info("OptimalPathController | calculateLandRoute | from : " +from);
        log.info("OptimalPathController | calculateLandRoute | to : " + to);

        return new ResponseEntity<RouteResponse>(optimalPathService.findOptimalPath(from,to), HttpStatus.OK);
    }
}
