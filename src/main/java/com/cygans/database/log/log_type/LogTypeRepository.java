package com.cygans.database.log.log_type;


import org.springframework.data.repository.CrudRepository;

public interface LogTypeRepository extends CrudRepository<LogType, Long> {
    LogType findLogsTypeById(Long id);
    LogType findLogsTypeByType(String meal);
    @Override
    <S extends LogType> S save(S s);

}
