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
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "potatoEffects");
        channel.setMethodCallHandler(new EffectsPlugin());
        Log.i(TAG, "Registration complete.");
    }

    @SuppressWarnings("WeakerAccess")
    public EffectsPlugin() {
        Log.i(TAG, "Invoking init");
        EffectsPluginService.diracInit();
        EffectsPluginService.eqInit();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onMethodCall(MethodCall call, Result result) {
        final boolean isDirac;
        if (call.argument("dirac") == null)
            isDirac = false;
        else
            isDirac = call.argument("dirac");
        switch (call.method) {
            case "init":
                if (isDirac) {
                    EffectsPluginService.diracInit();
                    if (!EffectsPluginService.mDiracSupported || EffectsPluginService.mDirac == null)
                        result.error("exception", "Dirac unsupported", false);
                    else
                        result.success(true);
                } else {
                    EffectsPluginService.eqInit();
                    if (EffectsPluginService.mEqualizer == null || !EqualizerWrapper.ismIsEQInitialized())
                        result.error("exception", "Equalizer init failure", false);
                    else
                        result.success(true);
                }
                break;
            case "setMusic": {
                final int arg = call.argument("enable") ? 1 : 0;
                if (isDirac) {
                    if (EffectsPluginService.mDirac != null)
                        EffectsPluginService.mDirac.setMusic(arg);
                    result.success(true);
                } else {
                    if (EffectsPluginService.mEqualizer != null)
                        EffectsPluginService.mEqualizer.setEnabled(arg == 1);
                }
                break;
            }
            case "setLevel": {
                final int band = call.argument("band");
                final float level = Float.parseFloat(call.argument("level").toString());
                if (isDirac) {
                    if (EffectsPluginService.mDirac != null)
                        EffectsPluginService.mDirac.setLevel(band, level);
                } else {
                    if (EffectsPluginService.mEqualizer != null)
                        EffectsPluginService.mEqualizer.setBandLevel((short) band, (short) level);
                }
                break;
            }
            case "getMusic":
                if (isDirac) {
                    if (EffectsPluginService.mDirac != null)
                        result.success(EffectsPluginService.mDirac.getMusic());
                } else {
                    if (EffectsPluginService.mEqualizer != null)
                        result.success(EffectsPluginService.mEqualizer.getEnabled());
                }
                break;
            case "getLevel": {
                final int band = call.argument("band");
                if (isDirac) {
                    if (EffectsPluginService.mDirac != null)
                        result.success(EffectsPluginService.mDirac.getLevel(band));
                } else {
                    if (EffectsPluginService.mEqualizer != null)
                        result.success(EffectsPluginService.mEqualizer.getBandLevel((short) band));
                }
                break;
            }
            case "getNumBands":
                if (isDirac)
                    result.success(7);
                else
                    result.success(EffectsPluginService.mEqualizer.getNumberOfBands());
                break;
            case "release":
                if (EffectsPluginService.mDirac != null)
                    EffectsPluginService.mDirac.release();
                result.success(true);
                break;

            /* Start Dirac Additions */
            case "isDiracSupported":
                EffectsPluginService.diracInit();
                result.success(EffectsPluginService.mDiracSupported);
                break;
            case "setHifiMode": {
                final int arg = call.argument("mode");
                if (EffectsPluginService.mDirac != null)
                    EffectsPluginService.mDirac.setHifiMode(arg);
                result.success(true);
                break;
            }
            case "setHeadsetType": {
                final int arg = call.argument("type");
                if (EffectsPluginService.mDirac != null)
                    EffectsPluginService.mDirac.setHeadsetType(arg);
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
                if (EffectsPluginService.mDirac != null)
                    EffectsPluginService.mDirac.setMovieMode(arg);
                result.success(true);
                break;
            }
            case "setSpeakerStereoMode": {
                final int arg = call.argument("mode");
                if (EffectsPluginService.mDirac != null)
                    EffectsPluginService.mDirac.setSpeakerStereoMode(arg);
                result.success(true);
                break;
            }
            case "getHeadsetType":
                if (EffectsPluginService.mDirac != null)
                    result.success(EffectsPluginService.mDirac.getHeadsetType());
                break;
            case "getHifiMode":
                if (EffectsPluginService.mDirac != null)
                    result.success(EffectsPluginService.mDirac.getHifiMode());
                break;
            case "getMovie":
                if (EffectsPluginService.mDirac != null)
                    result.success(EffectsPluginService.mDirac.getMovie());
                break;
            case "getMovieMode":
                if (EffectsPluginService.mDirac != null)
                    result.success(EffectsPluginService.mDirac.getMovieMode());
                break;
            case "getSpeakerStereoMode":
                if (EffectsPluginService.mDirac != null)
                    result.success(EffectsPluginService.mDirac.getSpeakerStereoMode());
                break;
            case "checkField": {
                final String arg = call.argument("field");
                if (EffectsPluginService.mDirac != null)
                    result.success(EffectsPluginService.mDirac.checkField(arg));
                break;
            }
            /* End Dirac Additions */
            default:
                result.notImplemented();
        }
    }


}
