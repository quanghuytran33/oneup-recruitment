package com.oneup.external.tvdb.client;

import com.oneup.external.tvdb.configuration.TvDbConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class TvDbSeriesClient {

  private final WebClient tvDbOAuthorizedClient;
  private final TvDbConfigurationProperties tvDbConfigurationProperties;

  public Object retrieveSeriesByName(String seriesName) {
    return tvDbOAuthorizedClient.get().uri(uriBuilder ->
        uriBuilder.host(tvDbConfigurationProperties.getUrl())
            .path("/search/series")
            .queryParam("name", seriesName)
            .build()).exchange().block().bodyToMono(String.class);
  }

}
