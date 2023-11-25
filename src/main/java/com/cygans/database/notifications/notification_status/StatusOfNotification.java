package com.cygans.database.notifications.notification_status;

public enum StatusOfNotification {
    NO_ANSWER("Нет ответа"),
    ANSWERED_NOT_SEEN("Новый ответ"),
    ANSWERED_SEEN("Ответ просмотрен");
    private final String value;

    StatusOfNotification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}