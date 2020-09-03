package com.sanbinit.planet.dao.Enum;

public enum ADlevel {

    SuperAdmin("超级管理员", 1), CommonAdmin("管理员", 2);

    private String name;
    private int index;

    ADlevel(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
