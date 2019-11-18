package com.xxx.plat.base.dto;


import com.xxx.plat.base.util.UuidUtil;

import java.io.Serializable;

public abstract class BaseDTO implements Serializable {
    private static final long serialVersionUID = -4873814384088149839L;
    private String traceID = UuidUtil.getLongUUID();
    private long timestamp = System.currentTimeMillis();
    private String language = "zh";

    protected BaseDTO() {
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTraceID() {
        return this.traceID;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
