package com.route.optimalpathfinder.payload.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RouteRequest {
    private String from;
    private String to;
}
