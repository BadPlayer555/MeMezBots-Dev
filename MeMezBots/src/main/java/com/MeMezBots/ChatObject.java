package com.MeMezBots;

public class ChatObject {
	
	private String userName;
    private String message;
	protected String x;
	protected String y;
	protected int byteLength;
	protected String ip;
	protected String origin;

    public ChatObject() {
    }

    public ChatObject(String userName, String message, String x, String y, int byteLength, String ip, String origin) {
        super();
        this.userName = userName;
        this.message = message;
        this.x = x;
        this.y = y;
        this.byteLength = byteLength;
        this.ip = ip;
        this.origin = origin;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getx() {
        return x;
    }
    
    public void getx(String x) {
        this.x = x;
    }
    
    public String gety() {
        return x;
    }
    
    public void gety(String y) {
        this.y = y;
    }

    public float getbyteLength() {
        return byteLength;
    }
    
    public void getbyteLength(int byteLength) {
        this.byteLength = byteLength;
    }
    
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOrigin() {
        return userName;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}

