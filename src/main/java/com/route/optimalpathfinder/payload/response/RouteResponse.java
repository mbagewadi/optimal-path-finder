package com.route.optimalpathfinder.payload.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteResponse {

    private List<String> routeList;
}
