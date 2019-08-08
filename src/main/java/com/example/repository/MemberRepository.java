package com.example.repository;

import com.example.models.Member;
import com.example.models.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {

    @Override
    List<Member> findAll();

    Optional<Member> findByName(String name);

    List<Member> findAllByProject_ProjectID(int id);

}
