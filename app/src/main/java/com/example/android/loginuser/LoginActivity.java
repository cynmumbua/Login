package com.example.android.loginuser;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final Button button = (Button) findViewById(R.id.button);
        final TextView registerLink = (TextView) findViewById(R.id.textView);

       registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final String username = editText.getText().toString();
               final String password = editText2.getText().toString();

               Response.Listener<String> responseListener = new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       try {
                           JSONObject jsonResponse = new JSONObject(response);
                           boolean success = jsonResponse.getBoolean("success");
                           if (success){
                               String name =jsonResponse.getString("name");
                               int age = jsonResponse.getInt("age");
                               Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                               intent.putExtra("name", name);
                               intent.putExtra("username", username);
                               intent.putExtra("age", age);
                               LoginActivity.this.startActivity(intent);

                           }
                           else {
                               AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                               builder.setMessage("Login Failed")
                                       .setNegativeButton("Retry, or Register above", null)
                                       .create()
                                       .show();
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }

                   }
               };
               LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
               RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
               queue.add(loginRequest);

           }
       });
    }
}