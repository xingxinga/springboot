package com.chsoft.fabric.manage.fabricPeer.entity;

import org.springframework.stereotype.Component;

@Component
public class FabricPeer {
    private String id;

    private String peerName;

    private String peerLocation;

    private String orgId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPeerName() {
        return peerName;
    }

    public void setPeerName(String peerName) {
        this.peerName = peerName == null ? null : peerName.trim();
    }

    public String getPeerLocation() {
        return peerLocation;
    }

    public void setPeerLocation(String peerLocation) {
        this.peerLocation = peerLocation == null ? null : peerLocation.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }
}