package com.cygans.database.log_book.logs_type;

public enum LogBookType {

  EMOTIONAL("Эмоциональное состояние"),
  SPORT("Спортивная активность"),
  EATING("Приём пищи");

  private final String text;

  LogBookType(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
