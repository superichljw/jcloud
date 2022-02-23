package com.project.jcloud.login;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userService")
public class userServiceImpl implements userService {

    @Autowired
    UserMapper userMapper;

    @Override
    public userDto loginCheck(userDto vo) throws Exception{
        log.debug("loginCheck service start");
        userDto result = userMapper.loginCheck(vo);
        log.debug("loginCheck service end");
        return result;
    };
}
