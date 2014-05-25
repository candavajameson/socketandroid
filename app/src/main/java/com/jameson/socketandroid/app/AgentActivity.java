package com.jameson.socketandroid.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jameson.socketandroid.app.com.jameson.socketandroid.app.model.Agent;
import com.jameson.socketandroid.app.com.jameson.socketandroid.app.model.Request;
import com.jameson.socketandroid.app.com.jameson.socketandroid.app.model.User;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.socketio.Acknowledge;
import com.koushikdutta.async.http.socketio.ConnectCallback;
import com.koushikdutta.async.http.socketio.EventCallback;
import com.koushikdutta.async.http.socketio.SocketIOClient;
import com.koushikdutta.async.http.socketio.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class AgentActivity extends ActionBarActivity {

    private SocketIOClient mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);

        Intent intent = getIntent();
        final int agent_id = intent.getIntExtra("AGENT_ID", 0);


        SocketIOClient.connect(AsyncHttpClient.getDefaultInstance(), "http://128.199.225.219:3000", new ConnectCallback() {
            @Override
            public void onConnectCompleted(Exception ex, SocketIOClient socketIOClient) {

                if (socketIOClient.isConnected()) {

                    Agent agent = new Agent(agent_id);
                    Gson gson = new Gson();
                    String json = gson.toJson(agent);

                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(json);
                    socketIOClient.emit("register user", jsonArray);
                    //socketIOClient.emit(json);
                }

                socketIOClient.setStringCallback(new StringCallback() {
                    @Override
                    public void onString(String string, Acknowledge acknowledge) {
                        System.out.println(string);
                        Log.d("SOCKET IO STRING CALLBACK", "" + string);
                    }
                });

                socketIOClient.on("event", new EventCallback() {
                    @Override
                    public void onEvent(JSONArray argument, Acknowledge acknowledge) {
                        Log.d("[DATA]", argument.toString());

                    }
                });
                mSocket = socketIOClient;
            }
        });


        Button btnSendGeo = (Button)findViewById(R.id.btnSend);
        btnSendGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // EditText editRequestId = (EditText) findViewById(R.id.txtRequest);
                Random rand = new Random();
                int randomNum = rand.nextInt((100 - 50) + 1) + 50;
                Request request = new Request(6, "1233." + randomNum, "27.9273" + randomNum, agent_id);
                Gson gson = new Gson();
                String json = gson.toJson(request);
                Log.d("[DATA]", json);
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(json);

                mSocket.emit("send geo", jsonArray);
            }
        });
    }

    private void sendGeo(int agent_id) {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.agent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
