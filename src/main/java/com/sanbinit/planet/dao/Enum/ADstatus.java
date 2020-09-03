package com.sanbinit.planet.dao.Enum;

public enum ADstatus {

    Normal("正常", 0), Freeze("冻结", 1);

    private String name;
    private int index;

    ADstatus(String name, int index){
        this.index = index;
        this.name = name;
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
