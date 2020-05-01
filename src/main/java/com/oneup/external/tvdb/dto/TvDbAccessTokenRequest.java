package com.oneup.external.tvdb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TvDbAccessTokenRequest {

  private final String apiKey;
  private final String userKey;
  private final String username;

}
