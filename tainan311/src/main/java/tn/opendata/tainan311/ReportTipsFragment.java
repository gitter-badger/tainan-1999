package tn.opendata.tainan311;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.content.SharedPreferences;

/**
 * Created by sam on 2014/6/11.
 */
public class ReportTipsFragment extends WizardFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private CheckBox mCheck_ignore_tips;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ReportTipsFragment newInstance() {
        ReportTipsFragment fragment = new ReportTipsFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//        fragment.setArguments(args);
        return fragment;
    }

    private ReportTipsFragment() {
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //FIXME: only for test..
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                setReady(true);
            }
        }, 1000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        boolean isIgnore = ReportTipsFragment.getIgnorePref(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_tips, container, false);
        mCheck_ignore_tips = (CheckBox)rootView.findViewById(R.id.checkbox_ignore_tips);
        mCheck_ignore_tips.setChecked(isIgnore);
        return rootView;
    }

    @Override
    public Bundle onNextClick(Bundle acc) {
        return acc;
    }

    @Override
    public void onStop() {
        super.onStop();

        if(mCheck_ignore_tips !=null)
            setIgnoreTipPref(getActivity(),mCheck_ignore_tips.isChecked());
    }

    private static final String PREFS_NAME = "tn.opendata.tainan311.tips";
    private static final String PREFS_KEY = "tn.opendata.tainan311.tips.ignore";

    public static boolean getIgnorePref(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(PREFS_KEY, false);
    }

    private void setIgnoreTipPref(Context context,boolean ignore){
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putBoolean(PREFS_KEY, ignore);
        prefs.commit();
    }



}

