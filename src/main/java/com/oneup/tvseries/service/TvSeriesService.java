package com.oneup.tvseries.service;

import com.oneup.tvseries.domain.TvSeries;
import com.oneup.tvseries.exception.ResourceNotFoundException;
import com.oneup.tvseries.repository.TvSeriesRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TvSeriesService {

  private final TvSeriesRepository tvSeriesRepository;

  public TvSeries saveTvSeries(TvSeries tvSeries) {
    return tvSeriesRepository.save(tvSeries);
  }

  public List<TvSeries> retrieveAllTvSeries() {
    return tvSeriesRepository.findAll();
  }

  public TvSeries retrieveTvSeriesById(final long id) {
    return tvSeriesRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException(TvSeries.class.getName(), String.valueOf(id)));
  }

  public List<TvSeries> retrieveTvSeriesByName(String seriesName) {

    List<TvSeries> result = tvSeriesRepository.findBySeriesName(seriesName);
    if(result.isEmpty()) {
      throw new ResourceNotFoundException("Series Name " + seriesName + " not found");
    }
    return result;
  }

}
