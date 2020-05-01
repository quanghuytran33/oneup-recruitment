package com.oneup.external.tvdb.client;

import com.oneup.external.tvdb.configuration.TvDbConfigurationProperties;
import com.oneup.external.tvdb.dto.TvDbAccessTokenRequest;
import com.oneup.external.tvdb.dto.TvDbAccessTokenResponse;
import java.net.URI;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class TvDbAccessResponseClient {

  private final WebClient tvDbWebClient;
  private final TvDbConfigurationProperties tvDbConfigurationProperties;
  private static final long HOURS_TO_EXPIRED = 24;

  public OAuth2AccessToken getTokenResponse(TvDbAccessTokenRequest accessTokenRequest) {

    TvDbAccessTokenResponse accessTokenResponse = tvDbWebClient.post()
        .uri(URI.create(tvDbConfigurationProperties.getUrl() + "/login"))
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(accessTokenRequest)
        .retrieve().bodyToMono(TvDbAccessTokenResponse.class).block();

    return new OAuth2AccessToken(TokenType.BEARER, accessTokenResponse.getToken(), Instant.now(),
        Instant.now().plus(HOURS_TO_EXPIRED, ChronoUnit.HOURS));
  }

  public OAuth2AccessToken getTokenResponseWithRefresh(String accessToken) {
    TvDbAccessTokenResponse accessTokenResponse = tvDbWebClient.get()
        .uri(URI.create(tvDbConfigurationProperties.getUrl() + "/refresh_token"))
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", "Bearer " + accessToken)
        .retrieve().bodyToMono(TvDbAccessTokenResponse.class).block();

    return new OAuth2AccessToken(TokenType.BEARER, accessTokenResponse.getToken(), Instant.now(),
        Instant.now().plus(HOURS_TO_EXPIRED, ChronoUnit.HOURS));
  }

}
