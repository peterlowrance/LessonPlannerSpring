package com.example.peter;

import com.example.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberIntegrationTests {

    @Autowired
    MemberService memberService;

    @Test
    public void testProjectMembersByID(){
        memberService.findProjectMembers(1).forEach(s -> System.out.println(s.getName()));
    }
}
