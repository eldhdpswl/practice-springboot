package dev.eldhdpswl.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")  //table의 name을 board로 정의, 이 설정을 안하면 table name은 board_entity
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "board_name") //table상에 name 컬럼명을 board_name으로 해준다.
    private String name;


    /*
      Entity는 실제로 존재하는 데이터를 표현하는 객제이다.

      board 하나에 post는 여러개이다.

      mappedBy가 없으면 BoardEntity에 작성된 @OneToMany와 PostEntity에 작성된 @ManyToOne의 관계가 서로 다른 관계라고
      이해를 할수 있기 때문에 작성을 한다.
      mappedBy의 "boardEntiy"는 PostEntity에 @ManyToOne에 정의된 boardEntity의 변수에 동일하게 작성한다.
      그렇게 되면 hibernate가 볼떄, BoardEntity와 PostEntity가 매핑이되는 관계라는 것을 알게된다.

      FetchType은 연관관계에 있는 Entity를 어떤식으로 불러올지에 대한 방식을 정의, 상황에 따라서 변경을 할수 있어야된다.
    */
    @OneToMany(
            targetEntity = PostEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "boardEntity"
    )
    private List<PostEntity> postEntityList = new ArrayList<>();


    public BoardEntity() {
    }

    public BoardEntity(Long id, String name, List<PostEntity> postEntityList) {
        this.id = id;
        this.name = name;
        this.postEntityList = postEntityList;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostEntity> getPostEntityList() {
        return postEntityList;
    }

    public void setPostEntityList(List<PostEntity> postEntityList) {
        this.postEntityList = postEntityList;
    }

    @Override
    public String toString() {
        return "BoardEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postEntityList=" + postEntityList +
                '}';
    }


}
