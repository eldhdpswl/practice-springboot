package dev.eldhdpswl.mybatis.mapper;

import dev.eldhdpswl.mybatis.dto.PostDto;

import java.util.List;

public interface PostMapper {
    int createPost(PostDto dto); //int형을 쓰는 이유는 insert, delete, update 수행시 몇개의 row가 처리됬는지 확인용
    int createPostAll(List<PostDto> dtoList);
    PostDto readPost(int id);
    List<PostDto> readPostAll();
    PostDto readPostQuery(PostDto dto);
    int updatePost(PostDto dto);
    int deletePost(int id);

}
