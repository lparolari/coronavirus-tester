package com.lparolari.covidtester.ui.tester;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * TesterViewModel
 *
 * This class manage the connection between the view and the model.
 * Each methods set the proper form state that is re-rendered in the ui.
 *
 * This process can be represented by a graph, where the nodes (i.e., tasks) are
 *
 * (A) start the test
 * (B) scan the finger
 * (C) rescan the finger
 * (D) release finger
 * (E) scanning data
 * (F) show results
 *
 * and the edges are
 *
 * (A) -> (B)
 * (B) -> (C)
 * (B) -> (D)
 * (C) -> (B)
 * (D) -> (E)
 * (E) -> (F)
 */
public class TesterViewModel {
    private MutableLiveData<TesterFormState> testerFormState = new MutableLiveData<>();

    public LiveData<TesterFormState> getTesterFormState() {
        return  testerFormState;
    }

    public void startTest() {
        testerFormState.setValue(TesterFormState.start());
    }

    public void scanningFinger() {
        testerFormState.setValue(TesterFormState.scanFinger());
    }

    public void fingerScanned() {
        testerFormState.setValue(TesterFormState.elaboratingData());
    }

    public void canRelease() {
        testerFormState.setValue(TesterFormState.canRelease());
    }

    public void fingerNotScanned() {
        testerFormState.setValue(TesterFormState.repeatScan());
    }

    public void dataElaborated() {
        if (Math.random() < 0.2) {
            // 20% negative
            testerFormState.setValue(TesterFormState.negative());
        }
        else {
            // 80% positive
            testerFormState.setValue(TesterFormState.positive());
        }
    }



}
