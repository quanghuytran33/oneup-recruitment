package com.oneup.tvseries;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AbstractOneUpTvSeriesControllerIntegrationTest {

  @LocalServerPort
  protected int port;

  protected String uri;

  protected ObjectMapper mapper = new ObjectMapper();

  @PostConstruct
  protected void init() {
    uri = "http://localhost:" + port;
  }

}
