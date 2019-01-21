package co.potatoproject.diracplugin;

import android.annotation.SuppressLint;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class DiracSoundWrapper {
    private final Object mDiracSoundConstructor;
    private Method mRelease;
    private Method mSetMusic;
    private Method mGetMusic;
    private Method mSetHeadsetType;
    private Method mGetHeadsetType;
    private Method mSetLevel;
    private Method mSetHifiMode;
    private Method mGetHifiMode;
    private Method mGetMovie;
    private Method mGetMovieMode;
    private Method mGetSpeakerStereoMode;
    private Method mSetMovie;
    private Method mSetMovieMode;
    private Method mSetSpeakerStereoMode;
    private Method mGetLevel;

    DiracSoundWrapper(int priority, int audioSession) throws RuntimeException {
        try {
            @SuppressLint("PrivateApi")
            Class<?> reflect = Class.forName("android.media.audiofx.DiracSound");
            this.mDiracSoundConstructor = reflect.getConstructor(Integer.TYPE, Integer.TYPE).newInstance(priority, audioSession);
            try {
                this.mRelease = reflect.getMethod("release");
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mSetMusic = reflect.getMethod("setMusic", Integer.TYPE);
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mGetMusic = reflect.getMethod("getMusic");
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mSetHeadsetType = reflect.getMethod("setHeadsetType", Integer.TYPE);
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mGetHeadsetType = reflect.getMethod("getHeadsetType");
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mSetLevel = reflect.getMethod("setLevel", Integer.TYPE, Float.TYPE);
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mSetHifiMode = reflect.getMethod("setHifiMode", Integer.TYPE);
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mGetHifiMode = reflect.getMethod("getHifiMode");
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mGetLevel = reflect.getMethod("getLevel", Integer.TYPE);
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mGetMovie = reflect.getMethod("getMovie");
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mGetMovieMode = reflect.getMethod("getMovieMode");
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mGetSpeakerStereoMode= reflect.getMethod("getSpeakerStereoMode");
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mSetMovie = reflect.getMethod("setMovie", Integer.TYPE);
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mSetMovieMode = reflect.getMethod("setMovieMode", Integer.TYPE);
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
            try {
                this.mSetSpeakerStereoMode= reflect.getMethod("setSpeakerStereoMode", Integer.TYPE);
            } catch (NoSuchMethodException e) {
                Log.e("DiracSoundWrapper", "", e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Not found android.media.audiofx.DiracSound", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    boolean checkField(String str) {
        if (this.mDiracSoundConstructor == null) {
            return false;
        }
        try {
            this.mDiracSoundConstructor.getClass().getField(str);
            return true;
        } catch (NoSuchFieldException e) {
            Log.e("DiracSoundWrapper", "", e);
            return false;
        }
    }

    int getHeadsetType() {
        if (this.mGetHeadsetType == null) {
            return 0;
        }
        try {
            return (Integer) this.mGetHeadsetType.invoke(this.mDiracSoundConstructor, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        }
    }

    int getMovie() {
        if (this.mGetMovie == null) {
            return 0;
        }
        try {
            return (Integer) this.mGetMovie.invoke(this.mDiracSoundConstructor, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        }
    }

    int getMovieMode() {
        if (this.mGetMovieMode == null) {
            return 0;
        }
        try {
            return (Integer) this.mGetMovieMode.invoke(this.mDiracSoundConstructor, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        }
    }

    int getSpeakerStereoMode() {
        if (this.mGetSpeakerStereoMode == null) {
            return 0;
        }
        try {
            return (Integer) this.mGetSpeakerStereoMode.invoke(this.mDiracSoundConstructor, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        }
    }

    int getMusic() {
        if (this.mGetMusic == null) {
            return 0;
        }
        try {
            return (Integer) this.mGetMusic.invoke(this.mDiracSoundConstructor, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        }
    }

    int getHifiMode() {
        Log.i("SHIT", "getHifi invoked");
        if (this.mGetHifiMode == null) {
            return 0;
        }
        try {
            return (Integer) this.mGetHifiMode.invoke(this.mDiracSoundConstructor, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        }
    }

    float getLevel(int i) {
        if (this.mGetLevel == null) {
            return 0f;
        }
        try {
            return (Float) this.mGetLevel.invoke(this.mDiracSoundConstructor, i);
        } catch (IllegalAccessException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            Log.e("DiracSoundWrapper", "", e);
            throw new RuntimeException(e);
        }
    }

    void release() {
        if (this.mRelease != null) {
            try {
                this.mRelease.invoke(this.mDiracSoundConstructor);
            } catch (IllegalAccessException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (IllegalArgumentException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            }
        }
    }

    void setHeadsetType(int i) {
        if (this.mSetHeadsetType != null) {
            try {
                this.mSetHeadsetType.invoke(this.mDiracSoundConstructor, i);
            } catch (IllegalAccessException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (IllegalArgumentException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            }
        }
    }

    void setHifiMode(int i) {
        if (this.mSetHifiMode != null) {
            try {
                this.mSetHifiMode.invoke(this.mDiracSoundConstructor, i);
            } catch (IllegalAccessException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (IllegalArgumentException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            }
        }
    }

    void setLevel(int i, float f) {
        if (this.mSetLevel != null) {
            try {
                this.mSetLevel.invoke(this.mDiracSoundConstructor, i, f);
            } catch (IllegalAccessException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (IllegalArgumentException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            }
        }
    }

    void setMusic(int i) {
        if (this.mSetMusic != null) {
            try {
                this.mSetMusic.invoke(this.mDiracSoundConstructor, i);
            } catch (IllegalAccessException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (IllegalArgumentException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            }
        }
    }

    void setMovie(int i) {
        if (this.mSetMovie != null) {
            try {
                this.mSetMovie.invoke(this.mDiracSoundConstructor, i);
            } catch (IllegalAccessException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (IllegalArgumentException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            }
        }
    }

    void setMovieMode(int i) {
        if (this.mSetMovieMode != null) {
            try {
                this.mSetMovieMode.invoke(this.mDiracSoundConstructor, i);
            } catch (IllegalAccessException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (IllegalArgumentException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            }
        }
    }

    void setSpeakerStereoMode(int i) {
        if (this.mSetSpeakerStereoMode != null) {
            try {
                this.mSetSpeakerStereoMode.invoke(this.mDiracSoundConstructor, i);
            } catch (IllegalAccessException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (IllegalArgumentException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                Log.e("DiracSoundWrapper", "", e);
                throw new RuntimeException(e);
            }
        }
    }

}
