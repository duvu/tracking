package com.vd5.tracking.entity;

import lombok.Data;

/**
 * @author beou on 8/1/17 04:00
 * @version 1.0
 */
public class Status {
    static final String ACTIVE               = "Active";
    static final String INACTIVE             = "InActive";
    public enum Account {
        INACTIVATED (INACTIVE, 0),
        ACTIVATED (ACTIVE, 1);

        private String label;
        private int value;
        Account(String label, int value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
