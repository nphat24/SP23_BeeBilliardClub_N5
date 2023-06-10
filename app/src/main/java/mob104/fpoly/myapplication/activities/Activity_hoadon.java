package mob104.fpoly.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mob104.fpoly.myapplication.R;

public class Activity_hoadon extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

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
        String position = intent.getStringExtra("position");
        String name = intent.getStringExtra("name");

        tv_tongtien.setText(tongtien);
        tv_khachcantra.setText(tongtien);
        tv_khachdathanhtoan.setText(tongtien);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String path ="Table/" + position;
                DatabaseReference ref = database.getReference(path);
                ref.child("start").setValue("");
                ref.child("end").setValue("");



                Intent intent = new Intent(Activity_hoadon.this,Activity_hoadonchitiet.class);
                intent.putExtra("tonggio", tonggio);
                intent.putExtra("giatien", giatien);
                intent.putExtra("giochoi", giochoi);
                intent.putExtra("tongtien", tongtien);
                intent.putExtra("position",position);
                intent.putExtra("name",name);
                intent.putExtra("check","save");
                startActivity(intent);
                finish();
            }
        });





    }
}