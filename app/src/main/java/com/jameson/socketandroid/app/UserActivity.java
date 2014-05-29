package com.jameson.socketandroid.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
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


public class UserActivity extends ActionBarActivity {

    private SocketIOClient mSocket;
    private Context mContext;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mContext = this;
        Intent intent = getIntent();
        final int user_id = intent.getIntExtra("USER_ID", 0);
        Log.d("[USER]", "[ACTIVITY]" + user_id);
        txtView = (TextView) findViewById(R.id.txtLocation);

        SocketIOClient.connect(AsyncHttpClient.getDefaultInstance(), "http://128.199.225.219:3000/", new ConnectCallback() {

            @Override
            public void onConnectCompleted(Exception ex, SocketIOClient socketIOClient) {

                if (socketIOClient.isConnected()) {
                    Log.d("[CONNECTED]", "[CONNECTED]");
                    User user = new User(user_id);
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        JSONArray jsonArray = new JSONArray();
                        jsonArray.put(json);
                        //socketIOClient.emit(json);
                        socketIOClient.emit("register user", jsonArray);
                    } catch (JSONException e) {
                       e.printStackTrace();
                    }

                    txtView.setText("CONNECTED");
                    //socketIOClient.emit(json);
                } else {
                    Log.d("[NOT CONNECTED]", "[NOT CONNECTED]" + ex.getMessage().toString());

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
                        try {
                            JSONObject jsonObject = argument.getJSONObject(0);
                            Log.d("[JSON]", jsonObject.getString("latitude").toString());
                            updateLocation(jsonObject);
                        } catch (JSONException ex) {
                            Log.d("[ERROR]", ex.getMessage().toString());
                            System.out.print(ex.getMessage().toString());
                        }
                    }
                });
            }
        });
    }

    private void updateLocation(JSONObject json) {
        txtView.setText("WTF");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);
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
