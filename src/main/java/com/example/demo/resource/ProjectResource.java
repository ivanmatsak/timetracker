package com.example.demo.resource;

import com.example.demo.model.Project;
import com.example.demo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

//CRUD контроллер проектов.
@RestController
//@RequiredArgsConstructor
@RequestMapping(path = "/api/projects")
public class ProjectResource {
    private final ProjectService projectService;

    public ProjectResource(@Qualifier(value = "PostgresProjectService")ProjectService projectService) {
        this.projectService = projectService;
    }

    //Получить все проекты
    @GetMapping
    public ResponseEntity<List<Project>> getProjects () {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    //Получить проект по if
    @GetMapping("{id}")
    public ResponseEntity<Project> getProject (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    //Добавить проект
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Project> addProject (@RequestBody Project project) {
        return ResponseEntity.created(getLocation(project.getId())).body(projectService.addProject(project));
    }
    //Удалить проект
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteProject (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(projectService.deleteById(id));
    }

    //Начать отсчет затраченного времени в проектк
    @PostMapping("/startTime/{id}")
    public ResponseEntity<Project> startProjectTime(@PathVariable("id") Integer id) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String str = currentDateTime.format(formatter);
        Project project = projectService.findById(id);

        project.setTime(str);

        Project updatedProject = projectService.addProject(project);
        return ResponseEntity.ok(updatedProject);
    }

    //Закончить отсчет времени
    @PostMapping("/stopTime/{id}")
    public ResponseEntity<String> stopProjectTime(@PathVariable("id") Integer id) {
        //Получаем проект по id, отнимаем от сохраненной даты текущую
        Project project = projectService.findById(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(project.getTime(), formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();

        //Подсчитываем разницу
        long years = ChronoUnit.YEARS.between(startTime, currentDateTime);
        currentDateTime = currentDateTime.minusYears(years);
        long months = ChronoUnit.MONTHS.between(startTime, currentDateTime );
        currentDateTime = currentDateTime.minusMonths(months);
        long days = ChronoUnit.DAYS.between(startTime, currentDateTime);
        currentDateTime = currentDateTime.minusDays(days);
        long hours = ChronoUnit.HOURS.between(startTime, currentDateTime);
        currentDateTime = currentDateTime.minusHours(hours);
        long minutes = ChronoUnit.MINUTES.between(startTime, currentDateTime);
        currentDateTime = currentDateTime.minusMinutes(minutes);
        long seconds = ChronoUnit.SECONDS.between(startTime, currentDateTime);


        return ResponseEntity.ok(String.format("Time stopped. Time spent: years: %d, months: %d, days: %d, hours: %d, minutes: %d, seconds: %d.",
                years, months, days, hours, minutes, seconds));
    }

    protected static URI getLocation(Integer id) {
        return fromCurrentRequest().path("{id}").buildAndExpand(id).toUri();
    }
}