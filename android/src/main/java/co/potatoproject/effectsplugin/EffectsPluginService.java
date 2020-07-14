package co.potatoproject.effectsplugin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.media.audiofx.Equalizer;

public class EffectsPluginService extends Service {

    public static DiracSoundWrapper mDirac;
    public static MiSoundWrapper mMi;
    public static Equalizer mEqualizer;
    public static boolean mDiracSupported = false;
    public static boolean mMiSupported = false;
    private static final String TAG = "EffectsPluginService";

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        diracInit();
        miInit();
        eqInit();
        return START_STICKY;
    }

    public static void diracInit() {
        try {
            mDirac = new DiracSoundWrapper(0, 0);
            mDiracSupported = true;
        } catch (RuntimeException e) {
            Log.e(TAG, "DiracSoundWrapper Creation failure! DiracSound may be unsupported on this device\n" + e);
            mDiracSupported = false;
        }
    }

    public static void miInit() {
        try {
            mMi = new MiSoundWrapper(0, 0);
            mMiSupported = true;
            Log.i(TAG, "MiSoundWrapper Creation success!\n");
        } catch (RuntimeException e) {
            Log.e(TAG, "MiSoundWrapper Creation failure! MiSound may be unsupported on this device\n" + e);
            mMiSupported  = false;
        }
    }

    public static void eqInit() {
        EqualizerWrapper.initEq();
        mEqualizer = EqualizerWrapper.getmEqualizerEffect();
        if (mEqualizer == null || !EqualizerWrapper.ismIsEQInitialized())
            Log.e(TAG, "Failed to create EqualizerEffect!");
    }
}
