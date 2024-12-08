package org.example.biomedbacktdd.util;

public enum Roles {
    ADMIN("ADMIN"),
    CUIDADOR("CUIDADOR"),
    RESPONSAVEL("RESPONSAVEL"),
    TEMPUSER("TEMPUSER");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
