package com.oridway.videopush.net;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lihao on 2016/12/16.
 */

public class SoapRequest {

    private Element[] requestHeader;
    private SoapSerializationEnvelope envelope;
    private PropertyInfo property;
    private SoapObject requestBody;

    private SoapRequest() {

    }

    public static SoapRequest getInstance() {
        return new SoapRequest();
    }

    public SoapObject setData(String nameSpace, String requestMethod, String url, Map<String, Object> params) {
        Log.d("Lihao", url.toString());
        Log.d("Lihao", nameSpace.toString());
        Log.d("Lihao", requestMethod.toString());

        requestHeader = NetUtil.setSoapHeader();
        requestBody = new SoapObject(nameSpace, requestMethod);
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                property = new PropertyInfo();
                property.setName(entry.getKey());
                property.setValue(entry.getValue());
                requestBody.addProperty(property);
            }
        }
        Log.d("Lihao", requestBody.toString());
        envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.headerOut = requestHeader;
        //envelope.bodyOut = requestBody;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(requestBody);
        HttpTransportSE ht = new HttpTransportSE(url);
        try {
            ht.call(nameSpace + requestMethod, envelope);
            Log.d("Lihao", envelope.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        SoapObject object = (SoapObject) envelope.bodyIn;
        return (SoapObject) envelope.bodyIn;
    }
}
