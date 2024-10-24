package org.spring.hackathon.plogging.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PloggingLocationDto {

  private Long ploggingLocationNo;

  private double latitude;

  private double longitude;

  private double distance;

  public double getLatitude() { return latitude; }
  
  public double getLongitude() {
    return longitude;
  }

  //JSON -> JAVA 객체로 변환할 때 쓰임
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  //JSON -> JAVA 객체로 변환할 때 쓰임
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

}
