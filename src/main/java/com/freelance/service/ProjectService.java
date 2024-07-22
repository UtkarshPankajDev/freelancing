package com.freelance.service;

import com.freelance.dto.project.ProjectMetaDto;
import com.freelance.dto.project.ProjectDto;
import com.freelance.dto.project.ProjectMetaWithIdDto;
import com.freelance.exception.InvalidOperationException;
import org.springframework.data.domain.Page;

public interface ProjectService {
    ProjectDto createProject(ProjectMetaDto projectMetaDto);
    ProjectDto getProjectById(Long projectId);
    Page<ProjectDto> getProjectsBySellerId(Long sellerId, Integer pageNo, Integer pageSize);
    Page<ProjectDto> getRecentProjects(Integer pageNo, Integer pageSize);
    ProjectDto updateProject(ProjectMetaWithIdDto projectMetaWithIdDto) throws InvalidOperationException;
    void deleteProject(Long projectId);
}
