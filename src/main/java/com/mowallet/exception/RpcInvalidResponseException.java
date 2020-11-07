/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mowallet.exception;

/**
 *
 * @author hp
 */
public class RpcInvalidResponseException extends CryptoCurrencyRpcException {
    
    public RpcInvalidResponseException(String message) {
        super(message);
    }
}
