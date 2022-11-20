package com.example.board.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.board.model.TradeTrigger;
import com.example.board.model.UserMsg;
import com.example.board.service.siseManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

import java.util.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Component
public class WebSocketHandler extends TextWebSocketHandler implements InitializingBean{
	static public WebSocketHandler sh=null;
	public WebSocketHandler(){
		sh=this;
	}
    siseManager sise;//바이낸스로부터 시세 받아옴.
    private Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();//접속자 소켓들
    public static Queue<UserMsg> msgList = new LinkedList<>();
	public static ArrayList<TradeTrigger> tradeList = new ArrayList<>();

    // 사용자가(브라우저) 웹소켓 서버에 붙게되면 호출
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionSet.add(session);
    }

    // 접속이 끊어진 사용자가 발생하면 호출
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        this.sessionSet.remove(session);
    }

    // CLIENTS 객체에 담긴 세션값들을 가져와서 반복문을 통해 메시지를 발송
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = "" + message.getPayload();
        synchronized(msgList) {
            msgList.add(new UserMsg(session,msg));
        }
    }
    @Override
	public void afterPropertiesSet() throws Exception {
        sise = new siseManager();
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);

						synchronized(tradeList){
							int msize = msgList.size();
							if(msize > 0){
								for(int i=0;i<3000;i++)
									cmdProcess();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();        
    }
	public void tradeCheck(){		
		synchronized(tradeList){
			for (Iterator<TradeTrigger> iter = tradeList.iterator(); iter.hasNext(); ) {
				TradeTrigger trigger = iter.next();
			}
		}
	}
    public void cmdProcess() throws Exception {
		UserMsg um = null;
		synchronized (msgList) {
			if (msgList.size() > 0) {
				um = msgList.poll();
			}
		}
		if (um == null)
			return;
		WebSocketSession session = um.session;
		String msg = um.msg;
		// =================================================} msg
		JSONParser p = new JSONParser();
		JSONObject obj = null;
		
		try {
			obj = (JSONObject) p.parse(msg);
		} catch (Exception e) {
			return;
		}
		String protocol = obj.get("protocol").toString();
		
		switch(protocol){
		case "buyBtn": 				OnBuyBtn(session, obj); break;
/* 		case "setTPSL":				setTPSL(session, obj); break;
		case "login":				OnLogin(session, obj); break;
		case "adminLogin":			OnAdminLogin(session, obj); break;
		case "sendLinePacket":		sendLinePacket(session, obj); break;
		case "orderCancel":			cancelOrder(obj); break;
		case "orderAllCancel":		cancelAllOrder(obj); break;
		case "changeLeverage":		changeLeverage(session, obj); break;
		case "changeLeverageStart":	changeLeverageStart(session, obj); break;
		case "changeSymbol":		initOrderAndPositionToUserSise(session, obj); break;
		case "submitRequest":		submitRequest(obj); break;
		case "newMember":			newMember(obj); break;		*/
		}
	}
			        	  
    public void sendAll(String message){
        sessionSet.forEach( (ss)->{
            send(ss,message);
        });
    }
	public void OnBuyBtn(WebSocketSession session,JSONObject obj){
		
	}

    public void send(WebSocketSession session,String msg){
        try{
            synchronized(session){
                session.sendMessage(new TextMessage(msg));
            }
        }catch(Exception e){ }
    }

}
