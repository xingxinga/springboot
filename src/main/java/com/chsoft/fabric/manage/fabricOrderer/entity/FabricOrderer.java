package com.chsoft.fabric.manage.fabricOrderer.entity;

import org.springframework.stereotype.Component;

@Component
public class FabricOrderer {
    private String id;

    private String ordererName;

    private String ordererLocation;

    private String ordererDomainName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrdererName() {
        return ordererName;
    }

    public void setOrdererName(String ordererName) {
        this.ordererName = ordererName == null ? null : ordererName.trim();
    }

    public String getOrdererLocation() {
        return ordererLocation;
    }

    public void setOrdererLocation(String ordererLocation) {
        this.ordererLocation = ordererLocation == null ? null : ordererLocation.trim();
    }

    public String getOrdererDomainName() {
        return ordererDomainName;
    }

    public void setOrdererDomainName(String ordererDomainName) {
        this.ordererDomainName = ordererDomainName == null ? null : ordererDomainName.trim();
    }
}