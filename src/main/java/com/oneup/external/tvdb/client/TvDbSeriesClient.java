package com.oneup.external.tvdb.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class TvDbSeriesClient {

  private final WebClient tvDbOAuthorizedClient;

  public Object retrieveSeriesByName(String seriesName) {
    return tvDbOAuthorizedClient.get().uri(uriBuilder ->
        uriBuilder.path("/search/series")
            .queryParam("name", seriesName)
            .build()).retrieve().bodyToMono(String.class);
  }

  public Object retrieveSeriesById(long id) {
    return tvDbOAuthorizedClient.get().uri(uriBuilder ->
        uriBuilder.path("/series/" + id)
            .build()).retrieve().bodyToMono(String.class);
  }

}
