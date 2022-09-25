package com.hollly.example.mapper;

import com.hollly.example.domain.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hollly
 * @date 2022/8/21 14:36
 */
public interface RoleMapper {
    @Select("select r.id,r.role_name roleName ,r.role_desc roleDesc " +
            "FROM sys_role r,sys_user_role ur " +
            "WHERE r.id=ur.rid AND ur.uid=#{uid}")
    public List<SysRole> findByUid(Integer uid);
}
