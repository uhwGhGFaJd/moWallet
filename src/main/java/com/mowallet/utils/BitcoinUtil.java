package com.mowallet.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/26
 * Github       : https://github.com/uhwGhGFaJd
 */

public class BitcoinUtil {

    public static String optString(JSONObject json, String key) {
        if (json.isNull(key))
            return null;
        else
            return json.optString(key, null);
    }

    public static int optInt(JSONObject json, String key) {
        if (json.isNull(key))
            return 0;
        else
            return json.optInt(key, 0);
    }

    public static BigDecimal optBigDecimal(JSONObject json, String key) {
        if (json.isNull(key))
            return BigDecimal.ZERO;
        else
            return json.optBigDecimal(key, BigDecimal.ZERO).setScale(8, RoundingMode.DOWN);
    }

    public static String getTimestampToDate(long timestampStr) {
        Date date = new Date(timestampStr * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }



}
