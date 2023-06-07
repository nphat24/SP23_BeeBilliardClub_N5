package mob104.fpoly.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import mob104.fpoly.myapplication.R;


public class Activity_hoadonchitiet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadonchitiet);
        TextView tv_tenhang = findViewById(R.id.tv_tenhang);
        TextView tv_dongia = findViewById(R.id.tv_dongia);
        TextView tv_soluong = findViewById(R.id.tv_soluong);
        TextView tv_thanhtien = findViewById(R.id.tv_thanhtien);
        TextView tv_tongtienhang = findViewById(R.id.tv_tongtienhang);
        TextView tv_tongcong = findViewById(R.id.tv_tongcong);

        Intent intent = getIntent();
        String tonggio = intent.getStringExtra("tonggio");
        String giatien = intent.getStringExtra("giatien");
        String giochoi = intent.getStringExtra("giochoi");
        String tongtien = intent.getStringExtra("tongtien");

        tv_tenhang.setText(tonggio);
        tv_dongia.setText(giatien);
        tv_soluong.setText(giochoi);
        tv_thanhtien.setText(tongtien);
        tv_tongtienhang.setText(tongtien);
        tv_tongcong.setText(tongtien);


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Bạn không thể quay lại ở màn hình này", Toast.LENGTH_SHORT).show();
    }
}