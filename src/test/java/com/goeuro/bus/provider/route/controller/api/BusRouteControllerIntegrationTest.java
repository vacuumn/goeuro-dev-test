package com.goeuro.bus.provider.route.controller.api;

import com.goeuro.BusRouteServiceRunner;
import com.goeuro.bus.provider.route.service.BusRouteService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.restassured.operation.preprocess.RestAssuredPreprocessors.modifyUris;

/**
 * Integration test for BusRouteController
 *
 * @author Serhii Pichkurov (serhiip@jfrog.com)
 */
@SpringApplicationConfiguration(classes = BusRouteServiceRunner.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringRunner.class)
public class BusRouteControllerIntegrationTest {

    @Autowired
    BusRouteService busRouteService;

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");

    @Value("${local.server.port}")
    private int port;

    private RequestSpecification spec;

    static {
        BusRouteServiceRunner.routes = BusRouteServiceRunner.parseRoutes("test-routes");
    }

    @Before
    public void setUp() {
        RestAssured.basePath = "api";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    public void shouldReturnTrueWhenDirect() {
        //@formatter:off
        given(this.spec).filter(document("direct", preprocessRequest(modifyUris().port(8088)))).
                queryParam("dep_sid", 5).
                queryParam("arr_sid", 142).
        when().
                port(this.port).
                get("/direct").
        then().
                statusCode(200).
                body("dep_sid", is(5)).
                body("arr_sid", is(142)).
                body("direct_bus_route", is(true));
        //@formatter:on
    }

    @Test
    public void shouldReturnErrorWhenBadQueryParam() {
        //@formatter:off
        given().
                queryParam("dep_sid", "string").
                queryParam("arr_sid", 142).
        when().
                port(this.port).
                get("/direct").
        then().
                statusCode(400);
        //@formatter:on
    }

}