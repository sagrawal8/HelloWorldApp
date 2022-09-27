package com.example.numad22fa_shashankagrawal;

public class Url {
    private final String name;

    private final String link;

    /**
     * Constructs a person object with the specified name and age.
     *
     * @param name - name of URL
     * @param link -  link of URL
     */
    public Url(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
}
