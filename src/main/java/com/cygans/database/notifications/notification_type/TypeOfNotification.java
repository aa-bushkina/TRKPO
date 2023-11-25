package com.cygans.database.notifications.notification_type;

public enum TypeOfNotification {
    ADD_REQUEST("Добавление в отслеживание"),
    DELETE_REQUEST("Удаление из отслеживания"),
    QUESTION("Вопрос"),
    NEW_LOG("Новая запись участника"),
    ANSWER_ON_LOG("Ответ ментора на запись"),
    ANSWER_ON_QUESTION("Ответ ментора на вопрос"),
    DECLINE_MENTOR("Отказ в отслеживании");
    private final String value;

    TypeOfNotification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
