package com.oridway.videopush.net;

import org.ksoap2.serialization.SoapObject;

import java.util.Map;

/**
 * Created by lihao on 2016/12/17.
 */

public class Request {

    public static SoapObject soapRequest(String nameSpace, String requestMethod, String url, Map<String, Object> params) {
        SoapRequest soapRequest = SoapRequest.getInstance();
        SoapObject result = soapRequest.setData(nameSpace, requestMethod, url, params);
        return result;
    }

    public static SoapObject listRequest(String nameSpace, String requestMethod, String url) {
        ListRequest listRequest = ListRequest.getInstance();
        SoapObject result = listRequest.setData(nameSpace, requestMethod, url);
        return result;
    }

    public static SoapObject listRequest(String nameSpace, String requestMethod, String url, int curPage, int pageSize) {
        ListRequest listRequest = ListRequest.getInstance();
        SoapObject result = listRequest.setData(nameSpace, requestMethod, url, curPage, pageSize);
        return result;
    }

    public static SoapObject listRequest(String nameSpace, String requestMethod, String url, int curPage, int pageSize, String where, String sortOrder) {
        ListRequest listRequest = ListRequest.getInstance();
        SoapObject result = listRequest.setData(nameSpace, requestMethod, url, curPage, pageSize, where, sortOrder);
        return result;
    }
}
