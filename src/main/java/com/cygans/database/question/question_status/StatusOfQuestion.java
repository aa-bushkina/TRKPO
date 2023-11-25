package com.cygans.database.question.question_status;

public enum StatusOfQuestion {

    NO_ANSWER("Нет ответа"),
    YES_ANSWER("Ответ получен");
    private final String text;

    StatusOfQuestion(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
