package com.vd5.tracking.auth;

import lombok.Data;

/**
 * @author beou on 8/21/17 18:38
 * @version 1.0
 */
@Data
public class AclEntry {
    String className;
    String fieldName;
    int accessLevel; //default 3

    public AclEntry() {
    }

    public AclEntry(String grantedAuthority) {
        //className:fieldName:accessLevel
        String[] mAclEntry = grantedAuthority.split(":");
        this.className = mAclEntry[0];
        this.fieldName = mAclEntry[1];
        this.accessLevel = Integer.parseInt(mAclEntry[2]);
    }

    public AclEntry(String className, String fieldName, int accessLevel) {
        this.className = className;
        this.fieldName = fieldName;
        this.accessLevel = accessLevel;
    }

    public Class getClazz() throws ClassNotFoundException {
            return Class.forName(this.className);
    }


}
