package org.example.biomedbacktdd.VO.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({"id", "userName", "fullName", "password", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled", "roles"})
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2447334822822351687L;

    @JsonProperty("id")
    private Long key;
    private String userName;
    private String fullName;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<String> roles;

    public UserVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVO userVO = (UserVO) o;
        return accountNonExpired == userVO.accountNonExpired && accountNonLocked == userVO.accountNonLocked && credentialsNonExpired == userVO.credentialsNonExpired && enabled == userVO.enabled && Objects.equals(key, userVO.key) && Objects.equals(userName, userVO.userName) && Objects.equals(fullName, userVO.fullName) && Objects.equals(password, userVO.password) && Objects.equals(roles, userVO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, userName, fullName, password, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, roles);
    }
}