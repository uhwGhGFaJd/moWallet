package com.mowallet.mapper;

import com.mowallet.domain.CreateUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Mapper
@Repository
public interface CreateUserMapper {
    @Select("SELECT EXISTS(SELECT 1 FROM users WHERE user_name = #{user_name} LIMIT 1)")
    boolean checkUsername(String user_name);

    @Insert("INSERT INTO users(user_name, user_password) VALUES (#{username}, #{password})")
    void insertNewUser(CreateUser createUser);
}
