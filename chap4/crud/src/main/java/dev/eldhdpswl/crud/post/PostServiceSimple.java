package dev.eldhdpswl.crud.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceSimple implements PostService{

    private static final Logger logger = LoggerFactory.getLogger(PostServiceSimple.class);

    // 이렇게 사용한 이유는 PostRepository 이후에 어떤식으로 구현이 되어있던 상관하지 않겠다라는 의미
    private final PostRepository postRepository;

    public PostServiceSimple(
            /*
            * interface를 요구를 해도 PostRepository가 만족되는 인터페이스 클래스를 가져다가 현재 구현되어있는것 중에서
              우선순위로 구현되어있는 클래스를 구현체를 가져온다. 그래서 PostRepositoryInMemory 클래스를 가져온다.
              이 역할을 Spring IoC에서 해준다.

            * @Autowired가 붙으면  PostRepository 인터페이스를 만족하는 클래스를 IoC 컴테이너에서 제공을 해서
              postRepository 이 인자로 자동으로 주입을 한다(Dependancy Injection이라고도 한다.).

            */
            @Autowired PostRepository postRepository
    ) {
        this.postRepository = postRepository;
    }

    @Override
    public void createPost(PostDto dto) {
        // TODO
        // 예시로서 사용한 모습이고, 좋은 형태는 아니다.
        if (!this.postRepository.save(dto)){
            throw new RuntimeException("save failed");
        }

    }

    @Override
    public List<PostDto> readPostAll() {
        return this.postRepository.findAll();
    }

    @Override
    public PostDto readPost(int id) {
        return this.postRepository.findById(id);
    }

    @Override
    public void updatePost(int id, PostDto dto) {
        this.postRepository.update(id, dto);
    }

    @Override
    public void deletePost(int id) {
        this.postRepository.delete(id);
    }
}
