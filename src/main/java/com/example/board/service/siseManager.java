package com.example.board.service;

import java.net.URI;
import java.util.logging.SocketHandler;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.board.config.WebSocketHandler;


public class siseManager {
    String []coin={"btcusdt","ethusdt"}; //시세받을 종목 바이낸스 문자열 기준.
    public String url="wss://fstream.binance.com/stream?streams=";//시세 받을 서버 주소문자열,바이낸스
    public WebSocketSession webSocketSession=null;

    public siseManager(){
        websocketConnect();
    }

    WebSocketClient webSocketClient = null;
	public WebSocketSession websocketConnect(){
		try {
            for(int c=0;c<coin.length;c++){
                if(c != 0)url+="/";
                url += coin[c]+"@markPrice@1s/"+coin[c]+"@depth20/"+coin[c]+"@ticker/"+coin[c]+"@kline_1m/"+coin[c] +"@aggTrade";
            }
            System.out.println("websocket connect start : "+ url);
            
            webSocketClient = new StandardWebSocketClient();
 
            webSocketSession= webSocketClient.doHandshake(new TextWebSocketHandler() {
                @Override public void handleTextMessage(WebSocketSession session, TextMessage message) {
                	OnMessageManager(session,message.getPayload());
                }
                @Override public void afterConnectionEstablished(WebSocketSession session) {
                    System.out.println("websocket connected !!!!!!!!!!!");
                }
                @Override public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
                }
                @Override
                public void afterConnectionClosed(WebSocketSession session,CloseStatus status) throws Exception {
                }
            }, new WebSocketHttpHeaders(), URI.create(url)).get();
            return webSocketSession;
        } catch (Exception e) {
        	return null;
        }
	}
    void OnMessageManager(WebSocketSession session,String msg){
//        System.out.println("on msg:"+msg);

		JSONParser p = new JSONParser();
		JSONObject obj = null;
        String s ="";
		
		try {
			obj = (JSONObject) p.parse(msg);
            s = ""+obj.get("stream");
		} catch (Exception e) {
			return;
		}

//        System.out.println("o : "+ s.substring(s.length()-8,s.length() )) ;
		if (s.indexOf("@depth20") >= 0 ) {//오더북
           // System.out.println("o : "+msg );
		}else if (s.indexOf("@kline_1m") >= 0 ) {
            //System.out.println("o : "+msg );
            JSONObject ok = (JSONObject)obj.get("data");
            String coin = ""+ok.get("s");
            JSONObject oc = (JSONObject)ok.get("k");
//            System.out.println(coin+ " c:"+oc.get("c")+" o:" );
            if( coin.indexOf("BTCUSDT")>= 0 )
                System.out.println(" c:"+oc.get("c")+" o:" );
        }
        /* 
        else if (stream.slice(-13) === '@markPrice@1s') {
			try {
				binanceobj.fundingrate(jdata);
			}catch(e) {
				console.log(stream, " mark price err",e);
			}
		} else if (stream.slice(-7) === '@ticker') {
			try{
				binanceobj.lowhigh(jdata);
			}catch(e) {
				console.log(stream, " ticker err",e);
			}
		} else if (stream.slice(-9) === '@kline_1m') {
			try{
				// if(jdata['s'] === 'ROSEUSDT') {console.log('[websocket] p :', jdata['k'].c, ' v : ' ,jdata['k'].v, ' t : ', new Date().toLocaleString(), new Date().getMilliseconds());}
				binanceobj.kline(jdata['s'], jdata['k'], jdata['E']);
			}catch(e) {
				console.log(stream, " kline err",e);
			}
		}*/



        WebSocketHandler.sh.tradeCheck();
    }

}
