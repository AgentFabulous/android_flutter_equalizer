package co.potatoproject.diracplugin;

import android.util.Log;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class DiracPlugin implements MethodCallHandler {
    private static final String TAG = "DiracPlugin";

    private static DiracSoundWrapper mDirac;

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "dirac");
        channel.setMethodCallHandler(new DiracPlugin());
        Log.i(TAG, "Registration complete.");
    }

    @SuppressWarnings("WeakerAccess")
    DiracPlugin() {
        Log.i(TAG, "Invoking init");
        diracInit();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onMethodCall(MethodCall call, Result result) {
        switch (call.method) {
            case "getPlatformVersion":
                result.success("Android " + android.os.Build.VERSION.RELEASE);
                break;
            case "diracInit":
                diracInit();
                result.success(true);
                break;
            case "setMusic": {
                final int arg = call.argument("enable") ? 1 : 0;
                mDirac.setMusic(arg);
                result.success(true);
                break;
            }
            case "setHifiMode": {
                final int arg = call.argument("mode");
                mDirac.setHifiMode(arg);
                result.success(true);
                break;
            }
            case "setLevel": {
                final int band = call.argument("band");
                final float level = Float.parseFloat(call.argument("level").toString());
                mDirac.setLevel(band, level);
                break;
            }
            case "setHeadsetType": {
                final int arg = call.argument("type");
                mDirac.setHeadsetType(arg);
                result.success(true);
                break;
            }
            case "setMovie": {
                final int arg = call.argument("enable") ? 1 : 0;
                mDirac.setMovie(arg);
                result.success(true);
                break;
            }
            case "setMovieMode": {
                final int arg = call.argument("mode");
                mDirac.setMovieMode(arg);
                result.success(true);
                break;
            }
            case "setSpeakerStereoMode": {
                final int arg = call.argument("mode");
                mDirac.setSpeakerStereoMode(arg);
                result.success(true);
                break;
            }
            case "getMusic":
                result.success(mDirac.getMusic());
                break;
            case "getHeadsetType":
                result.success(mDirac.getHeadsetType());
                break;
            case "getHifiMode":
                result.success(mDirac.getHifiMode());
                break;
            case "getLevel": {
                final int band = call.argument("band");
                result.success(mDirac.getLevel(band));
                break;
            }
            case "getMovie":
                result.success(mDirac.getMovie());
                break;
            case "getMovieMode":
                result.success(mDirac.getMovieMode());
                break;
            case "getSpeakerStereoMode":
                result.success(mDirac.getSpeakerStereoMode());
                break;
            case "checkField": {
                final String arg = call.argument("field");
                result.success(mDirac.checkField(arg));
                break;
            }
            case "release":
                mDirac.release();
                result.success(true);
                break;
            default:
                result.notImplemented();
        }
    }

    private void diracInit() {
        try {
            mDirac = new DiracSoundWrapper(0, 0);
        } catch (RuntimeException e){
            Log.e(TAG, "Wrapper Creation failure! Dirac may be unsupported on this device\n" + e);
        }
    }
}
