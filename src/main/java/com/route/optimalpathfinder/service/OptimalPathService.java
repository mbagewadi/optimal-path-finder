package com.route.optimalpathfinder.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.route.optimalpathfinder.bfs.BFSAlgorithm;
import com.route.optimalpathfinder.exception.RouteNotFoundException;
import com.route.optimalpathfinder.feign.CountryRouteFeign;
import com.route.optimalpathfinder.model.Country;
import com.route.optimalpathfinder.payload.response.RouteResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OptimalPathService {

    private final CountryRouteFeign countryRouteFeign;

    public RouteResponse findOptimalPath(String from, String to) {

        log.info("OptimalPathService | findOptimalPath is working");

        List<Country> countries = countryRouteFeign.fetchCountries();

        log.info("OptimalPathService | findOptimalPath | countries :  " + countries.size());

        Map<String, Country> countryMap = countries.stream().collect(Collectors.toMap(route -> route.getCca3(), it -> it));

        log.info("OptimalPathService | findOptimalPath | countryMap :  " + countryMap.toString());

        if(from.equals(to)){
            throw new RouteNotFoundException("From and to cannot be equal");
        }

        Country fromCountry = countryMap.get(from);
        Country toCountry = countryMap.get(to);

        log.info("OptimalPathService | findOptimalPath | fromCountry :  " + fromCountry.getCca3());
        log.info("OptimalPathService | findOptimalPath | toCountry :  " + toCountry.getCca3());

        if(fromCountry == null){
            throw new RouteNotFoundException("From cannot be null");
        }

        if(toCountry == null){
            throw new RouteNotFoundException("To cannot be null");
        }

        BFSAlgorithm bfsAlgorithm =new BFSAlgorithm();
        List<String> routes = bfsAlgorithm.shortestPathBFS(fromCountry,toCountry,countryMap);

        RouteResponse routeResponse = RouteResponse.builder()
                .routeList(routes)
                .build();

        return routeResponse;
    }
}
