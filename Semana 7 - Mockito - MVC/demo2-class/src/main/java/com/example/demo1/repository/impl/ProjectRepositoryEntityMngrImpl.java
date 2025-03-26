package com.example.demo1.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo1.model.Project;
import com.example.demo1.repository.IProjectRepositoryEntityMngr;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ProjectRepositoryEntityMngrImpl implements IProjectRepositoryEntityMngr{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Project save(Project project) {
        entityManager.persist(project);
        return project;
    }

    @Override
    @Transactional
    public Optional<Project> findById(Long id) {
        Project project = entityManager.find(Project.class, id);
        // Crea un Optional<Project> con el valor presente de project, si project es distinto de null,
        // de lo contrario crea un Optional<Project> vacío.
        return Optional.ofNullable(project);
    }

    @Override
    public List<Project> findAll() {
        return entityManager.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }
    
}
