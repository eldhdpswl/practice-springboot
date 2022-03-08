package dev.eldhdpswl.jpa;

import dev.eldhdpswl.jpa.entity.BoardEntity;
import dev.eldhdpswl.jpa.entity.PostEntity;
import dev.eldhdpswl.jpa.repository.BoardRepository;
import dev.eldhdpswl.jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {
    public TestComponent(
            @Autowired BoardRepository boardRepository,
            @Autowired PostRepository postRepository
            ){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName("new board");
        /*
          Entity는 실제로 존재하는 데이터를 표현하는 객제이다.
          그래서 코드상에서도 save 이후에 변경된 사항들 또는 내용들을 보기 위해서
          save의 결과물을 돌려주는 newBoardEntity가 실제로 생성된 정상적으로 사용할 수 있는 entity이다.
          따라서 save 작업하고 난 다음에 새로운 entity에 받아서 사용한다.
        */
        BoardEntity newBoardEntity = boardRepository.save(boardEntity);
//        System.out.println(newBoardEntity.getName());

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("hello ORM");
        postEntity.setContent("This Entity is created by hibernate!");
        postEntity.setWriter("eldhdpswl");
        postEntity.setBoardEntity(newBoardEntity);
        PostEntity newPostEntity = postRepository.save(postEntity);

//        System.out.println(postRepository.findAllByWriter("eldhdpswl").size());




    }

}
