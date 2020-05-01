package com.oneup.tvseries.controller;

import static com.oneup.tvseries.controller.TvSeriesController.TV_SERIES_PATH;

import com.oneup.tvseries.domain.TvSeries;
import com.oneup.tvseries.service.TvSeriesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = TV_SERIES_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TvSeriesController {

  public static final String TV_SERIES_PATH = "/series";

  private final TvSeriesService tvSeriesService;

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public TvSeries createCustomerFile(@RequestBody TvSeries tvSeries) {
    return tvSeriesService.saveTvSeries(tvSeries);
  }

//  @GetMapping(value = "/{id}")
//  @ResponseStatus(HttpStatus.OK)
//  public TvSeries retrieveTvSeries(@PathVariable("id") long id) {
//    return tvSeriesService.retrieveTvSeriesById(id);
//  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Object retrieveTvSeries(@PathVariable("id") long id) {
    return tvSeriesService.retrieveTvSeriesByIdViaTvDb(id);
  }

//  @GetMapping(value = "/search")
//  @ResponseStatus(HttpStatus.OK)
//  public List<TvSeries> retrieveTvSeriesByName(@RequestParam("name") String name) {
//    return tvSeriesService.retrieveTvSeriesByName(name);
//  }

  @GetMapping(value = "/search")
  @ResponseStatus(HttpStatus.OK)
  public Object retrieveTvSeriesByName(@RequestParam("name") String name) {
    return tvSeriesService.retrieveTvSeriesByNameViaTvDb(name);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<TvSeries> retrieveAllTvSeries() {
    return tvSeriesService.retrieveAllTvSeries();
  }
}
