package com.mowallet.service.impl;

import com.mowallet.domain.GetAddressesByLabel;
import com.mowallet.domain.getNewAddressPost;
import com.mowallet.mapper.JsonRpcDbMapper;
import com.mowallet.service.JsonRpcDbService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/27
 * Github       : https://github.com/uhwGhGFaJd
 */
@Service
public class JsonRpcDbServiceImpl implements JsonRpcDbService {

    private final JsonRpcDbMapper jsonRpcDbMapper;

    public JsonRpcDbServiceImpl(JsonRpcDbMapper jsonRpcDbMapper) {
        this.jsonRpcDbMapper = jsonRpcDbMapper;
    }

    @Override
    public void getNewAddressAndInsertDb(getNewAddressPost getNewAddressPost) {
        jsonRpcDbMapper.getNewAddressAndInsertDb(getNewAddressPost);
    }

    @Override
    public List<GetAddressesByLabel> getUserCreatedAddress(int user_id) {
        return jsonRpcDbMapper.getUserCreatedAddress(user_id);
    }
}
