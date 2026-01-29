package com.example.registerspringjpa.service;



import com.example.registerspringjpa.domain.Member;

import java.util.List;

interface MemberService {
    int register(Member member) throws Exception;

    Member read(Member member) throws Exception;

    int modify(Member member) throws Exception;

    int remove(Member member) throws Exception;

    List<Member> list() throws Exception;

    List<Member> search(String type, String keyword) throws Exception;
}
