package com.example.projectmanagement.entity;

public enum Status {
    READY_FOR_DEV("Ready for dev"),
    IN_DEVELOPMENT("In development"),
    READY_FOR_CODE_REVIEW("Ready for code review"),
    IN_REVIEW("In review"),
    DONE("Done"),
    CLOSED("Closed");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
