package com.cygans.database.sport_db.intensity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntensityService {

  @Autowired
  private IntensityRepository intensityRepository;

  public Long getIntensityId(String type) {
    return intensityRepository.findIntensityByType(type).getId();
  }

  public String getIntensityType(Long id) {
    return intensityRepository.findIntensityById(id).getType();
  }

  public void fill() {
    if (intensityRepository.count() == 0) {
      intensityRepository.save(new Intensity(IntensityType.LOWER.getText()));
      intensityRepository.save(new Intensity(IntensityType.MIDDLE.getText()));
      intensityRepository.save(new Intensity(IntensityType.HIGH.getText()));
    } else if (intensityRepository.count() > 3) {
      System.out.println("Что-то идет не так, почистите таблицу intensity!!! В ней должно быть только 3 заранее добавленные записи!!!");
    }
  }

  public String findIntensityById(Long id) {
    return intensityRepository.findIntensityById(id).getType();
  }

}
