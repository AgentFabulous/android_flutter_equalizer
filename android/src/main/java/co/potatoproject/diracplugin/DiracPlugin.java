package co.potatoproject.diracplugin;

import android.util.Log;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class DiracPlugin implements MethodCallHandler {
    private static final String TAG = "DiracPlugin";

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "dirac");
        channel.setMethodCallHandler(new DiracPlugin());
        Log.i(TAG, "Registration complete.");
    }

    @SuppressWarnings("WeakerAccess")
    public DiracPlugin() {
        Log.i(TAG, "Invoking init");
        DiracPluginService.diracInit();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onMethodCall(MethodCall call, Result result) {
        switch (call.method) {
            case "getPlatformVersion":
                result.success("Android " + android.os.Build.VERSION.RELEASE);
                break;
            case "isDiracSupported":
                DiracPluginService.diracInit();
                result.success(DiracPluginService.mDiracSupported);
                break;
            case "diracInit":
                DiracPluginService.diracInit();
                if (!DiracPluginService.mDiracSupported || DiracPluginService.mDirac == null)
                    result.error("exception", "Dirac unsupported", false);
                else
                    result.success(true);
                break;
            case "setMusic": {
                final int arg = call.argument("enable") ? 1 : 0;
                if (DiracPluginService.mDirac != null) DiracPluginService.mDirac.setMusic(arg);
                result.success(true);
                break;
            }
            case "setHifiMode": {
                final int arg = call.argument("mode");
                if (DiracPluginService.mDirac != null) DiracPluginService.mDirac.setHifiMode(arg);
                result.success(true);
                break;
            }
            case "setLevel": {
                final int band = call.argument("band");
                final float level = Float.parseFloat(call.argument("level").toString());
                if (DiracPluginService.mDirac != null) DiracPluginService.mDirac.setLevel(band, level);
                break;
            }
            case "setHeadsetType": {
                final int arg = call.argument("type");
                if (DiracPluginService.mDirac != null) DiracPluginService.mDirac.setHeadsetType(arg);
                result.success(true);
                break;
            }
            case "setMovie": {
                final int arg = call.argument("enable") ? 1 : 0;
                if (DiracPluginService.mDirac != null) DiracPluginService.mDirac.setMovie(arg);
                result.success(true);
                break;
            }
            case "setMovieMode": {
                final int arg = call.argument("mode");
                if (DiracPluginService.mDirac != null) DiracPluginService.mDirac.setMovieMode(arg);
                result.success(true);
                break;
            }
            case "setSpeakerStereoMode": {
                final int arg = call.argument("mode");
                if (DiracPluginService.mDirac != null) DiracPluginService.mDirac.setSpeakerStereoMode(arg);
                result.success(true);
                break;
            }
            case "getMusic":
                if (DiracPluginService.mDirac != null) result.success(DiracPluginService.mDirac.getMusic());
                break;
            case "getHeadsetType":
                if (DiracPluginService.mDirac != null) result.success(DiracPluginService.mDirac.getHeadsetType());
                break;
            case "getHifiMode":
                if (DiracPluginService.mDirac != null) result.success(DiracPluginService.mDirac.getHifiMode());
                break;
            case "getLevel": {
                final int band = call.argument("band");
                if (DiracPluginService.mDirac != null) result.success(DiracPluginService.mDirac.getLevel(band));
                break;
            }
            case "getMovie":
                if (DiracPluginService.mDirac != null) result.success(DiracPluginService.mDirac.getMovie());
                break;
            case "getMovieMode":
                if (DiracPluginService.mDirac != null) result.success(DiracPluginService.mDirac.getMovieMode());
                break;
            case "getSpeakerStereoMode":
                if (DiracPluginService.mDirac != null) result.success(DiracPluginService.mDirac.getSpeakerStereoMode());
                break;
            case "checkField": {
                final String arg = call.argument("field");
                if (DiracPluginService.mDirac != null) result.success(DiracPluginService.mDirac.checkField(arg));
                break;
            }
            case "release":
                if (DiracPluginService.mDirac != null)
                    DiracPluginService.mDirac.release();
                result.success(true);
                break;
            default:
                result.notImplemented();
        }
    }


}
