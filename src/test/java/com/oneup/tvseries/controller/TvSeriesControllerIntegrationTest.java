package com.oneup.tvseries.controller;

import static com.oneup.tvseries.controller.TvSeriesController.TV_SERIES_PATH;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oneup.tvseries.AbstractOneUpTvSeriesControllerIntegrationTest;
import com.oneup.tvseries.domain.TvSeries;
import com.oneup.tvseries.repository.TvSeriesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class TvSeriesControllerIntegrationTest extends
    AbstractOneUpTvSeriesControllerIntegrationTest {

  @Autowired
  private TvSeriesRepository tvSeriesRepository;

  @Test
  public void shouldReturnTvSeries_whenIdIs1() throws JsonProcessingException {

    // @formatter:off
    /*int id =
        given()
            .body(mapper.writeValueAsString(new TvSeries(1, "test series", "test overview 1", "test banner 1")))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
            .post(uri + TV_SERIES_PATH + "/create")
        .then()
            .assertThat()
            .statusCode(HttpStatus.CREATED.value())
        .extract().path("id");*/

    TvSeries tvSeries = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
    .when()
        .get(uri + TV_SERIES_PATH + "/" + 1)
    .then()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .extract().as(TvSeries.class);

    assertEquals("test series", tvSeries.getSeriesName());
    assertEquals("test overview 1", tvSeries.getOverview());
    assertEquals("test banner 1", tvSeries.getBanner());
    // @formatter:on
  }

  @Test
  public void shouldReturnNotFound_whenIdIsBad() throws JsonProcessingException {

    // @formatter:off
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
    .when()
        .get(uri + TV_SERIES_PATH + "/" + 9999)
    .then()
        .assertThat()
        .statusCode(HttpStatus.NOT_FOUND.value());
    // @formatter:on
  }

  @Test
  public void shouldReturnArrayWith2Elements_whenNameIsTestSeries() throws JsonProcessingException {

    // @formatter:off
    TvSeries[] listTvSeries =
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .param("name","test series")
    .when()
        .get(uri + TV_SERIES_PATH + "/search")
    .then()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .extract()
        .body().as(TvSeries[].class);

    assertEquals(2, listTvSeries.length);
    // @formatter:on
  }

  @Test
  public void shouldReturnArrayWith2Elements_whenNameIsBad() throws JsonProcessingException {

    // @formatter:off
    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .param("name","bad")
    .when()
        .get(uri + TV_SERIES_PATH + "/search")
    .then()
        .assertThat()
        .statusCode(HttpStatus.NOT_FOUND.value());

    // @formatter:on
  }

}
