package com.route.optimalpathfinder.bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import com.route.optimalpathfinder.exception.RouteNotFoundException;
import com.route.optimalpathfinder.model.Country;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BFSAlgorithm {

    private final Map<Country, Boolean> visitedCountryMap = new HashMap<>();
    private final Map<Country, Country> previousPathMap = new HashMap<>();
    private final List<Country> shortestPath = new ArrayList<>();

    public List<String> shortestPathBFS(Country countryFrom, Country countryTo , Map<String, Country> countries){

        log.info("BFSAlgorithm | shortestPathBFS is working");

        boolean shortestPathFound = false;
        Queue<Country> queue = new LinkedList<Country>();
        queue.add(countryFrom);

        while (!queue.isEmpty()) {
            Country currentCountry = queue.remove();
            visitedCountryMap.put(currentCountry, true);

            shortestPathFound = currentCountry.equals(countryTo) ? true : false;
            if(shortestPathFound)
                break;

            for (String borderCountry : currentCountry.getBorders()) {
                Country neighbourCountry = countries.get(borderCountry);
                if (neighbourCountry == null)
                    continue;

                if (!visitedCountryMap.containsKey(neighbourCountry)) {
                    queue.add(neighbourCountry);
                    visitedCountryMap.put(neighbourCountry, true);
                    previousPathMap.put(neighbourCountry, currentCountry);

                    shortestPathFound = neighbourCountry.equals(countryTo) ? true : false;
                    if(shortestPathFound)
                        break;
                }

            }

            if (shortestPathFound) {
                break;
            }
        }

        if (!shortestPathFound) {
            throw new RouteNotFoundException("Route cannot be created");
        }

        Country country = countryTo;

        while(country != null){
            shortestPath.add(country);
            country = previousPathMap.get(country);
        }

        Collections.reverse(shortestPath);
        return shortestPath.stream().map(route -> route.getCca3()).collect(Collectors.toList());
    }

}
