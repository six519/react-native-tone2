//
//  TGSineWaveToneGenerator.h
//  Tone Generator
//
//  Created by Anthony Picciano on 6/12/13.
//  Copyright (c) 2013 Anthony Picciano. All rights reserved.
//
//  Major contributions and updates by Simon Grätzer on 12/23/14.
//
//  Based upon work by Matt Gallagher on 2010/10/20.
//  Copyright 2010 Matt Gallagher. All rights reserved.
//
//  Permission is given to use this source code file, free of charge, in any
//  project, commercial or otherwise, entirely at your risk, with the condition
//  that any redistribution (in part or whole) of source code must retain
//  this copyright and permission notice. Attribution in compiled projects is
//  appreciated but not required.
//
//  Modified by Ferdinand Silva on 3/24/23

#import <Foundation/Foundation.h>
#import <AudioToolbox/AudioToolbox.h>
#import <AVFoundation/AVFoundation.h>

#define SINE_WAVE_TONE_GENERATOR_FREQUENCY_DEFAULT 440.0f

#define SINE_WAVE_TONE_GENERATOR_SAMPLE_RATE_DEFAULT 44100.0f

#define SINE_WAVE_TONE_GENERATOR_AMPLITUDE_LOW 0.01f
#define SINE_WAVE_TONE_GENERATOR_AMPLITUDE_MEDIUM 0.02f
#define SINE_WAVE_TONE_GENERATOR_AMPLITUDE_HIGH 0.03f
#define SINE_WAVE_TONE_GENERATOR_AMPLITUDE_FULL 0.25f
#define SINE_WAVE_TONE_GENERATOR_AMPLITUDE_DEFAULT SINE_WAVE_TONE_GENERATOR_AMPLITUDE_MEDIUM

typedef struct {
    double frequency;
    double amplitude;
    double theta;
} TGChannelInfo;

@interface TGSineWaveToneGenerator : NSObject {
@public
    AudioComponentInstance _toneUnit;
    double _sampleRate;
    TGChannelInfo *_channels;
    UInt32 _numChannels;
}

- (id)initWithChannels:(UInt32)size;
- (id)initWithFrequency:(double)hertz amplitude:(double)volume;
- (void)playForDuration:(int)time;
- (void)play;
- (void)stop;

@end

#ifdef RCT_NEW_ARCH_ENABLED
#import "RNTone2Spec.h"

@interface Tone2 : NSObject <NativeTone2Spec>
#else
#import <React/RCTBridgeModule.h>

@interface Tone2 : NSObject <RCTBridgeModule>
#endif

@end
