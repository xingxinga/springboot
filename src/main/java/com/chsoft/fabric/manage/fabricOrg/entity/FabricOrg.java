package com.chsoft.fabric.manage.fabricOrg.entity;

import org.springframework.stereotype.Component;

@Component
public class FabricOrg {
    private String id;

    private String orgName;

    private String orgMspId;

    private String orgDomainName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgMspId() {
        return orgMspId;
    }

    public void setOrgMspId(String orgMspId) {
        this.orgMspId = orgMspId == null ? null : orgMspId.trim();
    }

    public String getOrgDomainName() {
        return orgDomainName;
    }

    public void setOrgDomainName(String orgDomainName) {
        this.orgDomainName = orgDomainName == null ? null : orgDomainName.trim();
    }
}