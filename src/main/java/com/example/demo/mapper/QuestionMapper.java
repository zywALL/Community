package com.example.demo.mapper;

import com.example.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionMapper {
    @Insert("INSERT INTO QUESTION (title, description, gmt_create, gmt_modified, creator, tag)\n" + "VALUES\n" + "(#{title}, #{description}, #{gmt_create}, #{gmt_modified}, #{creator}, #{tag});")
    public void insert(Question question);
}
