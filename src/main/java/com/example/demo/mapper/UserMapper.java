package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Insert("INSERT INTO USER (account_id, name, token, gmt_create, gmt_modified, avatar_url)\n" + "VALUES\n" + "(#{account_id}, #{name}, #{token}, #{gmt_create}, #{gmt_modified}, #{avatar_url});")
    public void insert(User user);

    @Select("SELECT * FROM USER WHERE token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("SELECT * FROM USER WHERE id = #{id}")
    User findById(@Param("id") Integer id);
}
