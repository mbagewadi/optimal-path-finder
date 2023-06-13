package com.route.optimalpathfinder.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.route.optimalpathfinder.model.Country;

@FeignClient(name="country-route-api", url="https://raw.githubusercontent.com/mledoze/countries")
public interface CountryRouteFeign {

    @GetMapping(path = "/master/countries.json")
    List<Country> fetchCountries();
}
