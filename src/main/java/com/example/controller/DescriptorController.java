package com.example.controller;

import com.example.models.Descriptor;
import com.example.service.DescriptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class DescriptorController {

    @Autowired
    DescriptorService descriptorService;

    @GetMapping("/project/{id}/descriptors")
    @ResponseBody
    List<Descriptor> findProjectDescriptors(@PathVariable("id") int id) {
        return descriptorService.findProjectDescriptors(id);
    }

    @PostMapping("/project/{id}/descriptors")
    @ResponseBody
    Descriptor createDescriptor(@PathVariable("id") int id, @RequestBody Descriptor descriptor){
        return descriptorService.create(id, descriptor);
    }

    @PutMapping("/project/{id}/descriptors")
    @ResponseBody
    Descriptor updateDescriptor(@PathVariable("id") int id, @RequestBody Descriptor descriptor){
        return descriptorService.create(id, descriptor);
    }

    @DeleteMapping("/project/{id}/descriptors/{descriptorID}")
    @ResponseBody
    public void deleteDescriptor(@PathVariable("id") int id, @PathVariable("descriptorID") int descriptorID) {
        descriptorService.delete(descriptorID);
    }
}
