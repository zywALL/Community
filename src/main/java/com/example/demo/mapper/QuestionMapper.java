package com.example.demo.mapper;

import com.example.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    @Insert("INSERT INTO QUESTION (title, description, gmt_create, gmt_modified, creator, tag)\n" + "VALUES\n" + "(#{title}, #{description}, #{gmt_create}, #{gmt_modified}, #{creator}, #{tag});")
    void insert(Question question);

    @Select("SELECT * FROM QUESTION LIMIT #{offset}, #{size};" )
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size")Integer size);

    @Select("SELECT COUNT(1) FROM QUESTION;")
    Integer count();

    @Select("SELECT * FROM QUESTION WHERE creator = #{userId} LIMIT #{offset}, #{size};")
    List<Question> listByUserId(@Param(value = "userId")Integer userId, @Param(value = "offset")Integer offset, @Param(value = "size")Integer size);
}
