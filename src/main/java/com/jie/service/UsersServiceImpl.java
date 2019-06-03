package com.jie.service;

import com.jie.mapper.UsersMapper;
import com.jie.pojo.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jie
 * @date 2019/6/1 -10:21
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public Users login(Map<String, Object> map) {
        return usersMapper.login(map);
    }

    @Override
    public List<Users> getUsersByPage(Map<String, Object> map) {
        return usersMapper.getUsersByPage(map);
    }

    @Override
    public int getCount(Map<String, Object> map) {
        return usersMapper.getCount(map);
    }

    @Override
    public boolean updatePwd(String password, int id) {
        return usersMapper.updatePwd(password,id);
    }
}
