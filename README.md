TestFlight SDK for OpenFL
=========================

TestFlight comes with a set of SDK calls that can add helpful feedback from your app. Checkpoints are especially useful for games.
Android is integreated and works but Testflight SDK doesn't seem to be compatible with OpenFL yet. 
According to the docs you need to implement TestFlight in your own Application:

templates/android/MyApplication.java
```java
package ::APP_PACKAGE::;

import com.testflightapp.lib.TestFlight;
import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize TestFlight with your app token.
        TestFlight.takeOff(this, "YOUR_APP_TOKEN");
        TestFlight.log("It works!");
    }
}
```

Add this to your project.xml:
```xml
<template path="templates/android/MyApplication.java" rename="src/YOUR/PACKAGE/MyApplication.java" if="android" />
```

You also need to register the MyApplication in your Android Manifest (android:name="MyApplication"): 

templates/android/AndroidManifest.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android" android:installLocation="::ANDROID_INSTALL_LOCATION::" android:versionCode="::APP_BUILD_NUMBER::" android:versionName="::APP_VERSION::" package="::APP_PACKAGE::">

	<application android:label="::APP_TITLE::" android:name="MyApplication" android:debuggable="true"::if (HAS_ICON):: android:icon="@drawable/icon"::end::>
		
		::if WIN_REQUIRE_SHADERS::<uses-feature android:glEsVersion="0x00020000" android:required="true" />::elseif WIN_ALLOW_SHADERS::<uses-feature android:glEsVersion="0x00020000" android:required="false" />::end::
		
		<activity android:name="MainActivity" android:label="::APP_TITLE::" android:configChanges="keyboard|keyboardHidden|orientation" android:screenOrientation="sensorLandscape">
			
			<intent-filter>
				<action android:name="android.intent.action.MAIN" />
				<category android:name="android.intent.category.LAUNCHER" />
				<category android:name="tv.ouya.intent.category.GAME" />
			</intent-filter>
			
		</activity>
		
	</application>

<uses-sdk android:minSdkVersion="::ANDROID_MINIMUM_SDK_VERSION::" android:targetSdkVersion="::ANDROID_TARGET_SDK_VERSION::"/>
	
	<uses-permission android:name="android.permission.WAKE_LOCK" />
	<uses-permission android:name="android.permission.INTERNET" />
	<uses-permission android:name="android.permission.VIBRATE" />
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	
</manifest>
```

Add this to your project.xml:
```xml
<template path="templates/android/AndroidManifest.xml" rename="AndroidManifest.xml" if="android" />
```

Still it does not seem to work. I am able to get the message "TestFlight has taken off. Version 1.0" but checkpoints 
and log messages wont be recieved. Perhaps I am missing something. If you get it to work please report back if you 
have the time. Thanks!

A Brief Example for iOS and Android:
---------------

```haxe
import com.testflightapp.TestFlight;

class Example
{

	private function new()
	{
		// do this once at the beginning of your app
		TestFlight.takeOff("your-testflight-token");

		// use these methods to send data to TestFlight
		TestFlight.passCheckpoint("check1");
		TestFlight.remoteLog("Hello world");

		// only works in iOS
		TestFlight.submitFeedback("This is a comment about the app");
		TestFlight.customInfo("foo", ["bar", 1]);
	}

}
```
