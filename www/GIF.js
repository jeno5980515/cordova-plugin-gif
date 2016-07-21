var PLUGIN_NAME = "GIF";

function GIF(){

}

GIF.prototype.addFrame = function(input,data){
	var type = "base64" ;
	if ( data !== undefined )
		type = data.type || "base64" ;
	if ( type === "base64" )
		cordova.exec(null, null, PLUGIN_NAME, "addFrameBase64", [input]);
	else if ( type === "fileURI" )
		cordova.exec(null, null, PLUGIN_NAME, "addFrameFileURI", [input]);
}

GIF.prototype.init = function(option){
	cordova.exec(null, null, PLUGIN_NAME, "init", []);
}

GIF.prototype.finish = function(successCallback,errorCallback,data){
	var path = "/sdcard/output.gif" ;
	if ( data !== undefined )
		path = data.path || "/sdcard/output.gif" ;
	cordova.exec(successCallback, errorCallback, PLUGIN_NAME, "finish", [path]);
}

GIF.install = function () {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.gif = new GIF();
  return window.plugins.gif;
};

cordova.addConstructor(GIF.install);
