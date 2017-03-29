package com.oridway.videopush.model;

import org.ksoap2.serialization.SoapObject;

/**
 * Created by lihao on 2016/12/30.
 */

public class CurUser {
    
    private CurUser(){
        
    }

    public static String userID = "";
    public static String workNumber = "";
    public static String userType = "";
    public static String userName = "";
    public static String sysUserDesc = "";
    public static String passWord = "";
    public static String userCName = "";
    public static String orgID = "";
    public static String orgName = "";
    public static String loginMode = "";
    public static String userIP = "";
    public static String dynaPSW = "";
    public static String loginMessage = "";
    
    public static void setUserInfo(SoapObject soapObject){
        userCName = soapObject.getProperty("userCName").toString();
        userID = soapObject.getProperty("userID").toString();
        userIP = soapObject.getProperty("userIP").toString();
        userType = soapObject.getProperty("userType").toString();
        userName = soapObject.getProperty("userName").toString();
        sysUserDesc = soapObject.getProperty("sysUserDesc").toString();
        workNumber = soapObject.getProperty("workNumber").toString();
        passWord = soapObject.getProperty("passWord").toString();
        dynaPSW = soapObject.getProperty("dynaPSW").toString();
        loginMode = soapObject.getProperty("loginMode").toString();
        orgID = soapObject.getProperty("orgID").toString();
        orgName = soapObject.getProperty("orgName").toString();
        loginMessage = soapObject.getProperty("loginMessage").toString();
    }

    public static String getMessage() {
        return "userID:" + userID + ", userName:" + userCName + ", userNum:" + userName +", orgName:" + orgName +", userIP:" + userIP;
    }
}
