package mob104.fpoly.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mob104.fpoly.myapplication.MainActivity;
import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.frag.HosoNhanvienFragment;

public class Activity_thongtinnhanvien extends AppCompatActivity {
    TextView tv_hoten_thongtinnv, tv_taikhoan_thongtinnv, tv_ngaysinh_thongtincn_nv, tv_cccd_thongtincn_nv;
    Toolbar tbar_back_ttcn_nv;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference nhanvienReference = database.getReference().child("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtincanhan_nhanvien);

        tv_hoten_thongtinnv = findViewById(R.id.tv_hoten_thongtinnv);
        tv_taikhoan_thongtinnv = findViewById(R.id.tv_taikhoan_thongtinnv);
        tv_cccd_thongtincn_nv = findViewById(R.id.tv_cccd_thongtincn_nv);
        tv_ngaysinh_thongtincn_nv = findViewById(R.id.tv_ngaysinh_thongtincn_nv);
        tbar_back_ttcn_nv = findViewById(R.id.tbar_back_ttcn_nv);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        nhanvienReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                        String username1 = userSnapshot.child("username").getValue(String.class);

                        if (username.equals(username1)){
                            String name = userSnapshot.child("name").getValue(String.class);
                            String cccd = userSnapshot.child("cccd").getValue(String.class);
                            String ngaysinh = userSnapshot.child("birth_date").getValue(String.class);
                            tv_hoten_thongtinnv.setText("Họ tên: " + name);
                            tv_taikhoan_thongtinnv.setText("Tài khoản: " + username1);
                            tv_cccd_thongtincn_nv.setText(cccd);
                            tv_ngaysinh_thongtincn_nv.setText(ngaysinh);
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });

        tbar_back_ttcn_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
