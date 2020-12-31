package com.dskim.javacucumberspring.browsers;

public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox"),
    IE("ie");

    private String browserName;

    Browser(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }
}
