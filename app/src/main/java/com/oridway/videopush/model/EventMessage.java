package com.oridway.videopush.model;

/**
 * Created by lihao on 2017/3/29.
 * 已注册eventId事件列表:
 * 0: Fragment返回键点击通知HomeActivity切换
 */

public class EventMessage {

    public int eventId;
    public int eventType;
    public String eventName;
    public String eventBody;
    public String[] eventMessage;

    public EventMessage(int eventId, int eventType) {
        this.eventId = eventId;
        this.eventType = eventType;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventBody() {
        return eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

    public String[] getEventMessage() {
        return eventMessage;
    }

    public void setEventMessage(String[] eventMessage) {
        this.eventMessage = eventMessage;
    }
}
