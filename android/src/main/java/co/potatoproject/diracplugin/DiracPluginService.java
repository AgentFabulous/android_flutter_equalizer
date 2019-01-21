package co.potatoproject.diracplugin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class DiracPluginService extends Service {

    public static DiracSoundWrapper mDirac;
    public static boolean mDiracSupported = false;
    private static final String TAG = "DiracPluginService";

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        diracInit();
        return START_STICKY;
    }

    public static void diracInit() {
        try {
            mDirac = new DiracSoundWrapper(0, 0);
            mDiracSupported = true;
        } catch (RuntimeException e) {
            Log.e(TAG, "Wrapper Creation failure! Dirac may be unsupported on this device\n" + e);
            mDiracSupported = false;
        }
    }
}
