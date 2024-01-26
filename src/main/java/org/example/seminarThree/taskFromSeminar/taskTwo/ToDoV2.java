package org.example.seminarThree.taskFromSeminar.taskTwo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ToDoV2 implements Externalizable {

    private String title;
    private boolean isDone;

    public ToDoV2(String title) {
        this.title = title;
        this.isDone = false;
    }

    public ToDoV2(){}
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        out.writeObject(title);
        out.writeBoolean(isDone);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        title = (String) in.readObject();
        isDone = in.readBoolean();
    }

    public String getTitle() {
        return title;
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
