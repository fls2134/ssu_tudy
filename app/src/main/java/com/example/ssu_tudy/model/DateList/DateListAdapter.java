package com.example.ssu_tudy.model.DateList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssu_tudy.R;
import com.example.ssu_tudy.UI.CalendarFragment;
import com.example.ssu_tudy.UI.MainActivity;
import com.example.ssu_tudy.UI.fragmentchange;

import java.util.ArrayList;
import java.util.Calendar;

public class DateListAdapter extends BaseAdapter {
    LayoutInflater inflater =null;
    Activity activity;
    Context context;
    ArrayList<DateListArray> dateList_item;
    LinearLayout layout;

    TextView tvDate;
    Calendar mCal;


    public DateListAdapter(Context context, ArrayList<DateListArray> dateList_item) {
        this.context = context;
        this.dateList_item = dateList_item;
    }

    @Override
    public int getCount() {
        return this.dateList_item.size();
    }

    @Override
    public Object getItem(int position) {
        return this.dateList_item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if(view==null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_gridview,null);
        }

        tvDate = view.findViewById(R.id.gridItem_date);
        layout = view.findViewById(R.id.gridItem_layout);

        tvDate.setText(dateList_item.get(position).getDate());

        final String empty = "";
        if(!empty.equals(dateList_item.get(position).getDate())) {
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // n번째 오는 친구선택
                            Toast.makeText(context, dateList_item.get(position).getDate(), Toast.LENGTH_LONG).show();
                            

                        }
                    }, 0);

                }
            });
        }

        mCal = Calendar.getInstance();
        Integer today = mCal.get(Calendar.DAY_OF_MONTH);
        String sToday = String.valueOf(today);
        if (sToday.equals(dateList_item.get(position).getDate())) {
            tvDate.setTextColor(context.getResources().getColor(R.color.black));
        }

        return view;
    }

}
