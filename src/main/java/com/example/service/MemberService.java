package com.example.service;

import com.example.models.Member;
import com.example.repository.MemberRepository;
import com.example.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProjectRepository projectRepository;

    public Member addMember(Member m) {
        return memberRepository.save(m);
    }

    public Member create(int projectID, Member member) {
        member.setProject(projectRepository.findById(projectID).orElse(null));
        return memberRepository.save(member);
    }

    public void delete(int id){
        memberRepository.deleteById(id);
    }

    public List<Member> findProjectMembers(int id) {
        return memberRepository.findAllByProject_ProjectID(id);
    }
}
