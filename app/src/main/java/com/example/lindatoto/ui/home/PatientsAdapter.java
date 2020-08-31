package com.example.lindatoto.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lindatoto.NursesActivity;
import com.example.lindatoto.R;
import com.example.lindatoto.models.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.PatientViewHolder>  {

    private Context mCtx;
    private List<Patient> routesList;

    public PatientsAdapter(Context mCtx, List<Patient> routesList) {
        this.mCtx = mCtx;
        this.routesList = routesList;
    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.patient_item, null);

        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PatientViewHolder holder, final int position) {
        final Patient model = routesList.get(position);

        holder.tvName.setText(model.getName());
        String others = "Age: "+model.getAge()+" | Location: "+model.getLocation();
        holder.tvAge.setText(others);
        holder.btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category ="schedules";
                scheduleMeeting(category,holder,model);

            }
        });
        holder.btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category ="messages";
                scheduleMeeting(category,holder,model);
            }


        });





    }
    private void scheduleMeeting(final String category, final PatientViewHolder holder, final Patient model) {
        final Dialog d =new Dialog(holder.itemView.getContext());
        d.setContentView(R.layout.dialog_nurse);
        final Button btnSend =d.findViewById(R.id.btnSend);
        final EditText etDate = d.findViewById(R.id.etDate);
        final EditText etMessage = d.findViewById(R.id.etMessage);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = etDate.getText().toString().trim();
                String message = etMessage.getText().toString().trim();
                if (!TextUtils.isEmpty(date) && !TextUtils.isEmpty(message)) {
                    btnSend.setText(holder.itemView.getContext().getString(R.string.processing));
                    String userId =model.getUserId();
                    sendNotificationOrSchedule(date,message,userId,category);


                }else {
                    Toast.makeText(holder.itemView.getContext(), "Check your Inputs again...", Toast.LENGTH_SHORT).show();
                }
            }

            private void sendNotificationOrSchedule(String date, String message, String userId, String category) {
                DatabaseReference mDatabaseReference= FirebaseDatabase.getInstance().getReference().child( "LINDATOTO/nurses/notifications" );
                String notificationId = mDatabaseReference.push().getKey();
                HashMap<String,Object> notificationMap =new HashMap<>();
                notificationMap.put("notid",notificationId);
                notificationMap.put("category",category);
                notificationMap.put("userId",userId);
                notificationMap.put("message",message);
                notificationMap.put("date",date);
                Calendar cal =Calendar.getInstance();
                String postedon =cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH) +" "+cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
                notificationMap.put("postedon",postedon);
                assert notificationId != null;
                mDatabaseReference.child(userId).child(notificationId).updateChildren(notificationMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            btnSend.setText(holder.itemView.getContext().getString(R.string.success));
                            Toast.makeText(holder.itemView.getContext(), "Notification sent...", Toast.LENGTH_SHORT).show();
                            d.dismiss();
                        }
                    }
                });

            }
        });
        d.show();

    }

    @Override
    public int getItemCount() {

        return routesList.size();
    }
    class PatientViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName,tvAge;
        private Button btnSchedule,btnMessage;


        public PatientViewHolder(View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvAge=itemView.findViewById(R.id.tvAge);
            btnSchedule=itemView.findViewById(R.id.btnSchedule);
            btnMessage=itemView.findViewById(R.id.btnMessage);


        }
    }
}
