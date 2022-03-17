package dev.eldhdpswl.jpa.repository;

import dev.eldhdpswl.jpa.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

    //where writer = ?
    //List<PostEntity> findAllByWriter(String writer);

    // where writer = ? and board_entity_id = ?
    //List<PostEntity> findAllByWriterAndBoardEntity(String writer, BoardEntity boardEntity);

    //writer를 포함하는 내용 찾기
    //List<PostEntity> findAllByWriterContaing(String writer);


}
