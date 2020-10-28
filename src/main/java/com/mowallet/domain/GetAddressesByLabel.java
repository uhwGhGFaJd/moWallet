package com.mowallet.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/26
 * Github       : https://github.com/uhwGhGFaJd
 */
@Getter
@Setter
@Builder
public class GetAddressesByLabel {
    private String address_id;
    private String address;
    private String address_desc;
    private String address_create_date;
}
