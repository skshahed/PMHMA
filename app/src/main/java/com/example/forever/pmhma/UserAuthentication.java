package com.example.forever.pmhma;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Forever on 4/5/2017.
 */

public class UserAuthentication {
    private static final String PREFERENCE_NAME = "User Authenticate";
    private static final String USER_NAME = "user_name";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PASSWORD = "user_password";
    private static final String DEFAULT_MSG = "No Value Found";


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public UserAuthentication(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        editor =sharedPreferences.edit();
    }

    public void saveUser(String username, String email,String password){

        editor.putString(USER_NAME,username);
        editor.putString(USER_EMAIL,email);
        editor.putString(USER_PASSWORD,password);
        editor.commit();
    }
    public String getName(){
        return sharedPreferences.getString(USER_EMAIL,DEFAULT_MSG);
    }
    public String getEmail(){
        return sharedPreferences.getString(USER_NAME,DEFAULT_MSG);
    }
    public String getPassword(){
        return sharedPreferences.getString(USER_PASSWORD,DEFAULT_MSG);
    }
}
