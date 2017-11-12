package com.log515.lambda.wherework.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.pavlospt.CircleView;
import com.log515.lambda.wherework.R;
import com.log515.lambda.wherework.model.LocalOccupation;

import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by ttauveron on 11/11/17.
 */

public class LocalOccupationAdapter extends ArrayAdapter<LocalOccupation> {

    private LayoutInflater inflater;
    private Context context;

    public LocalOccupationAdapter(@NonNull Context context, int resource, @NonNull List<LocalOccupation> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.row_local, parent, false);
            holder = new ViewHolder();

            holder.cvMorning = view.findViewById(R.id.cv_morning);
            holder.cvAfternoon = view.findViewById(R.id.cv_afternoon);
            holder.cvEvening = view.findViewById(R.id.cv_evening);
            holder.tvJour = view.findViewById(R.id.tv_jour);
            holder.tvLocal = view.findViewById(R.id.tv_local);

            view.setTag(holder);
        }

        LocalOccupation item = getItem(position);

        holder.tvLocal.setText(item.getLocal());

        LocalDate date = new LocalDate();
        date = date.withDayOfWeek(item.getDayOfWeek() == 0 ? 7 : item.getDayOfWeek());

        holder.tvJour.setText(date.dayOfWeek().getAsText());

        int red = context.getResources().getColor(R.color.red_ets);
        int green = context.getResources().getColor(R.color.green_3);

        holder.cvMorning.setBackgroundColor(item.isMorning() ? red : green);
        holder.cvAfternoon.setBackgroundColor(item.isAfternoon() ? red : green);
        holder.cvEvening.setBackgroundColor(item.isEvening() ? red : green);

        return view;
    }

    static class ViewHolder {
        CircleView cvMorning;
        CircleView cvAfternoon;
        CircleView cvEvening;
        TextView tvLocal;
        TextView tvJour;
    }

}
