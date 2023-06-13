package com.route.optimalpathfinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.route.optimalpathfinder.controller.OptimalPathController;
import com.route.optimalpathfinder.feign.CountryRouteFeign;
import com.route.optimalpathfinder.payload.request.RouteRequest;
import com.route.optimalpathfinder.payload.response.RouteResponse;
import com.route.optimalpathfinder.service.OptimalPathService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OptimalPathController.class)
public class OptimalPathControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OptimalPathService optimalPathService;

	@MockBean
	private CountryRouteFeign countryRouteFeign;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@WithMockUser
	public void calculateLandRoute_ReturnsRouteResponse() throws Exception {
		// Arrange
		String from = "USA";
		String to = "CAN";

		RouteRequest routeRequest = new RouteRequest();
		routeRequest.setFrom(from);
		routeRequest.setTo(to);
		RouteResponse expectedResponse = RouteResponse.builder()
				.routeList(List.of("USA", "CAN"))
				.build();

		when(optimalPathService.findOptimalPath(anyString(), anyString())).thenReturn(expectedResponse);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/route")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(routeRequest)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.routeList").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$.routeList[0]").value("USA"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.routeList[1]").value("CAN"));
	}
}
