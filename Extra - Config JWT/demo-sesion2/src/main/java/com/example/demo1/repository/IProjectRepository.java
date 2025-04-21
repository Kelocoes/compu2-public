package com.example.demo1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Project;
import java.time.LocalDate;


@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByNameEquals(String name);

    Optional<Project> findByName(String name);

    List<Project> findByNameContaining(String name);

    List<Project> findByNameStartingWith(String name);

    List<Project> findByNameEndingWith(String name);

    List<Project> findByNameIgnoreCase(String name);

    List<Project> findByNameIsNot (String name);

    List<Project> findByNameLike(String name);

    List<Project> findByDateCreatedGreaterThan(LocalDate dateCreated);

    List<Project> findByDateCreatedLessThan(LocalDate dateCreated);

    List<Project> findByDateCreatedLessThanEqual(LocalDate dateCreated);

    @Query("SELECT count(*), year(p.dateCreated) from Project p group by year(p.dateCreated)")
    List<List<Integer>> countByYearCreated();

    @Query(value = "SELECT * FROM project WHERE name = ?1", nativeQuery = true)
    Optional<Project> findByNameNative(String name);

    List<Project> findByIdIn(List<Long> ids);

    @Query("SELECT p FROM Project p WHERE p.name = :name")
    List<Project> findByNameParam(@Param("name") String name);

    void deleteById(Long id);
}
