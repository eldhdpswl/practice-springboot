package dev.eldhdpswl.crud.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller와 @ResponseBody를 붙여넣으면 RestController가 controller에 있는 모든 함수에 ResponseBody를 붙여넣는것과 똑같다.
//PostController 안에 모든 함수에 ResponseBody가 붙은 형태로 된다.
@Controller
@ResponseBody
//@RequestMapping("post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    //private final List<PostDto> postList;
    private final PostService postService;
    public PostController(
            @Autowired PostService postService
    ){
        //postList = new ArrayList<>();
        this.postService = postService;
    }

    /*
    * 아래 함수들은 Rest API와는 거리가 먼 형태이다.
    * 웹서비스를 기준으로 아래 함수들은 URL은 어떤자원이 있는지 나타내기위해 사용한 것인데
      기능을 추가했기떄문에, 좋은 형태는 아니다.
    * */
    @PostMapping("create")
    public void createPost(@RequestBody PostDto postDto){
        logger.info(postDto.toString());
        //this.postList.add(postDto);
        this.postService.createPost(postDto);
    }

    @GetMapping("read-all")
    public List<PostDto> readPostAll(){
        logger.info("in read all");
        //return this.postList;
        return this.postService.readPostAll();
    }


    @GetMapping("read-one")
    public PostDto readPostOne(@RequestParam("id") int id){
        logger.info("in read one");
        //return this.postList.get(id);
        return this.postService.readPost(id);
    }

    @PostMapping("update")
    public void updatePost(
            @RequestParam("id") int id,
            @RequestBody PostDto postDto
    ){

        /*
        * 게시자는 바꿀수 없다는 가정하에 진행
        * 아래와 같은 내용을 거치는 이유는 targetPost대신에 postDto 날라온거를 다시 사용할수도 있겠지만,
          다시 사용을 하게 되면 postDto의 title이나 content의 내용이 없었다면 targetPost의 내용도 그대로 바뀌게 된다.
          그렇게 되면 RestFul과는 거리가 멀기 떄문에 아래와 같이 체크한다(자세한 내용은 다음 강의에서 확인).

          PostDto targetPost = this.postList.get(id);
          if(postDto.getTitle() != null){
              targetPost.setTitle(postDto.getTitle());
          }
          if(postDto.getContent() != null){
              targetPost.setContent(postDto.getContent());
          }
          this.postList.set(id, targetPost);

        * */

        logger.info("target id : "+ id);
        logger.info("update content "+ postDto);

        this.postService.updatePost(id, postDto);
    }

    @DeleteMapping("delete")
    public void deletePost(@RequestParam("id") int id){
        //this.postService.remove(id);
        this.postService.deletePost(id);
    }




}
