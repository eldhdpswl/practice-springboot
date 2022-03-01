package dev.eldhdpswl.mybatis;

import dev.eldhdpswl.mybatis.dao.BoardDao;
import dev.eldhdpswl.mybatis.dao.PostDao;
import dev.eldhdpswl.mybatis.dto.BoardDto;
import dev.eldhdpswl.mybatis.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestComponent {
    private final PostDao postDao;
    private final BoardDao boardDao;

    public TestComponent(
            @Autowired PostDao postDao,
            @Autowired BoardDao boardDao
    ){
        this.postDao = postDao;
        this.boardDao = boardDao;

        BoardDto boardDto = new BoardDto();
        boardDto.setName("new board");
        this.boardDao.createBoard(boardDto);
        System.out.println(boardDto.getId());

/*
        PostDto newPost = new PostDto();
        newPost.setTitle("form Mybatis");
        //newPost.setContent("Hello Database");
        newPost.setContent("Use Database with Spring Boot!");
        newPost.setWriter("eldhdpswl");
        newPost.setBoard(1);
        this.postDao.createPost(newPost);

        List<PostDto> postDtoList = this.postDao.readPostAll(); // 전체 읽기
        System.out.println(postDtoList.get(postDtoList.size() - 1)); //마지막에 추가된 데이터 확인용

        PostDto firstPost = postDtoList.get(0);
        //firstPost.setContent("Update From Mybatis!");
        firstPost.setContent("content updated from mybatis!");
        postDao.updatePost(firstPost);

        System.out.println(this.postDao.readPost(firstPost.getId()));
 */


    }
}
