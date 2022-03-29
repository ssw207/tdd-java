package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordValidator passwordValidator;

    @Override
    public Member save(Member member) {
        if (!passwordValidator.isValid(member.getPassword())) {
            throw new IllegalArgumentException("유효하지 않은 비밀번호");
        }

        return memberRepository.save(member);
    }

    @Override
    public Member findById(String userId) {
        return memberRepository.findById(userId).orElseThrow();
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
