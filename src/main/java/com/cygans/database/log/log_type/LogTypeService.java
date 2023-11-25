package com.cygans.database.log.log_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogTypeService {

    @Autowired
    private LogTypeRepository logsTypeRepository;

    public Long getLogTypeId(String type) {
        return logsTypeRepository.findLogsTypeByType(type).getId();
    }

    public String getLogTypeById(Long id) {
        return logsTypeRepository.findLogsTypeById(id).getType();
    }

    public void fill() {
        if (logsTypeRepository.count() == 0) {
            logsTypeRepository.save(new LogType(LogBookType.EMOTIONAL.getText()));
            logsTypeRepository.save(new LogType(LogBookType.SPORT.getText()));
            logsTypeRepository.save(new LogType(LogBookType.EATING.getText()));
        } else if (logsTypeRepository.count() > 3) {
            System.out.println("Что-то идет не так, почистите таблицу log_books_types! В ней должно быть только 3 заранее добавленные записи!!!");
        }
    }

}
