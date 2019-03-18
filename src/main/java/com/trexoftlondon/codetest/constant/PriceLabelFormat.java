package com.trexoftlondon.codetest.constant;

public enum PriceLabelFormat {
    ShowWasNow("ShowWasNow"),
    ShowWasThenNow("ShowWasThenNow"),
    ShowPercDscount("ShowPercDscount");

    private String name;

    PriceLabelFormat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
