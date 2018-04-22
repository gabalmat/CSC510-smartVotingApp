package com.aws.codestar.projecttemplates.model;

public class SimplePoll{

    private String title;

    private int id;

    public SimplePoll() {
    }

    public SimplePoll(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public int getID(){
        return id;
    }

    public void setTitle(String title){
        this.title =  title;
    }

    public void setID(int id){
        this.id =  id;
    }

}