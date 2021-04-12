package com.jesster2k10reactnativerangeslider

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar
import com.facebook.react.bridge.ReactContext
import com.facebook.react.uimanager.UIManagerModule

class RNRangeSliderView(context: Context) : LinearLayout(context), OnRangeSeekbarChangeListener, OnSeekbarChangeListener {
    private var rangeSeekBar: CrystalRangeSeekbar
    private var seekBar: CrystalSeekbar
    private var minTextView: TextView? = null
    private var maxTextView: TextView? = null

    private var minValue: Float = 0f
    private var maxValue: Float = 100f

    var suffix: String? = ""
        set(value) {
            field = value
            updateText()
        }

    var prefix: String? = ""
        set(value) {
            field = value
            updateText()
        }

    var hideLabels: String? = ""
//        set(value) {
//            field = value
//            minTextView?.visibility = if (value ?: false) View.GONE else View.VISIBLE
//            maxTextView?.visibility = if (value ?: false) View.GONE else View.VISIBLE
//        }

    var disableRange: Boolean? = false
        set(value) {
            field = value
            rangeSeekBar.visibility = View.GONE
            seekBar.visibility = View.GONE
            rangeSeekBar.setOnRangeSeekbarChangeListener(null)
            seekBar.setOnSeekbarChangeListener(null)
            if (value ?: false) {
                seekBar.visibility = View.VISIBLE;
                seekBar.setOnSeekbarChangeListener(this)
            } else {
                rangeSeekBar.visibility = View.VISIBLE;
                rangeSeekBar.setOnRangeSeekbarChangeListener(this)
            }
        }

    init {
        inflate(context, R.layout.range_slider, this)
        rangeSeekBar = findViewById(R.id.range_seek_bar)
        rangeSeekBar.setMinStartValue(minValue)
        rangeSeekBar.setMaxStartValue(maxValue)

        seekBar = findViewById(R.id.seek_bar)
        seekBar.setMinStartValue(minValue)

        //minTextView = findViewById(R.id.range_seek_bar_min)
        //maxTextView = findViewById(R.id.range_seek_bar_max)
        updateText(minValue, maxValue)
    }

    fun setTintColorBetweenHandles(color: String) {
        rangeSeekBar.setBarHighlightColor(Color.parseColor(color))
        seekBar.setBarHighlightColor(Color.parseColor(color))
    }

    fun setTintColor(color: String) {
        rangeSeekBar.setBarColor(Color.parseColor(color))
        seekBar.setBarColor(Color.parseColor(color))
    }

    fun setHandleColor(color: String) {
        rangeSeekBar.setLeftThumbColor(Color.parseColor(color))
        rangeSeekBar.setRightThumbColor(Color.parseColor(color))
        seekBar.setThumbColor(Color.parseColor(color))
    }

    fun setHandlePressedColor(color: String) {
        rangeSeekBar.setLeftThumbHighlightColor(Color.parseColor(color))
        rangeSeekBar.setRightThumbHighlightColor(Color.parseColor(color))
        seekBar.setThumbHighlightColor(Color.parseColor(color))
    }

    fun setLeftHandleColor(color: String) {
        rangeSeekBar.setLeftThumbColor(Color.parseColor(color))
        seekBar.setThumbColor(Color.parseColor(color))
    }

    fun setRightHandleColor(color: String) {
        rangeSeekBar.setRightThumbColor(Color.parseColor(color))
    }

    fun setLeftHandlePressedColor(color: String) {
        rangeSeekBar.setLeftThumbHighlightColor(Color.parseColor(color))
        seekBar.setThumbHighlightColor(Color.parseColor(color))
    }

    fun setRightHandlePressedColor(color: String) {
        rangeSeekBar.setRightThumbHighlightColor(Color.parseColor(color))
    }

    fun setMinValue(min: Float) {
        minValue = min
        rangeSeekBar.setMinValue(min)
        seekBar.setMinValue(min)
        updateText(minValue, maxValue)
    }

    fun setMaxValue(max: Float) {
        maxValue = max
        rangeSeekBar.setMaxValue(max)
        seekBar.setMaxValue(max)
        updateText(minValue, maxValue)
    }

    fun setFixGap(gap: Float) {
        rangeSeekBar.setFixGap(gap)
    }

    fun setStep(steps: Float) {
        rangeSeekBar.setSteps(steps)
        seekBar.setSteps(steps)
    }

    // started here
    fun setMinStartValue(minStartValue: Float) {
        rangeSeekBar.setMinStartValue(minStartValue).apply();
        seekBar.setMinStartValue(minStartValue).apply();
    }

    fun setMaxStartValue(maxStartValue: Float) {
        rangeSeekBar.setMaxStartValue(maxStartValue).apply();
    }

    fun setCornerRadius(diameter: Float) {
        rangeSeekBar.setCornerRadius(diameter / 2)
        seekBar.setCornerRadius(diameter / 2)
        seekBar.setThumbSize(diameter).apply()
        rangeSeekBar.setThumbSize(diameter).apply()
    }

    fun setGap(gap: Float) {
        rangeSeekBar.setGap(gap);
    }

    private fun updateText(min: Number? = null, max: Number? = null) {
        var minText = minTextView?.text
        var maxText = minTextView?.text
        if (min !== null) {
            minText = min.toString() + hideLabels
        }

        if (max !== null) {
            maxText = max.toString() + hideLabels
        }

        minTextView?.text = StringBuilder()
                .append(prefix)
                .append(minText)
                .append(suffix)

        maxTextView?.text = StringBuilder()
                .append(prefix)
                .append(maxText)
                .append(suffix)
    }

    override fun valueChanged(min: Number?, max: Number?) {
        if (max == null || min == null) return
        updateText(min, max)

        val event = RangeSliderChangeEvent(this.id)
        event.max = max.toDouble()
        event.min = min.toDouble()

        val reactContext = context as ReactContext
        reactContext.getNativeModule(UIManagerModule::class.java).eventDispatcher.dispatchEvent(
                event
        )
    }

    override fun valueChanged(value: Number?) {
        valueChanged(value, value);
    }
}
