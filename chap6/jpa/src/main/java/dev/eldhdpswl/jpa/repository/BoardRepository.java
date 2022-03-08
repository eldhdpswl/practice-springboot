package dev.eldhdpswl.jpa.repository;

import dev.eldhdpswl.jpa.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<BoardEntity, Long> {

    // CrudRepository< table명(Entity명), id type> => crud 작업을 하기 위한 interface


}
