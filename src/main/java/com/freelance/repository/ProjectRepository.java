package com.freelance.repository;

import com.freelance.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> getProjectByOrderByDateOfPostingDesc(Pageable pageable);
    Page<Project> getProjectBySellerId(Long sellerId, Pageable pageable);
}
