package com.test.plugin.android;

import android.app.Activity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zoho.deskportalsdk.DeskConfig;
import com.zoho.deskportalsdk.ZohoDeskPortalSDK;

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
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        else if (action.equals("initialize")) {
            this.initialize(args, callbackContext);
            return true;
        }
        else if (action.equals("startDeskHelpCenter")) {
            this.startDeskHelpCenter(callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
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

    private void startDeskHelpCenter(CallbackContext callbackContext) {
        deskPortalSDK.startDeskHomeScreen(activity);
    }
}
