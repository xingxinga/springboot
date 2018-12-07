package com.chsoft.fabric.manage.fabricUser.entity;

import com.chsoft.sys.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;
import org.springframework.stereotype.Component;

import java.util.Set;
@Setter
@Getter
@ToString
@Component
public class FabricUser extends BaseEntity implements User {
    private String id;

    private String username;

    private String privatekeyfilepath;

    private String certificatefile;

    private String orgId;

    /** 规则 */
    private Set<String> roles;
    /** 账户 */
    private String account;
    /** 从属联盟 */
    private String affiliation;
    /** 会员id */
    private String mspId;

    /** 注册登记操作 */
    Enrollment enrollment = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPrivatekeyfilepath() {
        return privatekeyfilepath;
    }

    public void setPrivatekeyfilepath(String privatekeyfilepath) {
        this.privatekeyfilepath = privatekeyfilepath == null ? null : privatekeyfilepath.trim();
    }

    public String getCertificatefile() {
        return certificatefile;
    }

    public void setCertificatefile(String certificatefile) {
        this.certificatefile = certificatefile == null ? null : certificatefile.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public Set<String> getRoles() {
        return roles;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public String getAffiliation() {
        return affiliation;
    }

    @Override
    public String getMspId() {
        return mspId;
    }

    @Override
    public Enrollment getEnrollment() {
        return enrollment;
    }
}