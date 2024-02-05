package com.cygans.database.log_book.logs_type;

import com.cygans.database.emotional_log_book.EmotionalLogBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogsTypeService {

    private final LogsTypeRepository logsTypeRepository;

    @Autowired
    public LogsTypeService(LogsTypeRepository logsTypeRepository) {
        this.logsTypeRepository = logsTypeRepository;
    }

    public Long getLogTypeId(String type) {
        return logsTypeRepository.findLogsTypeByType(type).getId();
    }

    public String getLogTypeById(Long id) {
        return logsTypeRepository.findLogsTypeById(id).getType();
    }

    public void fill() {
        if (logsTypeRepository.count() == 0) {
            logsTypeRepository.save(new LogsType(LogBookType.EMOTIONAL.getText()));
            logsTypeRepository.save(new LogsType(LogBookType.SPORT.getText()));
            logsTypeRepository.save(new LogsType(LogBookType.EATING.getText()));
        } else if (logsTypeRepository.count() > 3) {
            System.out.println("Что-то идет не так, почистите таблицу log_books_types! В ней должно быть только 3 заранее добавленные записи!!!");
        }
    }

    public List<String> getAllLogsTypes() {
        return new ArrayList<>(List.of(
                LogBookType.EMOTIONAL.getText(),
                LogBookType.SPORT.getText(),
                LogBookType.EATING.getText()));
    }

}
