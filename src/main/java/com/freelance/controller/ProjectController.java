package com.freelance.controller;

import com.freelance.dto.project.ProjectMetaDto;
import com.freelance.dto.project.ProjectDto;
import com.freelance.dto.project.ProjectMetaWithIdDto;
import com.freelance.exception.InvalidOperationException;
import com.freelance.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.freelance.Constants.*;

@AllArgsConstructor
@RestController
@RequestMapping(PROJECT_API)
public class ProjectController {

    private ProjectService projectService;

    @PostMapping(CREATE_PROJECT_API)
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectMetaDto projectMetaDto) {
        ProjectDto savedProject = projectService.createProject(projectMetaDto);
        return new ResponseEntity<ProjectDto>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping(GET_PROJECT_API)
    public ResponseEntity<ProjectDto> getProjectById(@RequestParam Long projectId) {
        ProjectDto projectDto = projectService.getProjectById(projectId);
        return ResponseEntity.ok(projectDto);
    }

    @GetMapping(GET_PROJECTS_BY_SELLER_API)
    public ResponseEntity<Page<ProjectDto>> getProjectsBySellerId(@RequestParam Long sellerId,
                                                                  @RequestParam Integer pageNo,
                                                                  @RequestParam Integer pageSize) {
        Page<ProjectDto> projectsDtoList = projectService.getProjectsBySellerId(sellerId, pageNo, pageSize);
        return ResponseEntity.ok(projectsDtoList);
    }

    @GetMapping(RECENT_PROJECTS_API)
    public ResponseEntity<Page<ProjectDto>> getRecentProjects(@RequestParam Integer pageNo,
                                                              @RequestParam Integer pageSize) {
        Page<ProjectDto> projectsDtoPage = projectService.getRecentProjects(pageNo, pageSize);
        return ResponseEntity.ok(projectsDtoPage);
    }

    @PutMapping(UPDATE_PROJECTS_API)
    public ResponseEntity<ProjectDto> updateProjectDetails(@RequestBody ProjectMetaWithIdDto newProjectMetaWithIdDto) throws InvalidOperationException {
        ProjectDto projectDto = projectService.updateProject(newProjectMetaWithIdDto);
        return ResponseEntity.ok(projectDto);
    }

    @DeleteMapping(DELETE_PROJECT_API)
    public ResponseEntity<String> deleteProject(@RequestParam Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok("Project with ID : " + projectId + " has been deleted.");
    }
}
