package com.example.course_registration.model.course;

public enum CourseDuration {
    THIRTY(30), SIXTY(60);

    private final int minutes;

    CourseDuration(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }
}