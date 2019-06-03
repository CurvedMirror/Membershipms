package com.jie.mapper;

import com.jie.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author jie
 * @date 2019/5/31 -21:44
 */
public interface UsersMapper {

    /**
     * 登录
     */
    Users login(Map<String, Object> map);

    /**
     * 分页查询
     */
    List<Users> getUsersByPage(Map<String, Object> map);

    /**
     * 获取页数
     */
    int getCount(Map<String, Object> map);

    boolean updatePwd(@Param("password")String password, @Param("id") int id);

}
