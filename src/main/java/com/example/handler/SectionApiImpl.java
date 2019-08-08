package com.example.handler;

import com.example.SectionsApi;
import com.example.dto.SectionListDto;
import com.example.dto.mapper.SectionMapper;
import com.example.project.ProjectDataController;
import com.example.project.ProjectResponseEntity;
import com.example.service.SectionService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ProjectDataController
public class SectionApiImpl implements SectionsApi {

    private final SectionService sectionService;
    private final SectionMapper sectionMapper;


    @Override
    public ProjectResponseEntity<SectionListDto> getAllSections() {
        return null;
    }
}
