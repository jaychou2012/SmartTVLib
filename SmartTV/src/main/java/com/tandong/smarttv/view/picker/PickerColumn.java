package com.tandong.smarttv.view.picker;

/**
 * Created by office on 2018/3/29.
 */

public class PickerColumn {

    private int mCurrentValue;
    private int mMinValue;
    private int mMaxValue;
    private CharSequence[] mStaticLabels;
    private String mLabelFormat;

    public PickerColumn() {
    }

    /**
     * Set string format (see {@link String#format}) to display label for an
     * integer value.  {@link #setStaticLabels(CharSequence[])} overrides the format.
     *
     * @param labelFormat String format to display label for value between minValue and maxValue.
     */
    public void setLabelFormat(String labelFormat) {
        mLabelFormat = labelFormat;
    }

    /**
     * Return string format (see {@link String#format}) to display label for
     * value.
     * @return String format to display label for value.
     */
    public String getLabelFormat() {
        return mLabelFormat;
    }

    /**
     * Set static labels for each value, minValue maps to labels[0], maxValue maps to
     * labels[labels.length - 1].
     * @param labels Static labels for each value between minValue and maxValue.
     */
    public void setStaticLabels(CharSequence[] labels) {
        mStaticLabels = labels;
    }

    /**
     * Returns static labels for each value, minValue maps to labels[0], maxValue maps to
     * labels[labels.length - 1].  When null, {@link #getLabelFormat()} will be used.
     */
    public CharSequence[] getStaticLabels() {
        return mStaticLabels;
    }

    /**
     * Get a label for value. The label can be static {@link #setStaticLabels(CharSequence[])}
     * or dynamically generated {@link #setLabelFormat(String)} when static labels is null.
     *
     * @param value Value between minValue and maxValue.
     * @return Label for the value.
     */
    public CharSequence getLabelFor(int value) {
        if (mStaticLabels == null) {
            return String.format(mLabelFormat, value);
        }
        return mStaticLabels[value];
    }

    /**
     * Returns current value of the Column.
     * @return Current value of the Column.
     */
    public int getCurrentValue() {
        return mCurrentValue;
    }

    /**
     * Sets current value of the Column.
     */
    public void setCurrentValue(int value) {
        mCurrentValue = value;
    }

    /**
     * Get total items count between minValue and maxValue.
     * @return Total items count between minValue and maxValue.
     */
    public int getCount() {
        return mMaxValue - mMinValue + 1;
    }

    /**
     * Returns minimal value of the Column.
     * @return Minimal value of the Column.
     */
    public int getMinValue() {
        return mMinValue;
    }

    /**
     * Returns maximum value of the Column.
     * @return Maximum value of the Column.
     */
    public int getMaxValue() {
        return mMaxValue;
    }

    /**
     * Sets minimal value of the Column.
     * @param minValue New minimal value to set.
     */
    public void setMinValue(int minValue) {
        mMinValue = minValue;
    }

    /**
     * Sets maximum value of the Column.
     * @param maxValue New maximum value to set.
     */
    public void setMaxValue(int maxValue) {
        mMaxValue = maxValue;
    }

}
