package com.lucasdachman.resistorcalculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

public class FourStripFragment extends Fragment {
    int d1, d2;
    double multiplier, tolerance;
    ResultFragment resultFragment;
    FragmentManager fragmentManager;


    public FourStripFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_four_strip, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //resultFragment = (ResultFragment) getFragmentManager().findFragmentById(R.id.result_fragment_4);
        resultFragment = (ResultFragment) getChildFragmentManager().getFragments().get(0);
        setSpinnerAdapters();
    }

    private void setResults() {
        if (resultFragment != null) {
            Log.i("AHHH", "res frag not null");
            double resistance = ((d1 * 10) + d2) * multiplier;
            double min = resistance - (resistance * tolerance);
            double max = resistance + (resistance * tolerance);
            resultFragment.setFields(resistance, tolerance, min, max);
            return;
        }
        Log.i("AHHH", "res frag is null");

    }

    private void setSpinnerAdapters() {
        /* Get reference to all 4 spinners */
        Spinner band1 = getView().findViewById(R.id.spinner4_1);
        Spinner band2 = getView().findViewById(R.id.spinner4_2);
        Spinner band3 = getView().findViewById(R.id.spinner4_3);
        Spinner band4 = getView().findViewById(R.id.spinner4_4);

        /* Set color object lists for each spinner */
        band1.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.getDigit1Colors()));
        band2.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.getDigit23Colors()));
        band3.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.geMultiplierColors()));
        band4.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.geToleranceColors()));

        /* set onItemSelectedListeners */
        band1.setOnItemSelectedListener(new OnBand1SelectedListener());
        band2.setOnItemSelectedListener(new OnBand2SelectedListener());
        band3.setOnItemSelectedListener(new OnBand3SelectedListener());
        band4.setOnItemSelectedListener(new OnBand4SelectedListener());
    }


    /* OnItemSelected functions for each band */
    private class OnBand1SelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ResistorColor colorItem = (ResistorColor) parent.getItemAtPosition(position);
            d1 = (int) colorItem.getValue();
            setResults();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnBand2SelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ResistorColor colorItem = (ResistorColor) parent.getItemAtPosition(position);
            d2 = (int) colorItem.getValue();
            setResults();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnBand3SelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ResistorColor colorItem = (ResistorColor) parent.getItemAtPosition(position);
            multiplier = colorItem.getValue();
            setResults();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnBand4SelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ResistorColor colorItem = (ResistorColor) parent.getItemAtPosition(position);
            tolerance = colorItem.getValue();
            setResults();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


}
