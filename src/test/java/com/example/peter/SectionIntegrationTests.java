package com.example.peter;

import com.example.models.Project;
import com.example.models.Section;
import com.example.service.DescriptorService;
import com.example.service.ProjectService;
import com.example.service.SectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SectionIntegrationTests {

    @Autowired
    SectionService sectionService;

    @Autowired
    ProjectService projectService;

    @Autowired
    DescriptorService descriptorService;

    @Test
    public void testInit() {
        assertNotNull(sectionService);
        assertNotNull(projectService);
    }

    @Test
    public void testServiceFindAll() {
        sectionService.findAll().forEach(s -> assertNotNull(s));
    }

    @Test
    public void testServiceFindByID() {
        Section s = new Section();
        s.setSectionID(6);
        s.setName("testFindByID");
        sectionService.update(s);
        assertEquals(s, sectionService.findByID(6));
    }

    @Test
    public void testServiceFindByIDFail() {
        assertNull(sectionService.findByID(10234));
    }

    @Test
    public void testServiceUpdate() {
        Section s = new Section();
        s.setSectionID(5);
        s.setName("test");
        sectionService.update(s);
        assertTrue(sectionService.findAll().contains(s));
    }

    @Test
    public void testServiceUpdateOverride() {
        Section s1 = new Section();
        s1.setName("testOne");
        s1.setSectionID(4);
        sectionService.update(s1);
        assertTrue(sectionService.findAll().contains(s1));

        Section s2 = new Section();
        s2.setSectionID(s1.getSectionID());
        s2.setName("testTwo");
        sectionService.update(s2);
        assertTrue(sectionService.findAll().contains(s2));
    }

    @Test
    public void testServiceCreate() {
        sectionService.create("createOne");
        sectionService.create("createTwo");
        boolean firstExists = false;
        boolean secondExists = false;
        for (Section s : sectionService.findAll()) {
            if (s.getName().equals("createOne")) {
                firstExists = true;
            } else if (s.getName().equals("createTwo")) {
                secondExists = true;
            }
        }
        assertTrue(firstExists && secondExists);
    }

    @Test
    public void testServiceFindProjectBySection(){
        Section s = sectionService.create("hasProjects");
        Project p1 = new Project();
        p1.setName("testProj1");
        p1.setProjectID(2193);
        Project p2 = new Project();
        p2.setName("testProj2");
        p2.setProjectID(9421);
        p2.setSection(s);
        projectService.addProject(s, p1);
        projectService.addProject(p2);
        assertTrue(sectionService.findProjectsBySection("hasProjects").contains(p1));
        assertTrue(sectionService.findProjectsBySection("hasProjects").contains(p2));
    }


    // PROJECT tests

    @Test
    public void testProjectFindAll(){
        projectService.findAll().forEach(p -> assertNotNull(p));
    }

    @Test
    public void testProjectFindByName(){
        Project p = new Project();
        p.setName("testName");
        p.setProjectID(123);
        p.setSection(sectionService.findAll().stream().findFirst().get());
        projectService.addProject(p);

        assertEquals(p, projectService.findByName("testName"));
    }

    @Test
    public void testProjectDescriptorsByID(){
        descriptorService.findProjectDescriptors(1).forEach(s -> System.out.println(s.getText()));
    }

    @Test
    public void testProjectCreateNew(){
        Project p = new Project();
        p.setName("testName");
        p.setProjectID(342879);
        projectService.addProject(1, p);
        assertEquals(p, projectService.findByID(p.getProjectID()).get());
    }
}
