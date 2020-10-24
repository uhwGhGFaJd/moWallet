package com.mowallet.mapper;

import com.mowallet.domain.CustomUserDetails;
import com.mowallet.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Mapper
@Repository()
public interface LoginMapper {
    @Select("SELECT user_id, user_name, user_password FROM users WHERE user_name = #{user_name}")
    User getUserInfo(String user_name);

    @Select("SELECT user_name, user_password, user_role, enabled FROM users WHERE user_name = #{user_name}")
    CustomUserDetails getUserById(String user_name);
}
