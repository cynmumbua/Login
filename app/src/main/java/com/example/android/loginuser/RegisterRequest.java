package com.example.android.loginuser;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 12/18/2017.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="https://cynmuinde.000webhostapp.com/Registers.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, int age, String password,int phone, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("age", age + "");
        params.put("phone", phone + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
