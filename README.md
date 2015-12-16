# SpeechOrbView
A custom view that animates like voice search button in Android TV

### Screenshots

<img src="http://imgreview.com/i/gVMtl" height="384" width="216"</img> <img src="http://imgreview.com/i/gVMtm" height="384" width="216"</img> <img src="http://imgreview.com/i/gVMtn" height="384" width="216"</img>

## How to add
SpeechOrbView is published with [JitPack.io](https://jitpack.io).
To add this library to your project, add these lines to your build.gradle

```
repositories {
  maven { url "https://jitpack.io" }
}

dependencies {
  // ... other dependencies
  compile 'com.github.sembozdemir:SpeechOrbView:1.0.0'
}
```

## How to use
#### Simply, add SpeechOrbView in your xml layout

```
<com.sembozdemir.speechorbview.library.SpeechOrbView
        android:id="@+id/speech_orb_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```

#### In your activity or fragment, if you want to toggle animation of SpeechOrbView when clicking, do like that below

```
final SpeechOrbView speechOrbView = (SpeechOrbView) findViewById(R.id.speech_orb_view);
speechOrbView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (!speechOrbView.isPlaying()) {
            speechOrbView.play();
        } else {
            speechOrbView.stop();
        }
    }
});
```

#### Repeat Mode (Optional)
SpeechOrbView is animating repeatly in default.
But, you can set repeat mode false by adding this attribute if you want (Default value is true)
```
app:repeatMode="false"
```
Don't forget to add namespace
```
xmlns:app="http://schemas.android.com/apk/res-auto"
```
Also, you can set it programmatically
```
speechOrbView.setRepeatMode(false);
```

#### Animation Listener (Optional)
If you want to listen animation is started or ended, you can set a listener

```
speechOrbView.setListener(new SpeechOrbView.Listener() {
    @Override
    public void onStartPlaying() {
        // do something
        Toast.makeText(MainActivity.this, "SpeechOrbView is playing", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onEndPlaying() {
        // do something
        Toast.makeText(MainActivity.this, "SpeechOrbView has stopped", Toast.LENGTH_LONG)
                .show();
    }
});
```


## Licence
The MIT License (MIT)

Copyright (c) 2015 Semih Bozdemir

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
