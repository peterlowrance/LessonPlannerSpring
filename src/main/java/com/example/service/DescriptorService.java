package com.example.service;

import com.example.models.Descriptor;
import com.example.repository.DescriptorRepository;
import com.example.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriptorService {

    @Autowired
    DescriptorRepository descriptorRepository;

    @Autowired
    ProjectRepository projectRepository;

    public List<Descriptor> findProjectDescriptors(int id) {
        return descriptorRepository.findAllByProject_ProjectID(id);
    }

    public Descriptor create(int projectID, Descriptor descriptor) {
        descriptor.setProject(projectRepository.findById(projectID).orElse(null));
        return descriptorRepository.save(descriptor);
    }

    public void delete(int id){
        descriptorRepository.deleteById(id);
    }

}
