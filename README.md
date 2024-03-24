# react-native-tone2

Generate tone by frequency React Native module For iOS/Android. Updated for compatibility with React Native 0.73.6.

## Installation

```sh
npm install react-native-tone2
```

## Usage

```js
import { playTone } from 'react-native-tone2';

// ...

const result = await playTone(261.63, 500); //play C4 for 500 milliseconds
```