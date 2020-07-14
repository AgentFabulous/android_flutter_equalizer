package co.potatoproject.effectsplugin;


import android.util.Log;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * AndroidFlutterSettingsPlugin
 */
public class EffectsPlugin implements FlutterPlugin, ActivityAware {
    private MethodChannel mMethodProvider;
    private static final String TAG = "EffectsPlugin";

    public static void registerWith(Registrar registrar) {
        final MethodChannel methodProvider = new MethodChannel(registrar.messenger(), "audio_effects_flutter/methods");
        methodProvider.setMethodCallHandler(new EffectsCallHandler());
        Log.i(TAG, "Registration complete.");
    }

    @Override
    public void onAttachedToEngine(FlutterPluginBinding binding) {
        mMethodProvider = new MethodChannel(binding.getBinaryMessenger(), "audio_effects_flutter/methods");
        Log.i(TAG, "Engine attached.");
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {}

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        mMethodProvider.setMethodCallHandler(new EffectsCallHandler());
        Log.i(TAG, "Activity attached.");
    }

    @Override
    public void onDetachedFromActivity() {}

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {}

    @Override
    public void onDetachedFromActivityForConfigChanges() {}
}