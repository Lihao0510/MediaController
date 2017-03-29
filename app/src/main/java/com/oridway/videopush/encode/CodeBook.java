package com.oridway.videopush.encode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihao on 2017/3/22.
 */

public class CodeBook {

    private static final int MAP_CAPACITY = 128;
    private Map<Character, String> codeMap;
    private static CodeBook mDictionary;
    private static final String WIFI_BEGIN = "0E";
    private static final String WIFI_END = "0F";
    private static final String PWD_BEGIN = "9E";
    private static final String PWD_END = "9F";

    private CodeBook() {
        codeMap = new HashMap<>(MAP_CAPACITY);
    }

    public static void init() {
        if (mDictionary == null) {
            synchronized (CodeBook.class) {
                if (mDictionary == null)
                    mDictionary = new CodeBook();
            }
        }
        mDictionary.initDictionary();
    }

    private void initDictionary() {
        codeMap.clear();
        codeMap.put('0', "10");
        codeMap.put('1', "11");
        codeMap.put('2', "12");
        codeMap.put('3', "13");
        codeMap.put('4', "14");
        codeMap.put('5', "15");
        codeMap.put('6', "16");
        codeMap.put('7', "17");
        codeMap.put('8', "18");
        codeMap.put('9', "19");
        codeMap.put('A', "1A");
        codeMap.put('B', "1B");
        codeMap.put('C', "1C");
        codeMap.put('D', "1D");
        codeMap.put('E', "1E");
        codeMap.put('F', "1F");
        codeMap.put('G', "30");
        codeMap.put('H', "31");
        codeMap.put('I', "32");
        codeMap.put('J', "33");
        codeMap.put('K', "34");
        codeMap.put('L', "35");
        codeMap.put('M', "36");
        codeMap.put('N', "37");
        codeMap.put('O', "38");
        codeMap.put('P', "39");
        codeMap.put('Q', "3A");
        codeMap.put('R', "3B");
        codeMap.put('S', "3C");
        codeMap.put('T', "3D");
        codeMap.put('U', "3E");
        codeMap.put('V', "3F");
        codeMap.put('W', "50");
        codeMap.put('X', "51");
        codeMap.put('Y', "52");
        codeMap.put('Z', "53");
        codeMap.put('a', "54");
        codeMap.put('b', "55");
        codeMap.put('c', "56");
        codeMap.put('d', "57");
        codeMap.put('e', "58");
        codeMap.put('f', "59");
        codeMap.put('g', "5A");
        codeMap.put('h', "5B");
        codeMap.put('i', "5C");
        codeMap.put('j', "5D");
        codeMap.put('k', "5E");
        codeMap.put('l', "5F");
        codeMap.put('m', "60");
        codeMap.put('n', "61");
        codeMap.put('o', "62");
        codeMap.put('p', "63");
        codeMap.put('q', "64");
        codeMap.put('r', "65");
        codeMap.put('s', "66");
        codeMap.put('t', "67");
        codeMap.put('u', "68");
        codeMap.put('v', "69");
        codeMap.put('w', "6A");
        codeMap.put('x', "6B");
        codeMap.put('y', "6C");
        codeMap.put('z', "6D");
        codeMap.put('_', "6E");
        codeMap.put('-', "6F");
        codeMap.put('<', "80");
        codeMap.put('>', "81");
        codeMap.put('/', "82");
        codeMap.put('~', "83");
        codeMap.put('`', "84");
        codeMap.put('#', "85");
        codeMap.put('@', "86");
        codeMap.put('%', "87");
        codeMap.put('^', "88");
        codeMap.put('&', "89");
        codeMap.put('*', "8A");
        codeMap.put('$', "8B");
        codeMap.put('!', "8C");
        codeMap.put(',', "8D");
        codeMap.put('.', "8E");
        codeMap.put('?', "8F");
        codeMap.put(';', "A0");
        codeMap.put('"', "A1");
        codeMap.put(':', "A2");

    }

    public static String getValue(char key) {
        return mDictionary.codeMap.get(key);
    }

    public static String wifiBegin() {
        return WIFI_BEGIN;
    }

    public static String wifiEnd() {
        return WIFI_END;
    }

    public static String pwdBegin() {
        return PWD_BEGIN;
    }

    public static String pwdEnd() {
        return PWD_END;
    }
}
