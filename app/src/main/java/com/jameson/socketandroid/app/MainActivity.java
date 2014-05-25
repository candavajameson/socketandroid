package com.jameson.socketandroid.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAgent = (Button)findViewById(R.id.btnAgent);
        Button btnUser = (Button)findViewById(R.id.btnUser);

        btnAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtAgent = (EditText)findViewById(R.id.txtAgent);
                int agent_id = Integer.parseInt(txtAgent.getText().toString());
                Intent intent = new Intent(getApplicationContext(), AgentActivity.class);
                intent.putExtra("AGENT_ID", agent_id);
                startActivity(intent);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText txtUser = (EditText)findViewById(R.id.txtUser);
                int user_id = Integer.parseInt(txtUser.getText().toString());
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("USER_ID", user_id);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
