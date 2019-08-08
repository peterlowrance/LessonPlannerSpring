package com.example.dto.mapper;

import com.example.dto.SectionDto;
import com.example.models.Section;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    SectionMapper MAPPER = Mappers.getMapper(SectionMapper.class);

    Section toSection(SectionDto sectionDto);

    @InheritInverseConfiguration
    SectionDto fromSection(Section section);
}