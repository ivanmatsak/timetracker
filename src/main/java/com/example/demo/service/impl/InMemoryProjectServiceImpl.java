package com.example.demo.service.impl;

import com.example.demo.model.Project;
import com.example.demo.repository.InMemoryProjectRepository;
import com.example.demo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Qualifier(value = "inMemory")
public class InMemoryProjectServiceImpl implements ProjectService {
    private final InMemoryProjectRepository inMemoryProjectRepository;

    @Override
    public Project addProject(Project project) {
        return inMemoryProjectRepository.addProject(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return inMemoryProjectRepository.getAllProjects();
    }

    @Override
    public Project findById(Integer id) {
        return inMemoryProjectRepository.findById(id);
    }

    // Assignment
//    @Override
//    public void updateProject(Project project) {
//    }

    @Override
    public Boolean deleteById(Integer id) {
        return inMemoryProjectRepository.deleteProject(id);
    }
}