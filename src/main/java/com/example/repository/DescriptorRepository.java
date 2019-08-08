package com.example.repository;

import com.example.models.Descriptor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DescriptorRepository extends CrudRepository<Descriptor, Integer> {

    @Override
    List<Descriptor> findAll();

    List<Descriptor> findAllByProject_ProjectID(int id);
}
