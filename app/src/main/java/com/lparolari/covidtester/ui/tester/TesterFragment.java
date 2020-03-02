package com.lparolari.covidtester.ui.tester;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.lparolari.covidtester.R;

import java.util.TimerTask;

public class TesterFragment extends Fragment {

    private static final int ELABORATING_TIME = 5000;
    private static final int SCANNING_TIME = 3000;

    private TesterViewModel testerViewModel = new TesterViewModel();
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tester, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView info = view.findViewById(R.id.info);
        final ConstraintLayout touch = view.findViewById(R.id.touch);

        testerViewModel.startTest();

        testerViewModel.getTesterFormState().observe(getViewLifecycleOwner(), new Observer<TesterFormState>() {
            @Override
            public void onChanged(final TesterFormState testerFormState) {
                if (testerFormState == null) {
                    return;
                }
                info.setText(getString(testerFormState.getLabel()));

                if (testerFormState.getPhase().equals(TesterFormState.ELABORATING_DATA)) {

                    handler.postDelayed(new TimerTask() {
                        @Override
                        public void run() {
                            testerViewModel.dataElaborated();
                        }
                    }, 5000);
                }
            }
        });

        touch.setOnTouchListener(new View.OnTouchListener() {
            private long firstTouch = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            testerViewModel.canRelease();
                        }
                    }, SCANNING_TIME);
                    testerViewModel.scanningFinger();
                    firstTouch = System.currentTimeMillis();
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    long currentTouch = System.currentTimeMillis();
                    if (currentTouch - firstTouch >= 3000)
                        testerViewModel.fingerScanned();
                    else
                        testerViewModel.fingerNotScanned();
                }
                return true;
            }
        });
        /*view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });*/
    }
}
