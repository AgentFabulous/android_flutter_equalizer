package co.potatoproject.effectsplugin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class EffectsPluginService extends Service {

    public static DiracSoundWrapper mDirac;
    public static EqualizerWrapper mEqualizer;
    public static boolean mDiracSupported = false;
    private static final String TAG = "EffectsPluginService";

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        diracInit();
        eqInit();
        return START_STICKY;
    }

    public static void diracInit() {
        try {
            mDirac = new DiracSoundWrapper(0, 0);
            mDiracSupported = true;
        } catch (RuntimeException e) {
            Log.e(TAG, "DiracSoundWrapper Creation failure! Dirac may be unsupported on this device\n" + e);
            mDiracSupported = false;
        }
    }

    public static void eqInit() {
        mEqualizer = new EqualizerWrapper();
        if (mEqualizer.mEqualizerEffect == null)
            Log.e(TAG, "Failed to create EqualizerEffect!");
    }
}
