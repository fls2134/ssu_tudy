package com.example.ssu_tudy.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.ssu_tudy.R;
import com.example.ssu_tudy.model.DateList.DateListAdapter;
import com.example.ssu_tudy.model.DateList.DateListArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarFragment extends Fragment {
    private GridView gridView;
    private Calendar mCal;

    private ArrayList<DateListArray> dateList;
    private DateListAdapter dateListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);

        gridView = (GridView)view.findViewById(R.id.grid_view);

        dateList = new ArrayList<>();
        setCalendarDate();

        dateListAdapter = new DateListAdapter(getActivity(), dateList);
        gridView.setAdapter(dateListAdapter);
    }

    private void setCalendarDate() {
        long now = System.currentTimeMillis();
        final Date date = new Date(now);

        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);

        ((MainActivity)getActivity()).changeToolbarText(curYearFormat.format(date) + "." + curMonthFormat.format(date));
        //tvDate.setText(curYearFormat.format(date) + "." + curMonthFormat.format(date));

        mCal = Calendar.getInstance();
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        for (int i = 1; i < dayNum; i++) {
            dateList.add(new DateListArray(""));
        }

        mCal.set(Calendar.MONTH, mCal.get(Calendar.MONTH));
        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dateList.add(new DateListArray("" + (i + 1)));
        }

        for (int i = 0; i < dateList.size() % 7; i++) {
            dateList.add(new DateListArray(""));
        }

    }

}
