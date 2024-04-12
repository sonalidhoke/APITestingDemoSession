package com.qa.Data;

public class Users {

    String name;
    String job;

    String id;
    String createdAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users(){

    }
    public  Users( String name, String job, String id, String createdAt){
        this.name=name;
        this.job=job;
        this.id=id;
        this.createdAt=createdAt;

    }


}
