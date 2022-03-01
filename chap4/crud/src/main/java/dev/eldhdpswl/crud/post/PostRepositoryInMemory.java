package dev.eldhdpswl.crud.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryInMemory implements PostRepository{

    /*
    * Repository에서 boolean값을 돌려주는 이유는 아래 함수들이 성공하는 케이스들만 가정하고 있기 떄문이다.
      사실은 아래 함수들을 더 정교하게 만들어야한다. 간단하게 다루기 위해 return을 boolean으로 했다.
    * */

    private static final Logger logger = LoggerFactory.getLogger(PostRepositoryInMemory.class);
    private final List<PostDto> postList;

    public PostRepositoryInMemory() {
        this.postList = new ArrayList<>();
    }

    //CRUD에서 C
    @Override
    public boolean save(PostDto dto) {
        return this.postList.add(dto);

    }

    @Override
    public List<PostDto> findAll() {
        return this.postList;
    }

    //CRUD에서 R
    @Override
    public PostDto findById(int id) {
        return this.postList.get(id);
    }

    //CRUD에서 U
    @Override
    public boolean update(int id, PostDto postDto) {
        PostDto targetPost = this.postList.get(id);

        if(postDto.getTitle() != null){
            targetPost.setTitle(postDto.getTitle());
        }
        if(postDto.getContent() != null){
            targetPost.setContent(postDto.getContent());
        }
        this.postList.set(id, targetPost);
        return true;
    }

    //CRUD에서 D
    @Override
    public boolean delete(int id) {
        this.postList.remove(id);
        return true;
    }
}
