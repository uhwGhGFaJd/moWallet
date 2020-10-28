package com.mowallet.mapper;

import com.mowallet.domain.GetAddressesByLabel;
import com.mowallet.domain.getNewAddressPost;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/22
 * Github       : https://github.com/uhwGhGFaJd
 */
@Mapper
@Repository
public interface JsonRpcDbMapper {
    @Insert("INSERT INTO users_address(user_id, address, address_desc) VALUES (#{user_id}, #{address}, #{desc})")
    void getNewAddressAndInsertDb(getNewAddressPost getNewAddressPost);

    @Select("SELECT address_id, address, address_desc, DATE_FORMAT(address_create_date,'%e %M %Y, %H:%i') address_create_date FROM users_address WHERE user_id = #{user_id} " +
            "ORDER BY address_id DESC limit 10")
    List<GetAddressesByLabel> getUserCreatedAddress(int user_id);

    @Select("SELECT config_value FROM config where id = 1")
    int getServiceFees();
}
