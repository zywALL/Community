package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER (account_id, name, token, gmt_create, gmt_modified)\n" + "VALUES\n" + "(#{account_id}, #{name}, #{token}, #{gmt_create}, #{gmt_modified});")
    void insert(User user);
}
