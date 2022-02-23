package com.project.jcloud.login;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    public userDto loginCheck(userDto vo);
}
