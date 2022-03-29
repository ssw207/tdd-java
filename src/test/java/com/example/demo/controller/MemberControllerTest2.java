package com.example.demo.controller;

import org.springframework.boot.test.context.SpringBootTest;

//랜덤포트의 스프링 컨테이너를 띄운다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest2 {

    //@Mock


//    String reqBody = "{\"id\": \"id\", \"pw\": \"123\", \"email\": \"a@a.com\" }";
//    RequestEntity<String> request =
//            RequestEntity.post(URI.create("/users"))
//                    .contentType(MediaType.APPLICATION_JSON_UTF8)
//                    .body(reqBody);
//
//    ResponseEntity<String> response = restTemplate.exchange(
//            request,
//            String.class);
//
//    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//    assertTrue(response.getBody().contains("WeakPasswordException"));

}