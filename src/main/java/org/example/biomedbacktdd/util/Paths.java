package org.example.biomedbacktdd.util;

public enum Paths {
    DEP_ADMIN_PATH("/api/dependent/admin"),
    DEP_MANAGER_PATH("/api/dependent/manager"),
    DEP_COMMONUSER_PATH("/api/dependent/commonuser"),
    RES_ADMIN_PATH("/api/responsible/admin"),
    RES_MANAGER_PATH("/api/responsible/manager"),
    RES_COMMONUSER_PATH("/api/responsible/commonuser");

    private final String path;

    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
