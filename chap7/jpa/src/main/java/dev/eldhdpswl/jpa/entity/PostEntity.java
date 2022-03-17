package dev.eldhdpswl.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "post") //table의 name을 post 정의, 이 설정을 안하면 table name은 post_entity
public class PostEntity extends BaseEntity{

    @Id //JPA에 Long id가 primary key 변수라는 것을 알려준다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Table을 생성하면서 auto generated, auto increment, unique key와 같은 id생성하는 규직
    private Long id;

    private String title;
    private String content;
    private String writer;


    /*
       post는 board에 속해있다.
       post는 여러개이지만, board는 1개이다.
    */
//    @ManyToOne(
//            targetEntity = BoardEntity.class, //어떠한 Entity를 상대로 관계를 맺나
//            fetch = FetchType.LAZY
//    )
//    @JoinColumn(name = "board_id")
//    private BoardEntity boardEntity;


    public PostEntity() {
    }

    public PostEntity(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }


    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                '}';
    }

}


