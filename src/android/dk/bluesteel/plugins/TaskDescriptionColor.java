package dk.bluesteel.plugins;

import android.view.WindowManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.ActivityManager;
import android.os.Build;
import android.graphics.Color;
import android.view.Window;
import android.view.View;
import android.content.Context;

public class TaskDescriptionColor extends CordovaPlugin {

  private static final String SET_COLOR = "setColor";

  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    try {
      if (SET_COLOR.equals(action)) {
        final JSONArray args2 = args;
        cordova.getActivity().runOnUiThread(
            new Runnable() {
              public void run() {
        	  int color = Color.parseColor("#000000");
              try {
              	color = Color.parseColor(args2.getString(0));
              } catch(Exception e) {

              }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                  cordova.getActivity().setTaskDescription(
                    new ActivityManager.TaskDescription(null, null, color)
                    );
                    
                  Window window = cordova.getActivity().getWindow();
                  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                  window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                  window.setStatusBarColor(color);
                  callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                } else {
                  callbackContext.error("Old phone?");
                }
              }
            });
        return true;

      } else {
        callbackContext.error("set description color." + action + " is not a supported function. Did you mean '" + SET_COLOR + "'?");
        return false;
      }
    } catch (Exception e) {
      callbackContext.error(e.getMessage());
      return false;
    }
  }
}