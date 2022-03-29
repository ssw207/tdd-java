package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/member")
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @GetMapping("/member/{id}")
    public Member findById(@PathVariable String id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id"));
    }

    @PostMapping("/member")
    public void save(Member member) {
        memberRepository.save(member);
    }
}
