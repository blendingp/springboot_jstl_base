package com.example.board.service;

import io.socket.client.IO;
import io.socket.emitter.Emitter;

public class liveratesise {

    private io.socket.client.Socket socket = null;
    public liveratesise(){
       // sisestart();
    }

    public void sisestart2(){

    }

    public void sisestart(){
        try{
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            socket = IO.socket("https://wss3.live-rates.com");
            socket.connect();
            String key = "a1ea04e8d3"; 
            //String key = 'XXXXXXX' // YOUR LIVE-RATES SUBSCRIPTION KEY
              
            socket.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~aaaaaaaaaaaaaaaa~~~");
                    // if you want to subscribe only specific instruments, emit instruments. To receive all instruments, comment the line below.
                    String[] instruments = {"EURUSD", "USDJPY", "BTCUSD", "ETH"};
                    socket.emit("instruments", instruments);
                  
                    socket.emit("key", key);
                }
            });
    
    
            socket.on("rates", new Emitter.Listener() {
              @Override
              public void call(Object... args) {
                //Do what you want with the Incoming Rates... Enjoy!
                try {
                  System.out.println("msg:"+args.toString());
                } catch (Exception e) {
                  System.out.println(e);
                }
              }
            });
        }catch(Exception e){

        }

    }
    
}
