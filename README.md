# cordova-plugin-gif
This plugin is designed to encode images to gif.

# Support
*   Android

# Installation
```
$ cordova plugin add https://github.com/jeno5980515/cordova-plugin-gif
```
# Example
```javascript
document.addEventListener('deviceready', function () {

	window.plugins.gif.init();
	
	var base64 = "" ;

	window.plugins.gif.addFrame(base64);

	window.plugins.gif.finish(successCallback,errorCallback);

	function successCallback(src){
		var img = new Image();
		img.src = src ;
		document.body.appendChild(img) ;
	}

	function errorCallback(err){
		alert(err);
	}

});
``` 
# Libraries Used
[android-gif-encoder](https://github.com/nbadal/android-gif-encoder)

# TODO
*	iOS
*	Setting
*	Content URI
