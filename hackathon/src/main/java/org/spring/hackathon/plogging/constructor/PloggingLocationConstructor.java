package org.spring.hackathon.plogging.constructor;

import org.spring.hackathon.plogging.domain.PloggingLocationEntity;
import org.spring.hackathon.plogging.dto.PloggingLocationDto;

public class PloggingLocationConstructor {

  public static PloggingLocationDto coordinateGet(PloggingLocationEntity locationEntity) {

    PloggingLocationDto locationDto = new PloggingLocationDto();

    locationDto.setLatitude(locationEntity.getLatitude());
    locationDto.setLongitude(locationEntity.getLongitude());

    return locationDto;

  }

}
