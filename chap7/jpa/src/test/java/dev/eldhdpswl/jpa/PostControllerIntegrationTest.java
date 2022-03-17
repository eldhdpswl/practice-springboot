package dev.eldhdpswl.jpa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.eldhdpswl.jpa.entity.PostEntity;
import dev.eldhdpswl.jpa.repository.PostRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        // @SpringBootTest 전체 웹 애플리케이션을 테스트를 한다.
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = JpaApplication.class
)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase // H2 데이터베이스 사용한다.
public class PostControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Before //테스트를 실행하기 전에 실행되는 함수
    public void setEntities(){
        createTestPost("first post", "first post content", "test_writer");
        createTestPost("second post", "second post content", "test_writer");

    }

    @Test
    void givenValidId_whenReadPost_then200() throws Exception {
        //given
        Long id = createTestPost("read Post", "Created on readPost()","read_test");

        //when
        final ResultActions actions = mockMvc.perform(get("/post/{id}", id))
                .andDo(print());

        //then
        actions.andExpectAll(
                status().isOk(),
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                jsonPath("$.title", is("Read Post")),
                jsonPath("$.content", is("Created on readPost()"))
        );

    }

    private Long createTestPost(String title, String content, String writer){
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(title);
        postEntity.setContent(content);
        postEntity.setWriter(writer);
        return postRepository.save(postEntity).getId();
    }


}
