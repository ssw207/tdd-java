package com.example.demo.service;

import com.example.demo.domain.Member;

import java.util.List;

public interface MemberService {
    public Member save(Member member);
    public Member findById(String userId);
    public List<Member> findAll();
}
