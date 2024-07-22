package com.freelance.service;

import com.freelance.dto.project.ProjectMetaDto;
import com.freelance.dto.project.ProjectDto;
import com.freelance.dto.project.ProjectMetaWithIdDto;
import com.freelance.entity.Project;
import com.freelance.exception.InvalidOperationException;
import com.freelance.exception.ResourceNotFoundException;
import com.freelance.repository.BidRepository;
import com.freelance.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private BidRepository bidRepository;
    private ModelMapper modelMapper;

    @Override
    public ProjectDto createProject(ProjectMetaDto projectMetaDto) {
        // TODO : This mapper is setting the id as sellerId
        ProjectDto projectDto = modelMapper.map(projectMetaDto, ProjectDto.class);
        projectDto.setDateOfPosting(Instant.now());
        Project project = modelMapper.map(projectDto, Project.class);
        Project savedProject = projectRepository.save(project);
        return modelMapper.map(savedProject, ProjectDto.class);
    }

    @Override
    public ProjectDto getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project with ID : " + projectId + " not found."));
        ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
        return projectDto;
    }

    @Override
    public Page<ProjectDto> getProjectsBySellerId(Long sellerId, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Project> projectsPage = projectRepository.getProjectBySellerId(sellerId, pageable);
        Page<ProjectDto> projectDtosPage = projectsPage.map(project -> modelMapper.map(project, ProjectDto.class));
        return projectDtosPage;
    }

    @Override
    public Page<ProjectDto> getRecentProjects(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Project> projectsPage = projectRepository.getProjectByOrderByDateOfPostingDesc(pageable);
        Page<ProjectDto> projectDtosPage = projectsPage.map(project -> modelMapper.map(project, ProjectDto.class));
        return projectDtosPage;
    }

    @Override
    public ProjectDto updateProject(ProjectMetaWithIdDto newProjectMetaWithIdDto) throws InvalidOperationException {
        Project oldProject = projectRepository.findById(newProjectMetaWithIdDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project with ID : " + newProjectMetaWithIdDto.getId() + " not found."));

        if (!oldProject.getSellerId().equals(newProjectMetaWithIdDto.getSellerId()))
            throw new InvalidOperationException("Seller details cannot be altered.");
        if (newProjectMetaWithIdDto.getLastDateForBidding().isBefore(Instant.now()))
            throw new InvalidOperationException("Cannot set last date for bidding before current date.");

        oldProject.setName(newProjectMetaWithIdDto.getName());
        oldProject.setDescription(newProjectMetaWithIdDto.getDescription());
        oldProject.setLastDateForBidding(newProjectMetaWithIdDto.getLastDateForBidding());

        Project project = projectRepository.save(oldProject);
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public void deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project with ID : " + projectId + " not found."));

        // TODO : Is the method correctly defined
        bidRepository.deleteByProjectId(projectId);
        projectRepository.deleteById(projectId);
    }
}
