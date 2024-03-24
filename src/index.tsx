import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-tone2' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const Tone2 = NativeModules.Tone2
  ? NativeModules.Tone2
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function playTone(frequency: number, duration: number): Promise<boolean> {
  return Tone2.playTone(frequency, duration);
}
