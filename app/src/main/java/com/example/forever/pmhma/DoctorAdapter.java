package com.example.forever.pmhma;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Forever on 4/6/2017.
 */

public class DoctorAdapter extends ArrayAdapter<Doctor>{
    private Context context;
    private ArrayList<Doctor> doctorAdapters;
    public DoctorAdapter(@NonNull Context context, ArrayList<Doctor> doctorAdapters) {
        super(context, R.layout.row_layout, doctorAdapters);
        this.context    =   context;
        this.doctorAdapters = doctorAdapters;
    }

    class ViewHolder{
        TextView nameTv;
        TextView phoneTV;
        TextView emailTV;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =   (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;
        if(convertView==null) {
            holder          =   new ViewHolder();

            convertView     = inflater.inflate(R.layout.row_layout, parent, false);
            holder.nameTv   = (TextView) convertView.findViewById(R.id.doctorName);
            holder.phoneTV  = (TextView) convertView.findViewById(R.id.doctorPhoneNumber);
            holder.emailTV  = (TextView) convertView.findViewById(R.id.doctorEmail);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTv.setText(doctorAdapters.get(position).getDocName());
        holder.phoneTV.setText(doctorAdapters.get(position).getDocPhone());
        holder.emailTV.setText(doctorAdapters.get(position).getDocEmail());

        return convertView;
    }
}
