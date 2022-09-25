package com.hollly.example.mapper;

import com.hollly.example.domain.SysUser;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hollly
 * @date 2022/8/21 14:36
 */
public interface UserMapper {
    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.hollly.example.mapper.RoleMapper.findByUid"))
    })
    public SysUser findByUsername(String username);

}
