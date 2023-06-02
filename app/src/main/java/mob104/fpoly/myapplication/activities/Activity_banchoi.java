package mob104.fpoly.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mob104.fpoly.myapplication.R;

public class Activity_banchoi extends AppCompatActivity {

    TextView tvTimeCur;
    LinearLayout btnTime;
    TextView tvTime;
    ImageView img;
    boolean isPlaying = false;


    TextView tvshowTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banchoi);
        tvTimeCur = findViewById(R.id.tv_time_current); // tv show thời gian thực
        btnTime = findViewById(R.id.btn_time); // để start time pause time
        img = findViewById(R.id.image); // img để thay đổi play pause
        tvTime = findViewById(R.id.tv_time); // tv show bắt đầu or dừng lại
        tvshowTime = findViewById(R.id.tv_start_time); // show dialog time

        String timeStamp = new SimpleDateFormat("HH-mm").format(Calendar.getInstance().getTime());
        Log.e("GET TIME ", timeStamp);
        tvTimeCur.setText("Thời gian bắt đầu: " + timeStamp);

        btnTime.setOnClickListener(view ->{
            if (!isPlaying){
                // true
                img.setImageResource(R.drawable.stop);
                tvTime.setText("dừng lại");
                isPlaying = true;
            }else{
                // false
                img.setImageResource(R.drawable.batdau);
                tvTime.setText("Bắt đầu");

                isPlaying = false;
            }
        });



    tvshowTime.setOnClickListener(view -> {
        showDialogTime();
    });



    }


    private void showDialogTime(){
        int hour = 23;
        int minute = 59;
        boolean is24Hours = true;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String str = hour+":"+minute;
                tvshowTime.setText(str);

            }
        },hour,minute,is24Hours);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.show();
    }
}