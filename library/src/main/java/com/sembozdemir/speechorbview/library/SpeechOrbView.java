package com.sembozdemir.speechorbview.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by semih on 16.12.2015.
 * This is a custom view that animates like voice search button in Android TV
 */
public class SpeechOrbView extends FrameLayout implements Animation.AnimationListener {
    private ImageView imageViewMic;
    private Animation anim;
    private View background;
    private boolean playing;
    private Listener listener;
    private boolean repeatMode;
    private boolean forceStop;
    private boolean onStartPlayingCalled;

    public SpeechOrbView(Context context) {
        super(context);
        init(context);
    }

    public SpeechOrbView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttrs(context, attrs, 0);
    }

    public SpeechOrbView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initAttrs(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = getMeasuredWidth();
        int backgroundSize = (int) (size * 0.68);

        ViewGroup.LayoutParams params = background.getLayoutParams();
        params.width = backgroundSize;
        params.height = backgroundSize;
        background.setLayoutParams(params);

        setMeasuredDimension(size, size);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init(Context context) {
        // inflate layout
        FrameLayout root = (FrameLayout) inflate(context, R.layout.speech_orb_view, this);
        // init background
        background = root.findViewById(R.id.view_background);
        // init image view
        imageViewMic = (ImageView) findViewById(R.id.image_view_mic);
        imageViewMic.setImageResource(R.drawable.ic_mic_outline);
        // init animation
        anim = AnimationUtils.loadAnimation(context, R.anim.speech_orb_view_anim);
        anim.setAnimationListener(this);
        // init flags
        playing = false;
        forceStop = false;
        onStartPlayingCalled = false;
        repeatMode = true; // default value
    }

    private void initAttrs(Context context, AttributeSet attrs, int defSytleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SpeechOrbView,
                defSytleAttr, 0);

        try {
            // set repeat mode (default value is true)
            repeatMode = a.getBoolean(R.styleable.SpeechOrbView_repeatMode, true);
        } finally {
            a.recycle();
        }
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        background.setOnClickListener(l);
    }

    public void play() {
        background.startAnimation(anim);
    }

    public void stop() {
        forceStop = true;
        background.clearAnimation();
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setRepeatMode(boolean repeatMode) {
        this.repeatMode = repeatMode;
    }

    public boolean isRepeatMode() {
        return repeatMode;
    }

    public boolean isPlaying() {
        return playing;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        handleAnimStart();
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        handleAnimEnd();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // do nothing
    }

    private void handleAnimStart() {
        playing = true;
        background.setBackgroundResource(R.drawable.speech_orb_view_background_active);
        imageViewMic.setImageResource(R.drawable.ic_mic);
        if (listener != null && !onStartPlayingCalled) {
            listener.onStartPlaying();
            onStartPlayingCalled = true;
        }
    }

    private void handleAnimEnd() {
        if (!forceStop && isRepeatMode()) {
            play();
            return;
        }
        playing = false;
        forceStop = false;
        background.setBackgroundResource(R.drawable.speech_orb_view_background);
        imageViewMic.setImageResource(R.drawable.ic_mic_outline);
        if (listener != null) {
            listener.onEndPlaying();
            // clear onStartPlayingCalled flag
            onStartPlayingCalled = false;
        }
    }

    public interface Listener {
        void onStartPlaying();
        void onEndPlaying();
    }
}
