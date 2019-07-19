var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'TestPluginForCordovaAndroid', 'coolMethod', [arg0]);
};

exports.initialize = function (arg0, success, error) {
    exec(success, error, 'TestPlugin', 'initialize', [arg0]);
};

exports.startDeskHelpCenter = function (arg0, success, error) {
    exec(success, error, 'TestPlugin', 'startDeskHelpCenter', [arg0]);
};
