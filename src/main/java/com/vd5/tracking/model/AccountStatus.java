package com.vd5.tracking.model;

/**
 * @author beou on 10/16/17 21:14
 */

public enum AccountStatus {
        UNKNOWN("unknown"),
        DELETED("deleted"),
        PENDING("pending"),
        INACTIVATED("inactivated"),
        ACTIVATED("activated");

    private final String status;

    AccountStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }

    public static AccountStatus get(String status) {
        if ("activated".equalsIgnoreCase(status)) {
            return ACTIVATED;
        } else if ("inactivated".equalsIgnoreCase(status)) {
            return INACTIVATED;
        } else if ("pending".equalsIgnoreCase(status)) {
            return PENDING;
        } else if ("deleted".equalsIgnoreCase(status)) {
            return DELETED;
        } else {
            return UNKNOWN;
        }
    }
}
