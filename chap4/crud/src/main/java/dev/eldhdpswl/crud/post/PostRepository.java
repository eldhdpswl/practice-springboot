package dev.eldhdpswl.crud.post;

import java.util.List;

/*
* 데이터 회수는 Repository
  회수된 데이터를 비교해서 사용가능한 데이터인지, 검증을 Service에서 작업
* Repository는 데이터를 실제로 주고 받는 파트를 만들때 사용한다.
* */

public interface PostRepository {
    boolean save(PostDto dto);
    List<PostDto> findAll();
    PostDto findById(int id);
    boolean update(int id, PostDto dto);
    boolean delete(int id);

}
