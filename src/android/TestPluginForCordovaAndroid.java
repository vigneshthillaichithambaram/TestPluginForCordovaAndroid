package com.test.plugin.android;

import android.app.Activity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zoho.deskportalsdk.DeskConfig;
import com.zoho.deskportalsdk.ZohoDeskPortalSDK;
import com.zoho.deskportalsdk.android.network.DeskCallback;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class TestPluginForCordovaAndroid extends CordovaPlugin {

    private ZohoDeskPortalSDK deskPortalSDK;
    private Activity activity;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("initialize")) {
            this.initialize(args, callbackContext);
            return true;
        }
        if (action.equals("startDeskHomeScreen")) {
            this.startDeskHomeScreen(callbackContext);
            return true;
        }
        if(action.equals("startNewTicket")) {
            this.startNewTicket(callbackContext);
            return true;
        }
        if(action.equals("startHelpCenter")) {
            this.startHelpCenter(callbackContext);
            return true;
        }
        if(action.equals("startCommunity")) {
            this.startCommunity(callbackContext);
            return true;
        }
        if(action.equals("startTickets")) {
            this.startTickets(callbackContext);
            return true;
        }
        if(action.equals("startLiveChat")) {
            this.startLiveChat(callbackContext);
            return true;
        }
        if(action.equals("setUserToken")) {
            this.setUserToken(args.getString(0), callbackContext);
            return true;
        }
        return false;
    }

    private void initialize(JSONArray array, CallbackContext callbackContext) {
        activity = cordova.getActivity();
        deskPortalSDK = ZohoDeskPortalSDK.getInstance(cordova.getActivity().getApplication());
        DeskConfig config = new DeskConfig.Builder().build();
        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(array.getString(0));
            JsonArray args = jsonElement.getAsJsonArray();
            long orgId = args.get(0).getAsLong();
            String appId = args.get(1).getAsString();
            deskPortalSDK.initDesk(orgId, appId, ZohoDeskPortalSDK.DataCenter.US, config);
        } catch (Exception e) {
            if(callbackContext != null) {
                callbackContext.error("Error while initialize");
            }
        }
    }

    private boolean initializeCheck(CallbackContext callbackContext) {
        if(activity == null) {
            callbackContext.error("Plugin is not initialised.");
        }
        return activity != null;
    }

    private void startDeskHomeScreen(CallbackContext callbackContext) {
        if(initializeCheck(callbackContext)) {
            deskPortalSDK.startDeskHomeScreen(activity);
        }
    }

    private void startNewTicket(CallbackContext callbackContext) {
        if(initializeCheck(callbackContext)) {
            deskPortalSDK.startDeskHomeScreen(activity);
        }
    }

    private void startHelpCenter(CallbackContext callbackContext) {
        if(initializeCheck(callbackContext)) {
            deskPortalSDK.startHelpCenter(activity);
        }
    }

    private void startCommunity(CallbackContext callbackContext) {
        if(initializeCheck(callbackContext)) {
            deskPortalSDK.startCommunity(activity);
        }
    }

    private void startTickets(CallbackContext callbackContext) {
        if(initializeCheck(callbackContext)) {
            deskPortalSDK.startTickets(activity);
        }
    }

    private void startLiveChat(CallbackContext callbackContext) {
        if(initializeCheck(callbackContext)) {
            deskPortalSDK.startLiveChat();
        }
    }

    private void setUserToken(String userToken, CallbackContext callbackContext) {
        if(initializeCheck(callbackContext)) {
            deskPortalSDK.setUserToken(userToken, new DeskCallback.DeskSetUserCallback() {
                @Override
                public void onUserSetSuccess() {
                    callbackContext.success();
                }

                @Override
                public void onException(DeskException e) {
                    callbackContext.error(e.getMessage());
                }
            });
        }
    }
}
