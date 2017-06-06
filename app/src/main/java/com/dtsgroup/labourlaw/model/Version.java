package com.dtsgroup.labourlaw.model;


import io.realm.RealmObject;

public class Version extends RealmObject{

    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
