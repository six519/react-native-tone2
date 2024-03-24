package com.tone2;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

@ReactModule(name = Tone2Module.NAME)
public class Tone2Module extends ReactContextBaseJavaModule {
  public static final String NAME = "Tone2";

  public Tone2Module(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void playTone(double frequency, int duration, Promise promise) {
    //from stackoverflow post: https://stackoverflow.com/questions/2413426/playing-an-arbitrary-tone-with-android
    final int sampleRate = 8000;
    final int numSamples = 10 * sampleRate; //10 secs * sample rate
    final double sample[] = new double[numSamples];

    final byte generatedSnd[] = new byte[2 * numSamples];

    for (int i = 0; i < numSamples; ++i) {
      sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/frequency));
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
      Thread.sleep(duration);
      promise.resolve(true);
    } catch (InterruptedException e) {
      e.printStackTrace();
      promise.resolve(false);
    }

    audioTrack.release();
  }

}
