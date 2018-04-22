package com.aws.codestar.projecttemplates.model;

public class SearchCriteria{

    private String criteria;

    public SearchCriteria() {
    }

    public SearchCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria(){
        return criteria;
    } 

    public void setCriteria(String criteria){
        this.criteria = criteria;
    }

}

