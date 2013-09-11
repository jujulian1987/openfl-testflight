package testflight.android;

import org.haxe.nme.GameActivity;
import com.testflightapp.lib.TestFlight;

public class TestFlightOpenFL {

    public static void takeOff(String token){
        TestFlight.takeOff(GameActivity.getInstance().getApplication(), token);
    }
}