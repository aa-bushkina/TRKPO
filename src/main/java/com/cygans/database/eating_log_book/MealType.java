package com.cygans.database.eating_log_book;

public enum MealType {
  BREAKFAST("Завтрак"),
  LAUNCH("Обед"),
  DINNER("Ужин"),
  OTHER("Другое");
  private final String text;

  MealType(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
