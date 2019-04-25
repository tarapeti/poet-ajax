package com.codecool.web.model;

public class Poem extends AbstractModel {
    private String title;
    private String context;
    private int userid;

    public Poem(int id, String title, String context, int  userid) {
        super(id);
        this.title = title;
        this.context = context;
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public String getContext() {
        return context;
    }

    public int getUserid() {
        return userid;
    }
}
