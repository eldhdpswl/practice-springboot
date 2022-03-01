package dev.eldhdpswl.crud.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//@Deprecated - 이게 붙으면 이 클래스는 더이상 사용하지 않을 예정이다.
@RestController
@RequestMapping("post")
public class PostRestController {
    private static final Logger logger = LoggerFactory.getLogger(PostRestController.class);
    //private final List<PostDto> postList;
    private final PostService postService;

    public PostRestController(
            @Autowired PostService postService
    ) {
        //this.postList = new ArrayList<>();
        this.postService = postService;
    }


    //http://localhost:8080/post
    //POST /post
    //REQUEST_BODY

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //@ResponseStatus는 client의 요청이 어떤식으로 처리됬는지 확인하기위해 사용
    // HttpServletRequest를 사용하면 앞쪽에 붙어있던 임베디드, 톰켓이나 다른 구현체에서 만든 요청이 여기까지 들어온다.
    public void createPost(@RequestBody PostDto postDto, HttpServletRequest request){
        logger.info(postDto.toString());
        //request.getHeader("Content-Type");
        this.postService.createPost(postDto);
    }


    //http://localhost:8080/post
    //GET /post
    @GetMapping()
    public List<PostDto> readPostAll(){
        logger.info("in read all");
        return this.postService.readPostAll();
    }


    // GET /post/0/
    // GET /post?id=0
    @GetMapping("{id}")
    public PostDto readPost(@PathVariable("id") int id){
        logger.info("in read one");
        return this.postService.readPost(id);
    }

    /*
       put은 보내고 있는 데이터를 그 위치에대 넣어주세요라는 의미
       새로운 것을 만들때는 postmapping을 사용하고 대체를 할떄는 putmapping을 사용한다.
    */
    // PUT /post/0/

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //상태가 200ok가 되면 body가 void여서 계속 기다리는 상태가 될수있어서 HttpStatus.NO_CONTENT를 한다.
    public void updatePost(
            @PathVariable("id") int id,
            @RequestBody PostDto postDto
    ){
        logger.info("target id : "+ id);
        logger.info("update content "+ postDto);

        this.postService.updatePost(id, postDto);

    }

    // DELETE /post/0/

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(@PathVariable("id") int id){
        this.postService.deletePost(id);
    }

    /*
    * 아래 내용은 RESTful형식의 기본 형태로 만든것
    * */

    //게시글 CRUD
    // POST /post
    // GET /post/0/
    // GET /post
    // PUT /post/0/
    // DELETE /post/0/

    //게시판
    // POST /board
    // GET /board/0/
    // GET /board
    // PUT /board/0/
    // DELETE /board/0/
}
