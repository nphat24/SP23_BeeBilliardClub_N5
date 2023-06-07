package mob104.fpoly.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mob104.fpoly.myapplication.R;

public class Activity_hoadon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);
        TextView tv_tongtien = findViewById(R.id.tv_tongtien);
        TextView tv_khachcantra = findViewById(R.id.tv_khachcantra);
        TextView tv_khachdathanhtoan = findViewById(R.id.tv_khachdathanhtoan);
        Button btn = findViewById(R.id.btn_hoantatthanhtoan);

        Intent intent = getIntent();
        String tonggio = intent.getStringExtra("tonggio");
        String giatien = intent.getStringExtra("giatien");
        String giochoi = intent.getStringExtra("giochoi");
        String tongtien = intent.getStringExtra("tongtien");

        tv_tongtien.setText(tongtien);
        tv_khachcantra.setText(tongtien);
        tv_khachdathanhtoan.setText(tongtien);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_hoadon.this,Activity_hoadonchitiet.class);
                intent.putExtra("tonggio", tonggio);
                intent.putExtra("giatien", giatien);
                intent.putExtra("giochoi", giochoi);
                intent.putExtra("tongtien", tongtien);
                startActivity(intent);
            }
        });





    }
}