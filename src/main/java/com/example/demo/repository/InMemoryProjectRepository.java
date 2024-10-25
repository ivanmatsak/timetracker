package com.example.demo.repository;

import com.example.demo.model.Project;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//Репозиторий для тестирования работы в докере (без бд)
@Repository
public class InMemoryProjectRepository {
    private static final List<Project> DATABASE = new ArrayList<>();

    static {
        DATABASE.add(new Project(1, "initial", "text", "2024-10-25 18:36:59"));
        DATABASE.add(new Project(2, "second", "text", "2024-10-25 18:36:59"));

    }

    public Project addProject(Project project){
        DATABASE.add(project);
        return project;
    }

    public List<Project> getAllProjects(){
        return List.copyOf(DATABASE);
    }

    public Project findById(Integer id){
        return DATABASE.stream().filter(element -> id.equals(element.getId())).findFirst().orElseThrow();
    }

    public Boolean deleteProject(Integer id){
        Project project = DATABASE.stream().filter(element -> id.equals(element.getId())).findFirst().orElseThrow();
        DATABASE.remove(project);
        return true;
    }
}
