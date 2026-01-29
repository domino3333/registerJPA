package com.example.registerspringjpa.repository;

import com.example.registerspringjpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByAge(int age);
    List<Member> findByNameContaining(String keyword);
}
