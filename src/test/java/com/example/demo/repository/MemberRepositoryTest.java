package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @DisplayName("회원정보가 저장되고 조회된다.")
    @Test
    public void test1() throws Exception {
        //givne
        Member member = new Member("id", "name","pw");

        //when
        Member save = memberRepository.save(member);
        Member find = memberRepository.findById(save.getUserId()).orElseThrow();

        //then
        assertThat(find.getUserId()).isEqualTo(save.getUserId());
        assertThat(find.getUserName()).isEqualTo(save.getUserName());
    }
}