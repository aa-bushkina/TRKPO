package com.cygans.database.log_book.logs_type;

import org.springframework.data.repository.CrudRepository;

public interface LogsTypeRepository extends CrudRepository<LogsType, Long> {
    LogsType findLogsTypeById(Long id);
    LogsType findLogsTypeByType(String meal);
    @Override
    <S extends LogsType> S save(S s);

}
