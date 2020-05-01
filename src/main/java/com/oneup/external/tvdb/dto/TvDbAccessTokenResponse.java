package com.oneup.external.tvdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TvDbAccessTokenResponse {

  @JsonProperty("token")
  private String token;
}
