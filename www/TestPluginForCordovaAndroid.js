var exec = require('cordova/exec');


exports.initialize = function (arg0, success, error) {
    exec(success, error, 'TestPluginForCordovaAndroid', 'initialize', [arg0]);
};

exports.startDeskHomeScreen = function (arg0, success, error) {
    exec(success, error, 'TestPluginForCordovaAndroid', 'startDeskHomeScreen', [arg0]);
};

exports.startNewTicket = function (arg0, success, error) {
    exec(success, error, 'TestPluginForCordovaAndroid', 'startNewTicket', [arg0]);
};

exports.startHelpCenter = function (arg0, success, error) {
    exec(success, error, 'TestPluginForCordovaAndroid', 'startHelpCenter', [arg0]);
};

exports.startCommunity = function (arg0, success, error) {
    exec(success, error, 'TestPluginForCordovaAndroid', 'startCommunity', [arg0]);
};

exports.startTickets = function (arg0, success, error) {
    exec(success, error, 'TestPluginForCordovaAndroid', 'startTickets', [arg0]);
};

exports.startLiveChat = function (arg0, success, error) {
    exec(success, error, 'TestPluginForCordovaAndroid', 'startLiveChat', [arg0]);
};

exports.setUserToken = function (arg0, success, error) {
    exec(success, error, 'TestPluginForCordovaAndroid', 'setUserToken', [arg0]);
};
