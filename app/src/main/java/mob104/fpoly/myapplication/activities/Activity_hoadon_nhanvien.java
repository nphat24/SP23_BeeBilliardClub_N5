package mob104.fpoly.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mob104.fpoly.myapplication.R;

public class Activity_hoadon_nhanvien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon_nhanvien);
        TextView tv_tonggiochoi = findViewById(R.id.tv_tonggiochoi);
        TextView tv_giochoi = findViewById(R.id.tv_giochoi);
        TextView tv_price = findViewById(R.id.tv_price);
        TextView tv_giochoi2 = findViewById(R.id.tv_giochoi2);
        TextView tv_total_money = findViewById(R.id.tv_total_money);
        TextView tv_total_money2 = findViewById(R.id.tv_total_money2);
        Button btn_huy = findViewById(R.id.btn_huy);
        Button btn_hoantat = findViewById(R.id.btn_hoantat);

        Intent intent = getIntent();
        String stringStartTime = intent.getStringExtra("start");
        String stringEndTime = intent.getStringExtra("end");
        String price = intent.getStringExtra("price");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date startTime = dateFormat.parse(stringStartTime);
            Date endTime = dateFormat.parse(stringEndTime);
            long duration = endTime.getTime() - startTime.getTime();
            long hours = duration / (60 * 60 * 1000);
            long minutes = (duration % (60 * 60 * 1000)) / (60 * 1000);
            long total_minutes = duration / (60 * 1000);

            tv_tonggiochoi.setText("Từ " + stringStartTime + " đến " + stringEndTime  + " (" + hours + " giờ " + minutes + " phút )");

            tv_giochoi.setText(total_minutes + " phút");
            tv_giochoi2.setText("Tổng giờ chơi: " + hours + " giờ " + minutes + " phút");

            tv_price.setText(price + " đ x");

            try {
                int giatien = Integer.parseInt(price);
                int giatien_phut = giatien/60;
                long total_money = giatien_phut * total_minutes;
                tv_total_money.setText(total_money + " đ");
                tv_total_money2.setText("Giá: " + total_money + " đ");
            }catch (Exception e){

            }

        } catch (ParseException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
            // aaa
        }

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_hoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_hoadon_nhanvien.this,Activity_hoadon.class);
                intent.putExtra("tonggio", tv_tonggiochoi.getText());
                intent.putExtra("giatien", tv_price.getText());
                intent.putExtra("giochoi", tv_giochoi.getText());
                intent.putExtra("tongtien", tv_total_money.getText());
                startActivity(intent);

            }
        });



    }
}