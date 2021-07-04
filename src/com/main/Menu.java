package com.main;

public class Menu {

    protected String name;
    protected int cookingTimeSec;

    public Menu(String menuName, float menuCookingTime, char cookingTimeUnit) {
        this.name = menuName;
        switch (cookingTimeUnit) {
            case 's' -> this.cookingTimeSec = (int) menuCookingTime;
            case 'm' -> this.cookingTimeSec = (int) (menuCookingTime * 60);
            default -> throw new IllegalStateException("Unexpected value: " + cookingTimeUnit);
        }
    }

    public float getCookingTimeInMinute() {
        return (float) this.cookingTimeSec / 60;
    }

    public int getCookingTimeInSecond() {
        return this.cookingTimeSec;
    }
}
