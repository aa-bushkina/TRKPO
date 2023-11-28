package com.cygans.database.sport_db.intensity;

public enum IntensityType {

  LOWER("Низкая"),
  MIDDLE("Средняя"),
  HIGH("Высокая");

  private final String text;

  IntensityType(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
