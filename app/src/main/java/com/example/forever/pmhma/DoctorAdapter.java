package com.example.forever.pmhma;

import android.content.Context;
import android.content.Intent;
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
        Button detailsBtn;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        LayoutInflater inflater =   (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final ViewHolder holder;
        if(convertView==null) {
            holder          =   new ViewHolder();

            convertView     = inflater.inflate(R.layout.row_layout, parent, false);
            holder.nameTv   = (TextView) convertView.findViewById(R.id.doctorName);
            holder.phoneTV  = (TextView) convertView.findViewById(R.id.doctorPhoneNumber);
            holder.emailTV  = (TextView) convertView.findViewById(R.id.doctorEmail);
            holder.detailsBtn  = (Button) convertView.findViewById(R.id.docDetails);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTv.setText(doctorAdapters.get(position).getDocName());
        holder.phoneTV.setText(doctorAdapters.get(position).getDocPhone());
        holder.emailTV.setText(doctorAdapters.get(position).getDocEmail());

        //final View finalConvertView = convertView;
        holder.detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String docName = holder.nameTv.getText().toString();
                String docSpecialist= doctorAdapters.get(position).getDocDetails();
                String docApoint    = doctorAdapters.get(position).getDocApnmnt().toString();
                int     rowId       = doctorAdapters.get(position).getDocId();
                String docPhone     = holder.phoneTV.getText().toString();
                String docEmail     = holder.emailTV.getText().toString();


                parent.getContext().startActivity(new Intent(parent.getContext(),DoctorDetailsActivity.class)
                .putExtra("id",rowId)
                .putExtra("doctorName",docName)
                .putExtra("doctorPhone",docPhone)
                .putExtra("doctorEmail",docEmail)
                .putExtra("doctorApoint",docApoint)
                .putExtra("docSpecialist",docSpecialist));
                /*convertView.startActivity(new Intent(DoctorAdapter.this,DoctorDetailsActivity.class)
                        .putExtra("doctorObj",doctors));*/
            }
        });
        return convertView;
    }
}
