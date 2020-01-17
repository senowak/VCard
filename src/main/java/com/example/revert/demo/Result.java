package com.example.revert.demo;

public class Result {
    private String fullName;
    private String title;
    private String organizationUnit;


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getFullName() {
        return fullName;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setOrganizationUnit(String organizationUnit) {
        this.organizationUnit = organizationUnit;
    }
}
