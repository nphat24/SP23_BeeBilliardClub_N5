package mob104.fpoly.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

                DatabaseReference ref = database.getReference("Table");
                Query query = ref.orderByChild("name").equalTo(name);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            DatabaseReference userRef = userSnapshot.getRef();
                            userRef.child("start").setValue("");
                            userRef.child("end").setValue("");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Xử lý khi có lỗi xảy ra
                    }
                });




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