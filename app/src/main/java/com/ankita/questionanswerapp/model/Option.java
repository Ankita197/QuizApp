package com.ankita.questionanswerapp.model;

import java.util.ArrayList;

public class Option {

    String optNumber;
    String optValue;
    int pos=-1;

    public Option(String optNumber, String optValue) {
        this.optNumber = optNumber;
        this.optValue = optValue;
    }

    public String getOptNumber() {
        return optNumber;
    }

    public void setOptNumber(String optNumber) {
        this.optNumber = optNumber;
    }

    public String getOptValue() {
        return optValue;
    }

    public void setOptValue(String optValue) {
        this.optValue = optValue;
    }


    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }


}
