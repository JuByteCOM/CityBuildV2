package com.jubyte.citybuild.manager.booster;

public enum BoosterType {
  ALL("All", 1),
  FLY("Fly", 2),
  XP("Erfahrung", 3),
  SPEED("Speed", 4),
  JUMP("Jump", 5),
  Haste("Break", 6),
  MONEY("Farm", 7),
  DROP("Drop", 8),
  ;

  private final String name;
  private final int id;

  BoosterType(String name, int id) {
    this.name = name;
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public int getId() {
    return this.id;
  }

  public static BoosterType byId(int id) {
    for (BoosterType booster : BoosterType.values()) {
      if (booster.id == id) return booster;
    }
    throw new IllegalArgumentException("No booster found for id " + id);
  }

  public static BoosterType byName(String name) {
    for (BoosterType type : BoosterType.values()) {
      if (type.getName().equalsIgnoreCase(name)) return type;
    }
    return null;
  }

  @SuppressWarnings("unused")
  public static boolean isInteger(String value) {
    try {
      int number = Integer.parseInt(value);
      return true;
    } catch (NumberFormatException ignored) {
      return false;
    }
  }
}
