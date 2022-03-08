package dev.eldhdpswl.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    /*
        Service는 비즈니스 로직
        Repository는 data access 기능
        controller는 front geteway 용도

        Entity는 데이터의 표현만 나타내는 것인데, 실제 데이터베이스는 아니다.
        하지만 내부 객제는 존재한다. 즉 데이터를 자체만 전송하는 것 이상으로 전달이 되는 데이터가 많다는게 약점이다.
        그래서 Entity를 Dto로 사용하는 것은 좋은 방법이 아니다.
        그래서 Dto를 새로 만든다. PostDto, BoardDto 등등
    */

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(
            @Autowired PostService postService
    ){
        this.postService = postService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostDto dto){
        this.postService.createPost(dto);
    }

    @GetMapping("{id}")
    public PostDto readPost(
            @PathVariable("id") int id
    ){
        return this.postService.readPost(id);
    }

    @GetMapping("")
    public List<PostDto> readPostAll(){
        return this.postService.readPostAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePost(
            @PathVariable("id") int id,
            @RequestBody PostDto dto
    ){
        this.postService.updatePost(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(
            @PathVariable("id") int id
    ){
        this.postService.deletePost(id);
    }





}
