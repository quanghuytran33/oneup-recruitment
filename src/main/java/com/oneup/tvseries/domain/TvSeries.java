package com.oneup.tvseries.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TvSeries {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String seriesName;

  private String overview;

  private String banner;

}
