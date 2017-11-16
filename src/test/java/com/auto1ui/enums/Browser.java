package com.auto1ui.enums;

public enum Browser {

    CHROME("Chrome"),
    IE("Internet Explorer");

    private String name;

    @Override
    public  String toString(){
    return name;
}

    Browser(final String name)
    {
        this.name = name;
    }
}
