//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ayiko.backend.exception;

public class SimpleResponse  {
    private Status status;

    public SimpleResponse(Status status) {
        this.status = status;
    }

    public SimpleResponse() {
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
