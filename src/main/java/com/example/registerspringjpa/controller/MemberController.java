package com.example.registerspringjpa.controller;

import com.example.registerspringjpa.domain.Member;
import com.example.registerspringjpa.domain.Search;
import com.example.registerspringjpa.service.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberServiceImpl memberService;

    @GetMapping("/main")
    public String main(){

        return "member/main";
    }


    @GetMapping("/joinForm")
    public String joinForm(){

        return "member/joinForm";
    }

    @PostMapping("/postInfo")
    public String postInfo(Member member){
        //todo db에 넣기
        try {
            int count = memberService.register(member);
            if(count > 0){
                return "member/registerSuccess";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "member/registerSuccess";
    }
    @GetMapping("/memberList")
    public String memberList(Model model){
        //todo db에서 받아와 모델로 넘겨주기
        List<Member> memberList;
        try {
            memberList = memberService.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("memberList",memberList);

        return "member/memberList";
    }

    @GetMapping("/{no}")
    public String memberDetail(@PathVariable long no, Model model){
        Member temp = new Member();
        temp.setNo(no);
        Member member;
        try {
            member = memberService.read(temp);
            if(member==null){
                return "redirect:/member/memberList";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("member",member);

        return "member/memberDetail";
    }

    @GetMapping("/edit/{no}")
    public String edit(@PathVariable int no,Model model){
        Member member = null;
        Member temp = new Member();
        temp.setNo(no);
        try {
            member = memberService.read(temp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("member",member);
        return "member/memberEdit";
    }

    @PostMapping("/editPost")
    public String editPost(Member member){
        //todo db에 update
        try {
            int count = memberService.modify(member);
            if(count>0){
                //수정 성공
                return "member/main";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        log.info("수정 실패");
        return "member/main";
    }

    @PostMapping("/delete/{no}")
    public String delete(@PathVariable int no){
        //todo no를 넘겨서 삭제하기
        Member member = new Member();
        member.setNo(no);
        try {
            int count = memberService.remove(member);
            if(count>0){
                log.info("삭제 성공");
                return "member/main";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        log.info("삭제 실패");

        return "member/main";
    }

    @GetMapping("/search")
    public String search(Model model,String type, String keyword){
        List<Member> memberList = memberService.search(type,keyword);


        model.addAttribute("memberList",memberList);
        return "member/memberList";
    }



}
