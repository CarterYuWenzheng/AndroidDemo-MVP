package com.carter.javaAndroid.core.event;

public class RefreshTodoEvent {
    public int getStatus() {
        return status;
    }

    private int status;

    public RefreshTodoEvent(int status) {
        this.status = status;
    }

}
