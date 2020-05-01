package com.oneup.external.tvdb.client;

import com.oneup.external.tvdb.configuration.TvDbConfigurationProperties;
import com.oneup.external.tvdb.dto.TvDbAccessTokenRequest;
import java.time.Clock;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizationContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TvDbOAuth2AuthorizedClientProvider implements OAuth2AuthorizedClientProvider {

  private Duration clockSkew = Duration.ofHours(24);
  private Clock clock = Clock.systemUTC();

  private final TvDbAccessResponseClient accessResponseClient;
  private final TvDbConfigurationProperties tvDbConfigurationProperties;

  @Override
  public OAuth2AuthorizedClient authorize(OAuth2AuthorizationContext context) {

    ClientRegistration clientRegistration = context.getClientRegistration();
    OAuth2AuthorizedClient authorizedClient = context.getAuthorizedClient();
    if (authorizedClient != null && !hasTokenExpired(authorizedClient.getAccessToken())) {
      return null;
    }

    OAuth2AccessToken tokenResponse;
    if (authorizedClient == null || authorizedClient.getAccessToken() == null) {
      tokenResponse = accessResponseClient.getTokenResponse(
          new TvDbAccessTokenRequest(tvDbConfigurationProperties.getApikey(),
              tvDbConfigurationProperties.getUserkey(),
              tvDbConfigurationProperties.getUsername()));
    } else {
      tokenResponse = accessResponseClient
          .getTokenResponseWithRefresh(authorizedClient.getAccessToken().getTokenValue());
    }

    return new OAuth2AuthorizedClient(clientRegistration, "OneUp", tokenResponse);
  }

  private boolean hasTokenExpired(AbstractOAuth2Token token) {
    return this.clock.instant().isAfter(token.getExpiresAt().minus(this.clockSkew));
  }
}
