import * as React from 'react';

import { StyleSheet, View, Button } from 'react-native';
import { playTone } from 'react-native-tone2';

export default function App() {
  return (
    <View style={styles.container}>
      <Button title='Play Tone' onPress={() => {
        playTone(261.63, 500);
      }} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
