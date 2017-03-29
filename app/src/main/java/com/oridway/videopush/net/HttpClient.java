package com.oridway.videopush.net;

import android.content.Intent;
import android.widget.Toast;


import com.oridway.videopush.application.Constant;
import com.oridway.videopush.application.IPCApplication;
import com.oridway.videopush.util.LogUtil;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lihao on 2016/12/30.
 */

public class HttpClient {

    private HttpClient() {

    }

    public interface MessageListener {
        public void onMessageArrive(SoapObject soapObject);
        public void onError(Throwable error);
    }

    public interface ListListener {
        public void onListArrive(SoapObject soapObject);
        public void onError(Throwable error);
    }

    public static void getMessage(final String url, final String method, final Map<String, Object> params, final MessageListener listener) {

        Observable.create(
                new Observable.OnSubscribe<SoapObject>() {
                    @Override
                    public void call(Subscriber<? super SoapObject> subscriber) {
                        subscriber.onNext(Request.soapRequest(Constant.WH_NAMESPACE, method, url, params));
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SoapObject>() {

                    @Override
                    public void onCompleted() {
                        LogUtil.debugLog("获取信息请求完成!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.debugLog("获取信息请求失败:" + e.toString());
                        e.printStackTrace();
                        listener.onError(e);
                    }

                    @Override
                    public void onNext(SoapObject soapObject) {
                        try {
                            listener.onMessageArrive((SoapObject) soapObject.getProperty(0));
                        } catch (ClassCastException e) {
                            listener.onMessageArrive(soapObject);
                        }
                    }
                });
    }

    public static void getList(final String url, final String method, final ListListener listener) {
        Observable.create(new Observable.OnSubscribe<SoapObject>() {
            @Override
            public void call(Subscriber<? super SoapObject> subscriber) {
                subscriber.onNext(Request.listRequest(Constant.WH_NAMESPACE, method, url));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SoapObject>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.debugLog("获取列表请求完成!");
                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onError(e);
                        LogUtil.debugLog("获取列表请求失败:" + e.toString());
                    }

                    @Override
                    public void onNext(SoapObject soapObject) {
                        if (soapObject != null) {
                            listener.onListArrive(soapObject);
                        } else {
                            Toast.makeText(IPCApplication.getAppContext(), "请求失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void getList(final String url, final String method, final int curPage, final int pageSize, final ListListener listener) {
        Observable.create(new Observable.OnSubscribe<SoapObject>() {
            @Override
            public void call(Subscriber<? super SoapObject> subscriber) {
                subscriber.onNext(Request.listRequest(Constant.WH_NAMESPACE, method, url, curPage, pageSize));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SoapObject>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.debugLog("获取列表请求完成!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.debugLog("获取列表请求失败:" + e.toString());
                        listener.onError(e);
                    }

                    @Override
                    public void onNext(SoapObject soapObject) {
                        if (soapObject != null) {
                            listener.onListArrive(soapObject);
                        } else {
                            Toast.makeText(IPCApplication.getAppContext(), "请求失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void getList(final String url, final String method, final int curPage, final int pageSize, final String where, final String sortOrder, final ListListener listener) {
        Observable.create(new Observable.OnSubscribe<SoapObject>() {
            @Override
            public void call(Subscriber<? super SoapObject> subscriber) {
                subscriber.onNext(Request.listRequest(Constant.WH_NAMESPACE, method, url, curPage, pageSize, where, sortOrder));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SoapObject>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.debugLog("获取列表请求完成!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.debugLog("获取列表请求失败:" + e.toString());
                        listener.onError(e);
                    }

                    @Override
                    public void onNext(SoapObject soapObject) {
                        if (soapObject != null) {
                            listener.onListArrive(soapObject);
                        } else {
                            Toast.makeText(IPCApplication.getAppContext(), "请求失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
