package com.example.repository;

import com.example.models.Project;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {

    @Override
    List<Project> findAll();

    Optional<Project> findByName(String name);

    /*@Modifying
    @Transactional
    @Query("update project p set p.date = :date where p.projectID = :id")
    int updateDate(@Param("id") Integer id, @Param("date") Date date);*/
}
