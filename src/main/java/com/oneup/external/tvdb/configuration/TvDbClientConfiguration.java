package com.oneup.external.tvdb.configuration;

import com.oneup.external.tvdb.client.TvDbOAuth2AuthorizedClientProvider;
import java.util.Arrays;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(value = {TvDbConfigurationProperties.class})
@ComponentScan(basePackages = {"com.oneup.external.tvdb.client"})
public class TvDbClientConfiguration {

  public static final String TVDB_CLIENT_REGISTRATION = "tvdb";

  @Bean
  public WebClient tvDbWebClient() {
    return WebClient.builder().build();
  }

  @Bean
  public WebClient tvDbOAuthorizedClient(OAuth2AuthorizedClientManager authorizedClientManager,
      TvDbConfigurationProperties tvDbConfigurationProperties) {
    ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
        new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
    oauth2Client.setDefaultClientRegistrationId(TVDB_CLIENT_REGISTRATION);
    return WebClient.builder()
        .baseUrl(tvDbConfigurationProperties.getUrl())
        .apply(oauth2Client.oauth2Configuration())
        .build();
  }

  @Bean
  public OAuth2AuthorizedClientManager authorizedClientManager(
      ClientRegistrationRepository clientRegistrationRepository,
      OAuth2AuthorizedClientRepository authorizedClientRepository,
      TvDbOAuth2AuthorizedClientProvider tvDbOAuth2AuthorizedClientProvider) {

    DefaultOAuth2AuthorizedClientManager authorizedClientManager =
        new DefaultOAuth2AuthorizedClientManager(
            clientRegistrationRepository, authorizedClientRepository);
    authorizedClientManager.setAuthorizedClientProvider(tvDbOAuth2AuthorizedClientProvider);

    return authorizedClientManager;
  }

  @Bean
  public ClientRegistrationRepository clientRegistrationRepository() {
    ClientRegistration registration = ClientRegistration
        .withRegistrationId(TVDB_CLIENT_REGISTRATION)
        .clientId("tvdb")
        .clientSecret("tvdb")
        .tokenUri("https://api.thetvdb.com")
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS).build();
    return new InMemoryClientRegistrationRepository(Arrays.asList(registration));
  }

  @Bean
  public OAuth2AuthorizedClientService authorizedClientService(
      ClientRegistrationRepository clientRegistrationRepository) {
    return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
  }

  @Bean
  public OAuth2AuthorizedClientRepository authorizedClientRepository(
      OAuth2AuthorizedClientService authorizedClientService) {
    return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
  }
}
