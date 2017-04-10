package com.example.forever.pmhma;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

public class MedicalHistoryAdapter extends ArrayAdapter<Doctor>{
    private Context context;
    private ArrayList<MedicalHistory> medicalHistories;

    public MedicalHistoryAdapter(@NonNull Context context, ArrayList<MedicalHistory> medicalHistories) {
       // super(context, R.layout.medical_history_layout, medicalHistories);
        super(context, R.layout.medical_history_layout, medicalHistories);
        this.context    =   context;
        this.medicalHistories = medicalHistories;
    }


    class ViewHolder{
        ImageView medicalImageIV;
        TextView doctorNameTV;
        TextView addDateTV;
        Button detailsBtn;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        LayoutInflater inflater =   (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final ViewHolder holder;
        if(convertView==null) {
            holder          =   new ViewHolder();

            convertView     = inflater.inflate(R.layout.medical_history_layout, parent, false);
            holder.medicalImageIV = (ImageView) convertView.findViewById(R.id.prescriptionImage);
            holder.doctorNameTV = (TextView) convertView.findViewById(R.id.doctorName);
            holder.addDateTV = (TextView) convertView.findViewById(R.id.addDate);
            holder.detailsBtn  = (Button) convertView.findViewById(R.id.docDetails);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //holder.medicalImageIV.setImageResource(medicalHistories.get(position).getImageName());
        //holder.doctorNameTV.setText(medicalHistories.get(position).getmHistoryId());
        holder.addDateTV.setText(medicalHistories.get(position).getAddDate());

        //final View finalConvertView = convertView;
        holder.detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorName = holder.doctorNameTV.getText().toString();
                String addDate    = medicalHistories.get(position).getAddDate().toString();
                int     rowId       = medicalHistories.get(position).getmHistoryId();

               // Intent i = new Intent(parent.getContext(),MedicalListActivity.class);
                //i.putExtra("ebookImage",ebookImage);
                holder.medicalImageIV.buildDrawingCache();
                Bitmap image= holder.medicalImageIV.getDrawingCache();


                parent.getContext().startActivity(new Intent(parent.getContext(),MedicalListActivity.class)
                .putExtra("id",rowId)
                .putExtra("doctorName",doctorName)
                .putExtra("prescriptionDate",addDate));


            }
        });
        return convertView;
    }
}
