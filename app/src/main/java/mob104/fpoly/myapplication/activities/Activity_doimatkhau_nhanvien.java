package mob104.fpoly.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.frag.HosoNhanvienFragment;

public class Activity_doimatkhau_nhanvien extends AppCompatActivity {
    Toolbar tbar_back_dmk_nv;
    TextView tv_hoten_doimk_nv, tv_taikhoan_doimk_nv;
    TextInputLayout ed_password_doimk_nv,ed_passwordmoi_doimk_nv,ed_xacnhanmk_doimk_nv;
    Button btn_Cancel_doimk_hsnv, btn_hoantat_doimk_hsnv;
    String passwdcu , passwdmoi , passmoil2 ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference nhanvienReference = database.getReference().child("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau_hsnv);

        tbar_back_dmk_nv = findViewById(R.id.tbar_back_dmk_nv);
        tv_hoten_doimk_nv = findViewById(R.id.tv_hoten_doimk_nv);
        tv_taikhoan_doimk_nv = findViewById(R.id.tv_taikhoan_doimk_nv);
        ed_password_doimk_nv = findViewById(R.id.ed_password_doimk_nv);
        ed_passwordmoi_doimk_nv = findViewById(R.id.ed_passwordmoi_doimk_nv);
        ed_xacnhanmk_doimk_nv = findViewById(R.id.ed_xacnhanmk_doimk_nv);
        btn_Cancel_doimk_hsnv = findViewById(R.id.btn_Cancel_doimk_hsnv);
        btn_hoantat_doimk_hsnv = findViewById(R.id.btn_hoantat_doimk_hsnv);


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
                            tv_hoten_doimk_nv.setText("Họ tên: " + name);
                            tv_taikhoan_doimk_nv.setText("Tài khoản: " + username1);

                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });

        tbar_back_dmk_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_Cancel_doimk_hsnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_password_doimk_nv.getEditText().setText("");
                ed_passwordmoi_doimk_nv.getEditText().setText("");
                ed_xacnhanmk_doimk_nv.getEditText().setText("");
            }
        });
        btn_hoantat_doimk_hsnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwdcu = ed_password_doimk_nv.getEditText().getText().toString().trim();
                passwdmoi = ed_passwordmoi_doimk_nv.getEditText().getText().toString().trim();
                passmoil2 = ed_xacnhanmk_doimk_nv.getEditText().getText().toString().trim();
                if (passwdcu.isEmpty() || passwdmoi.isEmpty() || passmoil2.isEmpty()) {
                    Toast.makeText(Activity_doimatkhau_nhanvien.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    if (passwdmoi.equals(passmoil2)) {
                        nhanvienReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                        String username1 = userSnapshot.child("username").getValue(String.class);
                                        Query query = nhanvienReference.orderByChild("username").equalTo(username1);

                                        if (username.equals(username1)) {
                                            String pass = userSnapshot.child("passwd").getValue(String.class);

                                            if (passwdcu.equals(pass)) {
                                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        for (DataSnapshot userSnapshot1 : snapshot.getChildren()) {
                                                            DatabaseReference userRef = userSnapshot1.getRef();

                                                            userRef.child("passwd").setValue(passwdmoi);
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                                Toast.makeText(Activity_doimatkhau_nhanvien.this, "Đổi mật khẩu thành công !", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(Activity_doimatkhau_nhanvien.this, " Mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        ed_password_doimk_nv.getEditText().setText("");
                        ed_passwordmoi_doimk_nv.getEditText().setText("");
                        ed_xacnhanmk_doimk_nv.getEditText().setText("");
                    }else {
                        Toast.makeText(Activity_doimatkhau_nhanvien.this, "Mật khẩu nhập lại không đúng !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

