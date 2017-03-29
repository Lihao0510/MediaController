package com.oridway.videopush.encode;

/**
 * Created by lihao on 2017/3/22.
 */

public class CharEncoder {

    public static String encodeWifiMessage(String[] wifiMessage) {
        StringBuffer stringBuffer = new StringBuffer();
        if (wifiMessage == null || wifiMessage.length != 2) {
            return null;
        }
        stringBuffer.append(CodeBook.wifiBegin());
        stringBuffer.append(encodeLine(wifiMessage[0]));
        stringBuffer.append(CodeBook.wifiEnd());
        stringBuffer.append(CodeBook.pwdBegin());
        stringBuffer.append(encodeLine(wifiMessage[1]));
        stringBuffer.append(CodeBook.pwdEnd());
        return stringBuffer.toString();
    }

    private static String encodeLine(String rawText) {
        StringBuffer stringBuffer = new StringBuffer();
        //TODO 按照码表解码编码字符串
        char[] charSeq = rawText.toCharArray();
        for (int i = 0; i < charSeq.length; i++) {
            stringBuffer.append(CodeBook.getValue(charSeq[i]));
        }
        return stringBuffer.toString();
    }
}
