package com.mowallet.mapper;

import com.mowallet.domain.getNewAddressPost;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/22
 * Github       : https://github.com/uhwGhGFaJd
 */
@Mapper
@Repository
public interface JsonRpcDbMapper {
    @Insert("INSERT INTO users_address(user_id, address, address_desc) VALUES (#{user_id}, #{address}, #{desc})")
    void getNewAddressAndInsertDb(getNewAddressPost getNewAddressPost);
}
