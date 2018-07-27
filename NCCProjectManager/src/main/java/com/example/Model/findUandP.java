package com.example.Model;

import com.example.Entity.Project;

import java.util.List;

public class findUandP {
    private List<UserDTO> user;
    private  List<Project> project;

    public findUandP() {

    }

    public List<UserDTO> getUser() {
        return user;
    }

    public void setUser(List<UserDTO> user) {
        this.user = user;
    }

    public List<Project> getProject() {
        return project;
    }

    public void setProject(List<Project> project) {
        this.project = project;
    }
}
