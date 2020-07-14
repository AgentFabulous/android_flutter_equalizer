package co.potatoproject.effectsplugin;

import android.util.Log;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class EffectsCallHandler implements MethodCallHandler {
    private static final String TAG = "EffectsCallHandler";

    @SuppressWarnings("WeakerAccess")
    public EffectsCallHandler() {
        Log.i(TAG, "Invoking init");
        EffectsPluginService.diracInit();
        EffectsPluginService.eqInit();
    }

    private void handleDiracCall(MethodCall call, @NonNull Result result) {
        switch (call.method) {
            case "init":
                EffectsPluginService.diracInit();
                if (!EffectsPluginService.mDiracSupported || EffectsPluginService.mDirac == null)
                    result.error("exception", "Dirac unsupported", false);
                else
                    result.success(true);
                break;
            case "setMusic": {
                final int arg = call.argument("enable") ? 1 : 0;
                if (EffectsPluginService.mDirac != null)
                    EffectsPluginService.mDirac.setMusic(arg);
                result.success(true);
                break;
            }
            case "setLevel": {
                final int band = call.argument("band");
                final float level = Float.parseFloat(call.argument("level").toString());
                if (EffectsPluginService.mDirac != null)
                    EffectsPluginService.mDirac.setLevel(band, level);
                break;
            }
            case "getMusic":

                if (EffectsPluginService.mDirac != null)
                    result.success(EffectsPluginService.mDirac.getMusic());
                break;
            case "getLevel": {
                final int band = call.argument("band");
                if (EffectsPluginService.mDirac != null)
                    result.success(EffectsPluginService.mDirac.getLevel(band));

                break;
            }
            case "getNumBands":
                result.success(7);
                break;
            case "release":
                if (EffectsPluginService.mDirac != null)
                    EffectsPluginService.mDirac.release();
                result.success(true);
                break;

            /* Start Dirac Additions */
            case "setHifiMode": {
                final int arg = call.argument("mode");
                if (EffectsPluginService.mDirac != null)
                    EffectsPluginService.mDirac.setHifiMode(arg);
                result.success(true);
                break;
            }
            case "setHeadsetType": {
                final int arg = call.argument("hType");
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
            default:
                result.error("exception", "Call not implemented! " + call.method, false);
                break;
        }
    }

    private void handleMiCall(MethodCall call, @NonNull Result result) {
        switch (call.method) {
            case "init":
                EffectsPluginService.miInit();
                if (!EffectsPluginService.mMiSupported || EffectsPluginService.mMi == null)
                    result.error("exception", "Mi unsupported", false);
                else
                    result.success(true);
                break;
            case "setMusic": {
                final int arg = call.argument("enable") ? 1 : 0;
                if (EffectsPluginService.mMi != null)
                    EffectsPluginService.mMi.setMusic(arg);
                result.success(true);
                break;
            }
            case "setLevel": {
                final int band = call.argument("band");
                final float level = Float.parseFloat(call.argument("level").toString());
                if (EffectsPluginService.mMi != null)
                    EffectsPluginService.mMi.setLevel(band, level);
                break;
            }
            case "getMusic":

                if (EffectsPluginService.mMi != null)
                    result.success(EffectsPluginService.mMi.getMusic());
                break;
            case "getLevel": {
                final int band = call.argument("band");
                if (EffectsPluginService.mMi != null)
                    result.success(EffectsPluginService.mMi.getLevel(band));

                break;
            }
            case "getNumBands":
                result.success(7);
                break;
            case "release":
                if (EffectsPluginService.mMi != null)
                    EffectsPluginService.mMi.release();
                result.success(true);
                break;

            /* Start Mi Additions */
            case "setHifiMode": {
                final int arg = call.argument("mode");
                if (EffectsPluginService.mMi != null)
                    EffectsPluginService.mMi.setHifiMode(arg);
                result.success(true);
                break;
            }
            case "setHeadsetType": {
                final int arg = call.argument("hType");
                if (EffectsPluginService.mMi != null)
                    EffectsPluginService.mMi.setHeadsetType(arg);
                result.success(true);
                break;
            }
            case "setMovie": {
                final int arg = call.argument("enable") ? 1 : 0;
                if (EffectsPluginService.mMi != null) EffectsPluginService.mMi.setMovie(arg);
                result.success(true);
                break;
            }
            case "setMovieMode": {
                final int arg = call.argument("mode");
                if (EffectsPluginService.mMi != null)
                    EffectsPluginService.mMi.setMovieMode(arg);
                result.success(true);
                break;
            }
            case "setSpeakerStereoMode": {
                final int arg = call.argument("mode");
                if (EffectsPluginService.mMi != null)
                    EffectsPluginService.mMi.setSpeakerStereoMode(arg);
                result.success(true);
                break;
            }
            case "getHeadsetType":
                if (EffectsPluginService.mMi != null)
                    result.success(EffectsPluginService.mMi.getHeadsetType());
                break;
            case "getHifiMode":
                if (EffectsPluginService.mMi != null)
                    result.success(EffectsPluginService.mMi.getHifiMode());
                break;
            case "getMovie":
                if (EffectsPluginService.mMi != null)
                    result.success(EffectsPluginService.mMi.getMovie());
                break;
            case "getMovieMode":
                if (EffectsPluginService.mMi != null)
                    result.success(EffectsPluginService.mMi.getMovieMode());
                break;
            case "getSpeakerStereoMode":
                if (EffectsPluginService.mMi != null)
                    result.success(EffectsPluginService.mMi.getSpeakerStereoMode());
                break;
            case "checkField": {
                final String arg = call.argument("field");
                if (EffectsPluginService.mMi != null)
                    result.success(EffectsPluginService.mMi.checkField(arg));
                break;
            }
            default:
                result.error("exception", "Call not implemented! " + call.method, false);
                break;
        }
    }

    private void handleEqualizerCall(MethodCall call, @NonNull Result result) {
        switch (call.method) {
            case "init":
                EffectsPluginService.eqInit();
                if (EffectsPluginService.mEqualizer == null || !EqualizerWrapper.ismIsEQInitialized())
                    result.error("exception", "Equalizer init failure", false);
                else
                    result.success(true);
                break;
            case "setMusic": {
                final int arg = call.argument("enable") ? 1 : 0;

                if (EffectsPluginService.mEqualizer != null)
                    EffectsPluginService.mEqualizer.setEnabled(arg == 1);
                result.success(true);
                break;
            }
            case "setLevel": {
                final int band = call.argument("band");
                final float level = Float.parseFloat(call.argument("level").toString());
                if (EffectsPluginService.mEqualizer != null)
                    EffectsPluginService.mEqualizer.setBandLevel((short) band, (short) (level * 100));
                break;
            }
            case "getMusic":
                if (EffectsPluginService.mEqualizer != null)
                    result.success(EffectsPluginService.mEqualizer.getEnabled() ? 1 : 0);
                break;
            case "getLevel": {
                final int band = call.argument("band");
                if (EffectsPluginService.mEqualizer != null)
                    result.success(((double) EffectsPluginService.mEqualizer.getBandLevel((short) band) / 100));
                break;
            }
            case "getNumBands":
                result.success(EffectsPluginService.mEqualizer.getNumberOfBands());
                break;
            case "release":
                if (EffectsPluginService.mEqualizer != null)
                    EffectsPluginService.mEqualizer.release();
                result.success(true);
                break;
            default:
                result.error("exception", "Call not implemented! " + call.method, false);
                break;
        }
        /* End Dirac Additions */
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onMethodCall(MethodCall call, @NonNull Result result) {
        switch (call.method) {
            case "isDiracSupported":
                EffectsPluginService.diracInit();
                result.success(EffectsPluginService.mDiracSupported);
                break;
            case "isMiSupported":
                EffectsPluginService.miInit();
                result.success(EffectsPluginService.mMiSupported);
                break;
            default:
                if (call.argument("type") == null)
                    result.error("exception", "No type provided!", false);
                else {
                    Integer type = call.argument("type");
                    switch (type) {
                        case 0:
                            handleEqualizerCall(call, result);
                            break;
                        case 1:
                            handleDiracCall(call, result);
                            break;
                        case 2:
                            handleMiCall(call, result);
                            break;
                        default:
                            result.error("exception", "Invalid type!", false);
                            break;
                    }
                }
                break;
        }
    }


}
