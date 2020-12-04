package com.jesster2k10reactnativerangeslider;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.AttributeSet;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;

import java.lang.StringBuilder;

import android.view.View;

public class RangeSlider extends CrystalRangeSeekbar {
    private final int THUMB_SIZE = 50;

    public RangeSlider(Context context) {
        super(context);
    }

    public RangeSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RangeSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected float getBarHeight() {
        return 3;
    }

    @Override
    protected float getThumbWidth() {
        return THUMB_SIZE;
    }

    @Override
    protected float getThumbHeight() {
        return THUMB_SIZE;
    }
}