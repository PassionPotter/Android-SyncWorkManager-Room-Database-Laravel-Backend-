package com.androidigniter.excelapp;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by AndroidIgniter on 23 Mar 2019 020.
 */

public class SessionHandler {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USER = "user";
    private static final String KEY_EXPIRES = "expires";
    private static final String KEY_TOKEN = "access_token";
    private static final String KEY_ROLE = "role";
    private static final String KEY_EMPTY = "";
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;

    public SessionHandler(Context mContext) {
        this.mContext = mContext;
        mPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.mEditor = mPreferences.edit();
    }

    /**
     * Logs in the user by saving user details and setting session
     *  @param user
     * @param token
     */
    public void loginUser(JSONObject user, String token, String role) {
        mEditor.putString(KEY_USER, user.toString());
        mEditor.putString(KEY_TOKEN, token);
        mEditor.putString(KEY_ROLE, role);
        mEditor.commit();
    }

    /**
     * Checks whether user is logged in
     *
     * @return
     */
    public boolean isLoggedIn() {
        return mPreferences.contains(KEY_TOKEN);
    }

    /**
     * Fetches and returns user details
     *
     * @return user details
     */
    public User getUserDetails() {
        //Check if user is logged in first
        if (!isLoggedIn()) {
            return null;
        }
        JSONObject userObject = null;
        User user = new User();
        try {
            userObject = new JSONObject(mPreferences.getString(KEY_USER, KEY_EMPTY));
            user.setEmail(userObject.getString("email"));
            user.setName(userObject.getString("name"));
            user.setRole(mPreferences.getString(KEY_ROLE, KEY_EMPTY));
            user.setToken(mPreferences.getString(KEY_TOKEN, KEY_EMPTY));
            //user.setSessionExpiryDate(new Date(mPreferences.getLong(KEY_EXPIRES, 0)));
        } catch (JSONException e) {
            e.printStackTrace();
        }



        return user;
    }


    public void changeRole(String role) {
        mEditor.remove(KEY_ROLE);
        mEditor.putString(KEY_ROLE, role);
    }

    /**
     * Logs out user by clearing the session
     */


    public void logoutUser(){
        mEditor.clear();
        mEditor.commit();
    }

}
