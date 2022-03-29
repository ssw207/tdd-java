package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    private ObjectMapper om = new ObjectMapper();

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberController memberController;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @DisplayName("사용자 저장")
    @Test
    public void test1() throws Exception {
        //given
        Member member = getMember();
        doReturn(member).when(memberRepository).save(any());

        //when
        mockMvc.perform(post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(getMember())))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
                //.andDo(print()); //출력하는경우
    }

    @DisplayName("id로 사용자 조회")
    @Test
    public void test2() throws Exception {
        //given
        Member member = getMember();
        doReturn(Optional.of(member)).when(memberRepository).findById(anyString());

        mockMvc.perform(get("/member/id")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is("id")))
                .andExpect(jsonPath("$.userName", is("name")))
                .andExpect(jsonPath("$.password", is("pw")));
    }

    @DisplayName("사용자 목록조회")
    @Test
    public void test3() throws Exception {
        //given
        Member member = getMember();
        List<Member> list = new ArrayList<>();
        list.add(member);

        doReturn(list).when(memberRepository).findAll();

        //when
        mockMvc.perform(get("/member")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(list.size()))
                .andExpect(jsonPath("$[0].userId").value(member.getUserId()));
    }

    //=============================================================================================
    private Member getMember() {
        Member member = new Member("id", "name", "pw");
        return member;
    }
}