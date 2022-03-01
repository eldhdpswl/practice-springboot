package dev.eldhdpswl.mybatis.dao;

import dev.eldhdpswl.mybatis.dto.PostDto;
import dev.eldhdpswl.mybatis.mapper.PostMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDao {
    private final SqlSessionFactory sessionFactory;

    public PostDao(
            @Autowired SqlSessionFactory sessionFactory
    ){
        this.sessionFactory = sessionFactory;
    }

    public int createPost(PostDto dto){
        /*
        SqlSession session = sessionFactory.openSession();
        //session에서 PostMapper와 동일한 구현체를 달라는 의미
        //PostMapper의 구현한 구현체가 mapper에 주입이된다.
        PostMapper mapper = session.getMapper(PostMapper.class);
        int rowAffected = mapper.createPost(dto);
        session.close(); // database와 소통을 하기 위해서 SqlSession session이 유지가 된다. 그래서 통신을 하고나서 session을 닫아야한다.

        return rowAffected;
        */
        //위의 소스를 아래와 같은 방법으로 사용할 수 있다.
        try (SqlSession session = sessionFactory.openSession();){
            PostMapper mapper = session.getMapper(PostMapper.class);
            return mapper.createPost(dto);
        }

    }
    /*
      SqlSession을 함수마다 새로 사용하는 이유는 하나만 사용했을시, 여러 함수를 동시사용시, 하나의 함수 실행시간이 길어지면
      다른 함수에도 영향을 미칠수 있어서 SqlSession은 따로 사용한다.
    */
    public PostDto readPost(int id){
        try (SqlSession session = sessionFactory.openSession()){
            PostMapper mapper = session.getMapper(PostMapper.class);
            return mapper.readPost(id);
        }
    }

    public List<PostDto> readPostAll(){
        try (SqlSession session = sessionFactory.openSession()){
            PostMapper mapper = session.getMapper(PostMapper.class);
            return mapper.readPostAll();
        }
    }

    public int updatePost(PostDto dto){
        try (SqlSession session = sessionFactory.openSession()){
            PostMapper mapper = session.getMapper(PostMapper.class);
            return mapper.updatePost(dto);
        }
    }

    public int deletePost(int id){
        try (SqlSession session = sessionFactory.openSession()){
            PostMapper mapper = session.getMapper(PostMapper.class);
            return mapper.deletePost(id);
        }
    }



}
