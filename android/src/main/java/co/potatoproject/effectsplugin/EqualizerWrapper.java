package co.potatoproject.effectsplugin;

import android.util.Log;
import android.media.audiofx.Equalizer;

class EqualizerWrapper {
    private static final int PRIORITY = 0;

    private static final String TAG = "EqualizerWrapper";

    private final static String EQUALIZER_PRESET_NAME_DEFAULT = "Preset";
    private final static short EQUALIZER_NUMBER_BANDS_DEFAULT = 5;
    private final static short EQUALIZER_NUMBER_PRESETS_DEFAULT = 0;
    private final static short[] EQUALIZER_BAND_LEVEL_RANGE_DEFAULT = { -1500, 1500 };
    private final static int[] EQUALIZER_CENTER_FREQ_DEFAULT = { 60000, 230000, 910000, 3600000,
            14000000 };
    Equalizer mEqualizerEffect = null;
    private static short[] mEQBandLevelRange = EQUALIZER_BAND_LEVEL_RANGE_DEFAULT;
    private static short mEQNumBands = EQUALIZER_NUMBER_BANDS_DEFAULT;
    private static int[] mEQCenterFreq = EQUALIZER_CENTER_FREQ_DEFAULT;
    private static short mEQNumPresets = EQUALIZER_NUMBER_PRESETS_DEFAULT;
    private static short[][] mEQPresetOpenSLESBandLevel =
            new short[EQUALIZER_NUMBER_PRESETS_DEFAULT][EQUALIZER_NUMBER_BANDS_DEFAULT];
    private static String[] mEQPresetNames;
    private static boolean mIsEQInitialized = false;

    EqualizerWrapper() {
        initEq();
    }

    public static short[] getmEQBandLevelRange() {
        return mEQBandLevelRange;
    }

    public static short getmEQNumBands() {
        return mEQNumBands;
    }

    public static int[] getmEQCenterFreq() {
        return mEQCenterFreq;
    }

    public static short getmEQNumPresets() {
        return mEQNumPresets;
    }

    public static short[][] getmEQPresetOpenSLESBandLevel() {
        return mEQPresetOpenSLESBandLevel;
    }

    public static String[] getmEQPresetNames() {
        return mEQPresetNames;
    }

    public static boolean ismIsEQInitialized() {
        return mIsEQInitialized;
    }

    void initEq() {
        final int session = 0;
        if (!mIsEQInitialized) {
            try {
                Log.d(TAG, "Creating dummy EQ effect on session " + session);
                mEqualizerEffect = new Equalizer(PRIORITY, session);

                mEQBandLevelRange = mEqualizerEffect.getBandLevelRange();
                mEQNumBands = mEqualizerEffect.getNumberOfBands();
                mEQCenterFreq = new int[mEQNumBands];
                for (short band = 0; band < mEQNumBands; band++) {
                    mEQCenterFreq[band] = mEqualizerEffect.getCenterFreq(band);
                }
                mEQNumPresets = mEqualizerEffect.getNumberOfPresets();
                mEQPresetNames = new String[mEQNumPresets];
                mEQPresetOpenSLESBandLevel = new short[mEQNumPresets][mEQNumBands];
                for (short preset = 0; preset < mEQNumPresets; preset++) {
                    mEQPresetNames[preset] = mEqualizerEffect.getPresetName(preset);
                    mEqualizerEffect.usePreset(preset);
                    for (short band = 0; band < mEQNumBands; band++) {
                        mEQPresetOpenSLESBandLevel[preset][band] = mEqualizerEffect
                                .getBandLevel(band);
                    }
                }

                mIsEQInitialized = true;
            } catch (final IllegalStateException e) {
                Log.e(TAG, e.toString());
            } catch (final IllegalArgumentException e) {
                Log.e(TAG, e.toString());
            } catch (final UnsupportedOperationException e) {
                Log.e(TAG, e.toString());
            } catch (final RuntimeException e) {
                Log.e(TAG, e.toString());
            } finally {
                if (mEqualizerEffect != null) {
                    Log.d(TAG, "EqualizerEffect init complete.");
                }
            }
        }

    }
}
