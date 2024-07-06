package com.authorizer.service.domain.mcc;

public class Mcc {
    private Integer id;
    private String mccCode;
    private String description;

    private Mcc(Integer id, String mccCode, String description){
        this.id = id;
        this.mccCode = mccCode;
        this.description = description;
    }

    public static Mcc with(Integer id, String mccCode, String description) {
        return new Mcc(id, mccCode, description);
    }

    public Integer getId() {
        return id;
    }

    public String getMccCode() {
        return mccCode;
    }

    public String getDescription() {
        return description;
    }
}
