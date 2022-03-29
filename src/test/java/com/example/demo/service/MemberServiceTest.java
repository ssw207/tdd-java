package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // 테스트 클래스가 Mockito를 사용함
class MemberServiceTest {

    @Mock // mock 객체 선언
    private MemberRepository memberRepository;

    @Mock
    private PasswordValidator passwordValidator;

    /**
     * 테스트 런타임시 MemberService내부의 맴버변수중 @Mock 타입의 변수가 있다면 @Mock에 선언된 객체가 대신 주입된다.
     * 인터페이스가 아니라 구현체로 선언해야함
     */
    @InjectMocks
    private MemberServiceImpl memberService;

    @DisplayName("사용자가 저장되고 조회된다 - Mock 사용")
    @Test
    public void test1() throws Exception {
        //given
        Member member = new Member("id", "name", "pw");

        //Mock 객체 동작선언
        when(memberRepository.save(any())).thenReturn(member); // save 호출시 지정된 객체가 리턴
        when(memberRepository.findById("id")).thenReturn(Optional.of(member)); // findById 호출시 지정된 객체가 리턴
        when(passwordValidator.isValid("pw")).thenReturn(true); // password 검증시 pw 값이 들어오면 ture 리턴

        //when
        Member save = memberService.save(member);
        Member find = memberService.findById(save.getUserId());

        //then
        assertEquals("id", find.getUserId());
        assertEquals("name", find.getUserName());
    }
}