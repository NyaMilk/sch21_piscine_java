package edu.school21.classes;

import java.util.StringJoiner;

public class Car {
    private String modelName;
    private int speed;

    public Car() {
        this.modelName = "Default model";
        this.speed = 0;
    }

    public Car(String modelName, int speed) {
        this.modelName = modelName;
        this.speed = speed;
    }

    public int boost(int value) {
        this.speed += value;
        return speed;
    }

    public int fullChange(int value, String name) {
        this.speed += value;
        this.modelName = name;
        return speed;
    }

    public void changeSpeed(int value) {
        this.speed = value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("modelName='" + modelName + "'")
                .add("speed=" + speed)
                .toString();
    }
}
