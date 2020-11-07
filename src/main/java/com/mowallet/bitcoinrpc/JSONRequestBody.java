/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mowallet.bitcoinrpc;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hp
 */
@Getter
@Setter
public class JSONRequestBody {
    private String method, id;
    private Object[] params = new Object[]{};
}
