package com.alexey.pan.socketio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity {

// http://192.168.2.101/
    private Socket SocketIO;

    {
        try {
            IO.Options options = new IO.Options();
            //options.transports = new String[] { WebSocket.NAME };//default polling
            options.reconnection = true;
            options.reconnectionDelay = 5000;
            options.timeout = 5000;
            SocketIO = IO.socket("http://192.168.2.101",options);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--------------------------------------------------------------//
        SocketIO.connect();
        SocketIO.on("news", sockerCustomEvent); //Call custom event
        //--------------------------------------------------------------//

        //--------------------------------------------------------------//
        //Call anonym Emitter Function
        SocketIO.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... arg0) {
                Log.e("onCreate", "socket EVENT_CONNECT");
            }
        });
        //Call anonym Emitter Function
        //--------------------------------------------------------------//


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        SocketIO.disconnect();
    }



    //---------------------------------------------------------------------//
    // private Emitter.Listener sockerCustomEvent
    private Emitter.Listener sockerCustomEvent = new Emitter.Listener(){
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            Log.e("sockerCustomEvent" , "sockerCustomEvent" );
            try {
                String message = data.getString("message");
                String id = data.getString("id");
            } catch (JSONException e) {
                return;
            }
        }
    };
    // private Emitter.Listener sockerCustomEvent
    //---------------------------------------------------------------------//


}
