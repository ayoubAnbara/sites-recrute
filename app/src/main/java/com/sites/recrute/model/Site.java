package com.sites.recrute.model;

public class Site {
    private long id;
    private String label;
    private String link;
    private boolean isPreferred;

    public Site() {
    }

    public Site(long id,String label, String link) {
        this.id=id;
        this.label = label;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPreferred() {
        return isPreferred;
    }

    public void setPreferred(boolean preferred) {
        isPreferred = preferred;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
