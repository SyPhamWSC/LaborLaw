package com.dtsgroup.labourlaw.model;

/**
 * Created by vantr on 5/15/2017.
 */

public class EventMessage {
    private String action;
    private String data;

    public EventMessage(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
