package com.oneup.external.tvdb.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tvdb.oauth2.client")
@Data
public class TvDbConfigurationProperties {

  private String apikey;
  private String userkey;
  private String username;
  private String url;
}
