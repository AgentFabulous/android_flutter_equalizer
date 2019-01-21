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
    private boolean mDiracSupported = false;

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
            case "isDiracSupported":
                diracInit();
                result.success(mDiracSupported);
                break;
            case "diracInit":
                diracInit();
                if (!mDiracSupported || mDirac == null)
                    result.error("exception", "Dirac unsupported", false);
                else
                    result.success(true);
                break;
            case "setMusic": {
                final int arg = call.argument("enable") ? 1 : 0;
                if (mDirac != null) mDirac.setMusic(arg);
                result.success(true);
                break;
            }
            case "setHifiMode": {
                final int arg = call.argument("mode");
                if (mDirac != null) mDirac.setHifiMode(arg);
                result.success(true);
                break;
            }
            case "setLevel": {
                final int band = call.argument("band");
                final float level = Float.parseFloat(call.argument("level").toString());
                if (mDirac != null) mDirac.setLevel(band, level);
                break;
            }
            case "setHeadsetType": {
                final int arg = call.argument("type");
                if (mDirac != null) mDirac.setHeadsetType(arg);
                result.success(true);
                break;
            }
            case "setMovie": {
                final int arg = call.argument("enable") ? 1 : 0;
                if (mDirac != null) mDirac.setMovie(arg);
                result.success(true);
                break;
            }
            case "setMovieMode": {
                final int arg = call.argument("mode");
                if (mDirac != null) mDirac.setMovieMode(arg);
                result.success(true);
                break;
            }
            case "setSpeakerStereoMode": {
                final int arg = call.argument("mode");
                if (mDirac != null) mDirac.setSpeakerStereoMode(arg);
                result.success(true);
                break;
            }
            case "getMusic":
                if (mDirac != null) result.success(mDirac.getMusic());
                break;
            case "getHeadsetType":
                if (mDirac != null) result.success(mDirac.getHeadsetType());
                break;
            case "getHifiMode":
                if (mDirac != null) result.success(mDirac.getHifiMode());
                break;
            case "getLevel": {
                final int band = call.argument("band");
                if (mDirac != null) result.success(mDirac.getLevel(band));
                break;
            }
            case "getMovie":
                if (mDirac != null) result.success(mDirac.getMovie());
                break;
            case "getMovieMode":
                if (mDirac != null) result.success(mDirac.getMovieMode());
                break;
            case "getSpeakerStereoMode":
                if (mDirac != null) result.success(mDirac.getSpeakerStereoMode());
                break;
            case "checkField": {
                final String arg = call.argument("field");
                if (mDirac != null) result.success(mDirac.checkField(arg));
                break;
            }
            case "release":
                if (mDirac != null)
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
            mDiracSupported = true;
        } catch (RuntimeException e) {
            Log.e(TAG, "Wrapper Creation failure! Dirac may be unsupported on this device\n" + e);
            mDiracSupported = false;
        }
    }
}
