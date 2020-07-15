package org.wzxy.breeze.core.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.dto.UserDto;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class User implements Serializable {

    private int uid;
    private int administrationId;
    private String upwd;
    private String salt;
    private String uname;
    private List<role> roles;
    private organization thisOrgan;

    public User() {
        super();
    }

    public User(int uid, int administrationId, String upwd, String salt, String uname, List<role> roles, organization thisOrgan) {
        this.uid = uid;
        this.administrationId = administrationId;
        this.upwd = upwd;
        this.salt = salt;
        this.uname = uname;
        this.roles = roles;
        this.thisOrgan = thisOrgan;
    }

    public User(UserDto userdto) {
        this.uid = userdto.getUid();
        this.administrationId = userdto.getAdministrationId();
        this.upwd = userdto.getUpwd();
        this.uname = userdto.getUname();
    }

    public List<role> getRoles() {
        return roles;
    }

    public void setRoles(List<role> roles) {
        this.roles = roles;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }


    public int getAdministrationId() {
        return administrationId;
    }

    public void setAdministrationId(int administrationId) {
        this.administrationId = administrationId;
    }

    public organization getThisOrgan() {
        return thisOrgan;
    }

    public void setThisOrgan(organization thisOrgan) {
        this.thisOrgan = thisOrgan;
    }
}
