#import "Tone.h"
#import "TGSineWaveToneGenerator.h"

@implementation Tone

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(play:(double)freq dur:(int)dur)
{
    // Original code at: https://github.com/picciano/iOS-Tone-Generator/
    generateTone(freq, dur);
}

@end
