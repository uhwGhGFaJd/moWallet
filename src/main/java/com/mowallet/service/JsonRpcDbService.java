package com.mowallet.service;

import com.mowallet.domain.GetAddressesByLabel;
import com.mowallet.domain.getNewAddressPost;

import java.util.List;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/27
 * Github       : https://github.com/uhwGhGFaJd
 */
public interface JsonRpcDbService {
    void getNewAddressAndInsertDb(getNewAddressPost getNewAddressPost);

    List<GetAddressesByLabel> getUserCreatedAddress(int user_id);
}
