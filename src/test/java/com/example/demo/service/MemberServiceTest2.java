package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // 테스트 클래스가 Mockito를 사용함
class MemberServiceTest2 {

    @Mock // mock 객체 선언
    private MemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach // @BeforeAll로 메소드를 static로 바꾸면 mock 객체가 주입되지 않음 원인확인필요
    @Test
    void setUp() throws Exception {
        memberService = new MemberServiceImpl(memberRepository, new MockValidator());
    }

    @DisplayName("사용자가 저장되고 조회된다 - Repsitory만 Mock사용")
    @Test
    public void test2() throws Exception {
        //given
        Member member = new Member("id", "name", "123455");

        //Mock 객체 동작선언
        when(memberRepository.save(any())).thenReturn(member); // save 호출시 지정된 객체가 리턴
        when(memberRepository.findById("id")).thenReturn(Optional.of(member)); // findById 호출시 지정된 객체가 리턴

        //when
        Member save = memberService.save(member);
        Member find = memberService.findById(save.getUserId());

        //then
        assertEquals("id", find.getUserId());
        assertEquals("name", find.getUserName());
    }

    static class MockValidator implements PasswordValidator {
        @Override
        public boolean isValid(String password) {
            System.out.println("MockValidator.isValid:" + password);
            return (password.length() > 5);
        }
    };
}