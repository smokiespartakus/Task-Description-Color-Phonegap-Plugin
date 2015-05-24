function TaskDescriptionColor() {
}

TaskDescriptionColor.prototype.keepAwake = function (color, successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "TaskDescriptionColor", "keepAwake", [color]);
};

TaskDescriptionColor.install = function () {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.TaskDescriptionColor = new TaskDescriptionColor();
  return window.plugins.TaskDescriptionColor;
};

cordova.addConstructor(TaskDescriptionColor.install);