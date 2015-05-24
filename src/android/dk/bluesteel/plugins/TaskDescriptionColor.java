package dk.bluesteel.plugins;

import android.view.WindowManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class TaskDescriptionColor extends CordovaPlugin {

  private static final String SET_COLOR = "setColor";

  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    try {
      if (SET_COLOR.equals(action)) {
        cordova.getActivity().runOnUiThread(
            new Runnable() {
              public void run() {
                int myColor = 0xffff00ff;
                TaskDescription taskDesc = new TaskDescription(null, null, myColor);
                cordova.getActivity().setTaskDescription(taskDesc);
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
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