package com.example.demo.mapper;

import com.example.demo.model.QuestionPublish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionPublishMapper {
    @Select("SELECT * FROM QUESTIONPUBLISH LIMIT #{offset}, #{size};")
    List<QuestionPublish> list(@Param(value = "offset")Integer offset, @Param(value = "size")Integer size);

    @Select("SELECT COUNT(1) FROM QUESTIONPUBLISH;")
    Integer count();
}
