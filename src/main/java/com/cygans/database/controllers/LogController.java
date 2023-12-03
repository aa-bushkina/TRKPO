package com.cygans.database.controllers;

import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.eating_log_book.EatingLogBookService;
import com.cygans.database.eating_log_book.meal.MealService;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBookService;
import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogService;
import com.cygans.database.log_book.logs_type.LogBookType;
import com.cygans.database.log_book.logs_type.LogsType;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.SportLogBookService;
import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.database.sport_log_book.intensity.IntensityService;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.cygans.views.participant.logbooks.ParticipantPersonData;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class LogController {

    @Autowired
    private LogService logService;
    @Autowired
    private LogsTypeService logsTypeService;
    @Autowired
    private SportLogBookService sportLogBookService;
    @Autowired
    private IntensityService intensityService;
    @Autowired
    private EmotionalLogBookService emotionalLogBookService;
    @Autowired
    private EatingLogBookService eatingLogBookService;
    @Autowired
    private MealService mealService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private NotificationsService notificationsService;

    private Long saveGeneralLog(LogBookType type) {
        Long participantId = participantService.getParticipantByLoginInfoId(
                loginInfoService.findByLogin(
                        SecurityContextHolder.getContext().getAuthentication().getName()
                ).getId()
        ).getId();
        Log log = new Log(
                participantId,
                (LocalDate) VaadinSession.getCurrent().getAttribute("date"),
                logsTypeService.getLogTypeId(type.getText())
        );
        logService.saveLog(log);
        return log.getId();
    }

    public Long saveSportLog(String intensity, String duration, String activity, String comments) {
        Long logId = saveGeneralLog(LogBookType.SPORT);
        SportLogBook sportLogBook = new SportLogBook(
                logId,
                intensityService.getIntensityId(intensity),
                Integer.parseInt(duration),
                LocalDateTime.now(),
                activity,
                comments);
        sportLogBookService.saveSportLog(sportLogBook);
        return logId;
    }

    public Long saveEatingLog(LocalTime time, String description, String meal_type) {
        Long logId = saveGeneralLog(LogBookType.EATING);
        EatingLogBook eatingLogBook = new EatingLogBook(
                logId,
                time,
                description,
                mealService.getMealId(meal_type),
                LocalDateTime.now());
        eatingLogBookService.saveEatingLog(eatingLogBook);
        return logId;
    }

    public Long saveEmotionalLog(String comments) {
        Long logId = saveGeneralLog(LogBookType.EMOTIONAL);
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook(
                logId,
                LocalDateTime.now(),
                comments
        );
        emotionalLogBookService.saveEmotionalLog(emotionalLogBook);
        return logId;
    }

    public List<Log> getAllNowParticipantLogs(boolean byAuthentication) {
        Long participantId = null;
        if (byAuthentication) {
            participantId = getIdNowParticipantByAuthentication();
        } else {
            participantId = getIdNowParticipantByAttribute();
        }
        return logService.findLogBooksByParticipantId(participantId);
    }

    public List<Log> getAllNowParticipantLogsBetweenDate(LocalDate checkDate, LocalDate today, boolean byAuthentication) {
        Long participantId = null;
        if (byAuthentication) {
            participantId = getIdNowParticipantByAuthentication();
        } else {
            participantId = getIdNowParticipantByAttribute();
        }
        return logService.findLogBooksBetweenDate(checkDate, today, participantId);
    }

    public List<ParticipantPersonData> convertListLogToParticipantPersonData(List<Log> logBook) {
        List<ParticipantPersonData> participantPersonData = new ArrayList<>();

        for (Log log : logBook) {
            ParticipantPersonData addData = new ParticipantPersonData();
            addData.setDate(log.getDate());
            addData.setLogBookType(getLogsLogtype(log));
            addData.setLogBookId(log.getId());
            participantPersonData.add(addData);
        }
        participantPersonData.sort(Comparator.comparing(ParticipantPersonData::getDate).reversed());

        return participantPersonData;
    }

    public Long getIdNowParticipantByAuthentication() {
        return participantService
                .getParticipantByLoginInfoId(
                        loginInfoService.findByLogin(
                                SecurityContextHolder.getContext().getAuthentication().getName()
                        ).getId()
                ).getId();
    }

    public Long getIdNowParticipantByAttribute() {
        return participantService.getParticipantByLoginInfoId(
                (Long) VaadinSession.getCurrent().getAttribute("ParticipantID")
        ).getId();
    }

    public String getLogsLogtype(Log log) {
        return logsTypeService.getLogTypeById(log.getLogTypeId());
    }
    public Long getLogTypeIdByName(LogBookType type) {
        return logsTypeService.getLogTypeId(type.getText());
    }
    public String getMealEatingLog(Long mealId) {
        return mealService.getMealType(mealId);
    }

    public Intensity getIntensitySportLog(Long intensityId) {
        return intensityService.getIntensityType(intensityId);
    }

    public void updateEatingLog(Long logBookId, String foodDesc, LocalTime time, String meal) {
        Long eatingLogId = eatingLogBookService.findByLogBookId(logBookId).getId();
        if (foodDesc != null) {
            eatingLogBookService.updateEatingDescription(eatingLogId, foodDesc);
        }
        if (time != null) {
            eatingLogBookService.updateFoodTime(eatingLogId, time);
        }
        if (meal != null) {
            eatingLogBookService.updateMale(eatingLogId, mealService.getMealId(meal));
        }
    }

    public void updateEmotionalLog(Long logBookId, String emotionalDescText) {
        Long emotionalLogId = emotionalLogBookService.findByLogBookId(logBookId).getId();
        if (emotionalDescText != null) {
            emotionalLogBookService.updateEmotionalDescription(emotionalLogId, emotionalDescText);
        }
    }

    public void updateSportLog(Long logBookId, String sportDesc, String activityField, String intensity, Integer durationField) {
        Long sportLogId = sportLogBookService.findByLogBookId(logBookId).getId();
        if (sportDesc != null) {
            sportLogBookService.updateSportDescription(sportLogId, sportDesc);
        }
        if (activityField != null) {
            sportLogBookService.updateActivity(sportLogId, activityField);
        }
        if (intensity != null) {
            sportLogBookService.updateIntensityId(sportLogId, intensityService.getIntensityId(intensity));
        }
        if (durationField != null) {
            sportLogBookService.updateDuration(sportLogId, durationField);
        }
    }

    public EmotionalLogBook getEmotionalLogByLogbookId(Long logBookId) {
        return emotionalLogBookService.findByLogBookId(logBookId);
    }

    public EatingLogBook getEatingLogByLogbookId(Long logBookId) {
        return eatingLogBookService.findByLogBookId(logBookId);
    }

    public SportLogBook getSportLogByLogbookId(Long logBookId) {
        return sportLogBookService.findByLogBookId(logBookId);
    }

    public List<String> getAllLogsTypes() {
        var allTypes = logsTypeService.getAllLogsTypes();
        allTypes.add(0, "Все");
        return allTypes;
    }

    public String getAnswerForLog(Long logBookId) {
        return notificationsService.getNotificationByLogBookId(logBookId).getReplyMessage();
    }

}
