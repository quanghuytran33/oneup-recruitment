package com.oneup.tvseries.repository;

import com.oneup.tvseries.domain.TvSeries;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvSeriesRepository extends JpaRepository<TvSeries, Long> {

  List<TvSeries> findBySeriesName(String seriesName);
}
