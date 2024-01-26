package org.example.seminarThree.taskFromSeminar.taskTwo;

import lombok.Getter;

import java.io.Serializable;

public class ToDoV1 implements Serializable {

    @Getter
    private String title;
    private boolean isDone;

    public ToDoV1(String title) {
        this.title = title;
        this.isDone = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
