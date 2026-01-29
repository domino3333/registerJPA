package com.example.registerspringjpa.service;


import com.example.registerspringjpa.domain.Member;
import com.example.registerspringjpa.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;


    @Override
    @Transactional
    public int register(Member m) throws Exception {

        Member member = memberRepository.save(m);
        return member != null ? 1 : 0;
    }

    @Override
    public Member read(Member member) throws Exception {

        return memberRepository.getReferenceById(member.getNo());
    }

    @Override
    @Transactional
    public int modify(Member m) throws Exception {
        Member member = memberRepository.getReferenceById(m.getNo());
        member.setName(m.getName());
        member.setPassword(m.getPassword());
        member.setAge(m.getAge());
        return member != null? 1:0;
    }

    @Override
    @Transactional
    public int remove(Member member) throws Exception {
        int count = 1;
        try{
        memberRepository.deleteById(member.getNo());
        }catch (Exception e){
            log.info(e.toString());
            count =0;
        }
        return count;
    }

    @Override
    public List<Member> list() throws Exception {

        return memberRepository.findAll(Sort.by(Sort.Direction.DESC,"no"));
    }

    @Override
    public List<Member> search(String type, String keyword) {

        if(type.equals("age")){
            return memberRepository.findByAge(Integer.parseInt(keyword));
        }else{
            return memberRepository.findByNameContaining(keyword);
        }



    }
}
