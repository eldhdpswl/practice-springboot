package dev.eldhdpswl.jpa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*; // jsonPath("$.-----", is("")) 여기에서 is() 사용하기 위해
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  //mockMvc.perform(get()) 사용하기 위해
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
// @WebMvcTest는 컨트롤러만 bean으로 만들어서 테스르를 진행한다. @SpringBootTest와 반대개념
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;  //Http 클라이언트인 척한다.

    @MockBean
    private PostService postService; //실제로 존재하는 객체가 아니어서 given을 통해 기능을 부여받아야된다.

    @Test
    void readPost() throws Exception{
        // given 어떤 데이터가 준비가 되어있다.
        // PostEntity가 존재할떄 (PostService가 PostEntity를 잘돌려줄때)
        final int id = 10;
        PostDto testDto = new PostDto();
        testDto.setId(id);
        testDto.setTitle("Unit Title");
        testDto.setContent("Unit Content");
        testDto.setWriter("unit");

        // willReturn은 반환값
        given(postService.readPost(id)).willReturn(testDto);

        // when 어떤 행위가 일어났을떄 (함수 호출 등)
        // 경로에 GET 요청이 오면
        final ResultActions actions = mockMvc.perform(get("/post/{id}", id))
                .andDo(print());

        // then 어떤 결과가 올것인지
        // PostDto가 반환된다
        // acions에 담긴 내용을 호출하여 확인한다.
        actions.andExpectAll(
                status().isOk(), //status 코드가 200인지 아닌지 확인
                content().contentType(MediaType.APPLICATION_JSON),  //contentType이 무엇인지
                jsonPath("$.title", is("Unit Title")), //jsonPath는 돌아온 값이 json인지 확인하는 용도, 내용물 확인
                jsonPath("$.content", is("Unit Content")),  //$ 은 문서 하나 자체를 나타낸다
                jsonPath("$.writer", is("unit"))
        );

    }

    @Test
    void readPostAll() throws Exception{
        //given
        PostDto post1 = new PostDto();
        post1.setTitle("title 1");
        post1.setContent("test");
        post1.setWriter("test");

        PostDto post2 = new PostDto();
        post2.setTitle("title 2");
        post2.setContent("test2");
        post2.setWriter("test2");

        List<PostDto> readAllPost = Arrays.asList(post1, post2);
        given(postService.readPostAll()).willReturn(readAllPost);

        // when
        final ResultActions actions = mockMvc.perform(get("/post"))
                .andDo(print());

        // then
        actions.andExpectAll(
                status().isOk(),
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                jsonPath("$", hasSize(readAllPost.size())) // mock에서 호출한 사이즈와 같은 크기를 호출
        );


    }
}