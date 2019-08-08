package com.example.repository;

import com.example.models.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface SectionRepository extends CrudRepository<Section, Integer> {

    @Override
    List<Section> findAll();

    Optional<Section> findByName(String name);
}
