package co.potatoproject.effectsplugin;

import android.util.Log;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class EffectsPlugin implements MethodCallHandler {
    private static final String TAG = "EffectsPlugin";

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "dirac");
        channel.setMethodCallHandler(new EffectsPlugin());
        Log.i(TAG, "Registration complete.");
    }

    @SuppressWarnings("WeakerAccess")
    public EffectsPlugin() {
        Log.i(TAG, "Invoking init");
        EffectsPluginService.diracInit();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onMethodCall(MethodCall call, Result result) {
        switch (call.method) {
            case "getPlatformVersion":
                result.success("Android " + android.os.Build.VERSION.RELEASE);
                break;
            case "isDiracSupported":
                EffectsPluginService.diracInit();
                result.success(EffectsPluginService.mDiracSupported);
                break;
            case "diracInit":
                EffectsPluginService.diracInit();
                if (!EffectsPluginService.mDiracSupported || EffectsPluginService.mDirac == null)
                    result.error("exception", "Dirac unsupported", false);
                else
                    result.success(true);
                break;
            case "setMusic": {
                final int arg = call.argument("enable") ? 1 : 0;
                if (EffectsPluginService.mDirac != null) EffectsPluginService.mDirac.setMusic(arg);
                result.success(true);
                break;
            }
            case "setHifiMode": {
                final int arg = call.argument("mode");
                if (EffectsPluginService.mDirac != null) EffectsPluginService.mDirac.setHifiMode(arg);
                result.success(true);
                break;
            }
            case "setLevel": {
                final int band = call.argument("band");
                final float level = Float.parseFloat(call.argument("level").toString());
                if (EffectsPluginService.mDirac != null) EffectsPluginService.mDirac.setLevel(band, level);
                break;
            }
            case "setHeadsetType": {
                final int arg = call.argument("type");
                if (EffectsPluginService.mDirac != null) EffectsPluginService.mDirac.setHeadsetType(arg);
                result.success(true);
                break;
            }
            case "setMovie": {
                final int arg = call.argument("enable") ? 1 : 0;
                if (EffectsPluginService.mDirac != null) EffectsPluginService.mDirac.setMovie(arg);
                result.success(true);
                break;
            }
            case "setMovieMode": {
                final int arg = call.argument("mode");
                if (EffectsPluginService.mDirac != null) EffectsPluginService.mDirac.setMovieMode(arg);
                result.success(true);
                break;
            }
            case "setSpeakerStereoMode": {
                final int arg = call.argument("mode");
                if (EffectsPluginService.mDirac != null) EffectsPluginService.mDirac.setSpeakerStereoMode(arg);
                result.success(true);
                break;
            }
            case "getMusic":
                if (EffectsPluginService.mDirac != null) result.success(EffectsPluginService.mDirac.getMusic());
                break;
            case "getHeadsetType":
                if (EffectsPluginService.mDirac != null) result.success(EffectsPluginService.mDirac.getHeadsetType());
                break;
            case "getHifiMode":
                if (EffectsPluginService.mDirac != null) result.success(EffectsPluginService.mDirac.getHifiMode());
                break;
            case "getLevel": {
                final int band = call.argument("band");
                if (EffectsPluginService.mDirac != null) result.success(EffectsPluginService.mDirac.getLevel(band));
                break;
            }
            case "getMovie":
                if (EffectsPluginService.mDirac != null) result.success(EffectsPluginService.mDirac.getMovie());
                break;
            case "getMovieMode":
                if (EffectsPluginService.mDirac != null) result.success(EffectsPluginService.mDirac.getMovieMode());
                break;
            case "getSpeakerStereoMode":
                if (EffectsPluginService.mDirac != null) result.success(EffectsPluginService.mDirac.getSpeakerStereoMode());
                break;
            case "checkField": {
                final String arg = call.argument("field");
                if (EffectsPluginService.mDirac != null) result.success(EffectsPluginService.mDirac.checkField(arg));
                break;
            }
            case "release":
                if (EffectsPluginService.mDirac != null)
                    EffectsPluginService.mDirac.release();
                result.success(true);
                break;
            default:
                result.notImplemented();
        }
    }


}
