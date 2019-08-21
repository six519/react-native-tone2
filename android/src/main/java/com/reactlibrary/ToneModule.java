package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class ToneModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public ToneModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Tone";
    }

    @ReactMethod
    public void play(double freq, int dur) {
        //from stackoverflow post: https://stackoverflow.com/questions/2413426/playing-an-arbitrary-tone-with-android
        int duration = 10; // seconds
        final int sampleRate = 8000;
        final int numSamples = duration * sampleRate;
        final double sample[] = new double[numSamples];

        final byte generatedSnd[] = new byte[2 * numSamples];

        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freq));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (final double dVal : sample) {
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

        }

        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.play();

        try {
            //NOTE: It will cause deadlock, need to update soon!!!
            Thread.sleep(dur);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        audioTrack.release();
    }
}
