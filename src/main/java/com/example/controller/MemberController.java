package com.example.controller;

import com.example.models.Member;
import com.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/project/{id}/members")
    @ResponseBody
    List<Member> findProjectMembers(@PathVariable("id") int id) {
        return memberService.findProjectMembers(id);
    }

    @PostMapping("/project/{id}/members")
    @ResponseBody
    Member createMember(@PathVariable("id") int id, @RequestBody Member member){
        return memberService.create(id, member);
    }

    @DeleteMapping("/project/{id}/members/{memberID}")
    @ResponseBody
    public void deleteProject(@PathVariable("id") int id, @PathVariable("memberID") int memberID) {
        memberService.delete(memberID);
    }
}
