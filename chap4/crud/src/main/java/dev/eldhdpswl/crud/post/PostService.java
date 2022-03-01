package dev.eldhdpswl.crud.post;

import java.util.List;

/*
* 데이터 회수는 Repossitory
  회수된 데이터를 비교해서 사용가능한 데이터인지, 검증을 Service에서 작업
* 서비스는 비즈니스 로직을 담당한다. 실제로 사용하기 이전의 비즈니스 로직 또는
  여러가지 실행결과들을 조합할떄 사용
* */

public interface PostService {
    void createPost(PostDto dto);
    List<PostDto> readPostAll();
    PostDto readPost(int id);
    void updatePost(int id, PostDto dto);
    void deletePost(int id);
}
