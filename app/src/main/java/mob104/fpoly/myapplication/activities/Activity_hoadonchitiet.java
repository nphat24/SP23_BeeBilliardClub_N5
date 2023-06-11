package mob104.fpoly.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mob104.fpoly.myapplication.MainActivity;
import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.models.HoadonModel;


public class Activity_hoadonchitiet extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Bill");

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
        TextView tv_close = findViewById(R.id.tv_close);
        TextView tv_name = findViewById(R.id.tv_name);
        TextView tv_giovao =findViewById(R.id.tv_giovao);
        TextView tv_giora =findViewById(R.id.tv_giora);

        Intent intent = getIntent();
        String tonggio = intent.getStringExtra("tonggio");
        String giatien = intent.getStringExtra("giatien");
        String giochoi = intent.getStringExtra("giochoi");
        String tongtien = intent.getStringExtra("tongtien");
        String name = intent.getStringExtra("name");
        String check = intent.getStringExtra("check");

        int viTriDen = tonggio.indexOf("đến");


        String phanTruoc = tonggio.substring(2, viTriDen).trim();
        tv_giovao.setText("Giờ vào: " + phanTruoc);

        String phanSau = tonggio.substring(viTriDen + 3, tonggio.indexOf("(")).trim();
        tv_giora.setText("Giờ ra: " + phanSau);

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = dateFormat.format(currentDate);
        if (check.equals("save")){
            HoadonModel hoadonModel = new HoadonModel(dateString,giatien,tonggio,name,giochoi,tongtien,"staff");
            ref.push().setValue(hoadonModel);
        }

        tv_name.setText(name);
        tv_tenhang.setText(tonggio);
        tv_dongia.setText(giatien);
        tv_soluong.setText(giochoi);
        tv_thanhtien.setText(tongtien);
        tv_tongtienhang.setText(tongtien);
        tv_tongcong.setText(tongtien);

        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_hoadonchitiet.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Bạn không thể quay lại ở màn hình này", Toast.LENGTH_SHORT).show();
    }
}