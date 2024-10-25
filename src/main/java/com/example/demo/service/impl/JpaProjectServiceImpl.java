package com.example.demo.service.impl;

import com.example.demo.model.Project;
import com.example.demo.repository.JpaProjectRepository;
import com.example.demo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Qualifier(value = "PostgresProjectService")
public class JpaProjectServiceImpl implements ProjectService {
    private final JpaProjectRepository jpaProjectRepository;

    @Override
    public Project addProject(Project Project) {
        return jpaProjectRepository.save(Project);
    }

    @Override
    public List<Project> getAllProjects() {
        return jpaProjectRepository.findAll();
    }

    @Override
    public Project findById(Integer id) {
        return jpaProjectRepository.findById(id).get();
    }

    // Assignment
//    @Override
//    public Project updateProject(Integer id, Project Project) {
//        Project projectToUpdate = jpaProjectRepository.findById(id).get();
//        projectToUpdate.setName();
//        projectToUpdate.setDescription();
//        projectToUpdate.setTime();
//        return jpaProjectRepository.save(Project);
//    }

    @Override
    public Boolean deleteById(Integer id) {
        jpaProjectRepository.deleteById(id);
        return Boolean.TRUE;
    }
}