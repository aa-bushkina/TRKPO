package com.cygans.database.sport_db.intensity;

import org.springframework.data.repository.CrudRepository;

public interface IntensityRepository extends CrudRepository<Intensity, Long> {
    Intensity findIntensityById(Long id);
    Intensity findIntensityByType(String meal);
    @Override
    <S extends Intensity> S save(S s);

}
