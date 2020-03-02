package com.lparolari.covidtester.ui.tester;

import android.util.Log;

import com.lparolari.covidtester.R;

public class TesterFormState {
    /** The phase indicator */
    private Integer phase;
    /** The label text for user interface */
    private Integer label;

    public static final Integer START = 0;
    public static final Integer SCAN_FINGER = 1;
    public static final Integer REPEAT_SCAN = 2;
    public static final Integer CAN_RELEASE = 3;
    public static final Integer ELABORATING_DATA = 4;
    public static final Integer CORONAVIRUS_POSITIVE = 5;
    public static final Integer CORONAVIRUS_NEGATIVE = 6;

    public static TesterFormState start()  { return new TesterFormState(START); }
    public static TesterFormState scanFinger()  { return new TesterFormState(SCAN_FINGER); }
    public static TesterFormState repeatScan()  { return new TesterFormState(REPEAT_SCAN); }
    public static TesterFormState canRelease()  { return new TesterFormState(CAN_RELEASE); }
    public static TesterFormState elaboratingData()  { return new TesterFormState(ELABORATING_DATA); }
    public static TesterFormState positive()  { return new TesterFormState(CORONAVIRUS_POSITIVE); }
    public static TesterFormState negative()  { return new TesterFormState(CORONAVIRUS_NEGATIVE); }

    /**
     * @return An integer representing the resource if of the string associated with
     * this label.
     */
    public Integer getLabel() {
        return label;
    }

    /**
     * @return An integer representing the phase of the process.
     */
    public Integer getPhase() {
        return phase;
    }

    private TesterFormState(Integer phase) {
        this.phase = phase;
        setLabelByPhase();
    }

    private void setLabelByPhase() {
             if (phase.equals(START)) label = R.string.touch_sensor;
        else if (phase.equals(SCAN_FINGER)) label = R.string.scanning_finger;
        else if (phase.equals(REPEAT_SCAN)) label = R.string.repeat_scanning;
        else if (phase.equals(CAN_RELEASE)) label = R.string.can_release;
        else if (phase.equals(ELABORATING_DATA)) label = R.string.elaborating_data;
        else if (phase.equals(CORONAVIRUS_POSITIVE)) label = R.string.coronavirus_positive;
        else if (phase.equals(CORONAVIRUS_NEGATIVE)) label = R.string.coronavirus_negative;
        else label = R.string.error_performing_test;
    }

}
