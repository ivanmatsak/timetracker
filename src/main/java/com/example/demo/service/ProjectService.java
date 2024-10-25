package com.example.demo.service;

import com.example.demo.model.Project;

import java.util.List;

public interface ProjectService {
    Project addProject(Project project);

    List<Project> getAllProjects();

    Project findById(Integer id);

//    Project updateProject(Project project);

    Boolean deleteById(Integer Id);
}
