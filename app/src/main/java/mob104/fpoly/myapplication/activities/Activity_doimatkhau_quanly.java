package mob104.fpoly.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import mob104.fpoly.myapplication.frag.HosoQuanliFragment;

public class Activity_doimatkhau_quanly extends AppCompatActivity {
    Toolbar tbar_back_dmk_ql;
    TextView tv_hoten_doimk_hsql , tv_taikhoan_doimk_hsql;
    EditText ed_taikhoan_doimk_hsql , ed_sdt_doimk_hsql;
    TextInputLayout ed_passwokmoi_doimk_hsql , ed_xacnhanmk_doimk_hsql;
    Button btn_Cancel_dmk_hsql , btn_hoantat_doimk_hsql;
    String strSdt , strPassmoi , strPassxn , strName;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference quanlyReference = database.getReference().child("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau_hsql);

        tbar_back_dmk_ql = findViewById(R.id.tbar_back_dmk_ql);
        tv_hoten_doimk_hsql = findViewById(R.id.tv_hoten_doimk_hsql);
        tv_taikhoan_doimk_hsql = findViewById(R.id.tv_taikhoan_doimk_hsql);
        ed_taikhoan_doimk_hsql = findViewById(R.id.ed_taikhoan_doimk_hsql);
        ed_sdt_doimk_hsql = findViewById(R.id.ed_sdt_doimk_hsql);
        ed_passwokmoi_doimk_hsql = findViewById(R.id.ed_passwokmoi_doimk_hsql);
        ed_xacnhanmk_doimk_hsql = findViewById(R.id.ed_xacnhanmk_doimk_hsql);
        btn_Cancel_dmk_hsql = findViewById(R.id.btn_Cancel_dmk_hsql);
        btn_hoantat_doimk_hsql = findViewById(R.id.btn_hoantat_doimk_hsql);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        quanlyReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                        String username1 = userSnapshot.child("username").getValue(String.class);

                        if (username.equals(username1)){
                            String name = userSnapshot.child("name").getValue(String.class);
                            tv_hoten_doimk_hsql.setText("Họ tên: " + name);
                            tv_taikhoan_doimk_hsql.setText("Tài khoản: " + username1);

                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });

        btn_hoantat_doimk_hsql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strName = ed_taikhoan_doimk_hsql.getText().toString().trim();
                strSdt = ed_sdt_doimk_hsql.getText().toString().trim();
                strPassmoi = ed_passwokmoi_doimk_hsql.getEditText().getText().toString().trim();
                strPassxn = ed_xacnhanmk_doimk_hsql.getEditText().getText().toString().trim();
                if (strPassxn.isEmpty() || strPassmoi.isEmpty() || strSdt.isEmpty()||strName.isEmpty()){
                    Toast.makeText(Activity_doimatkhau_quanly.this, "Vui lòng điền đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else {
                    if ( strSdt.length() == 10) {
                        if (strPassmoi.equals(strPassxn)) {
                            Query query = quanlyReference.orderByChild("username").equalTo(username);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot userSnapshot1 : snapshot.getChildren()) {
                                        DatabaseReference userRef = userSnapshot1.getRef();

                                        userRef.child("passwd").setValue(strPassmoi);
                                        userRef.child("phonenumber").setValue(strSdt);
                                        userRef.child("name").setValue(strName);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            Toast.makeText(Activity_doimatkhau_quanly.this, "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                            ed_sdt_doimk_hsql.setText("");
                            ed_taikhoan_doimk_hsql.setText("");
                            ed_passwokmoi_doimk_hsql.getEditText().setText("");
                            ed_xacnhanmk_doimk_hsql.getEditText().setText("");
                        } else {
                            Toast.makeText(Activity_doimatkhau_quanly.this, "Mật khẩu xác nhận không chính xác!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Activity_doimatkhau_quanly.this, "Số điện thoại sai định dạng!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_Cancel_dmk_hsql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_sdt_doimk_hsql.setText("");
                ed_taikhoan_doimk_hsql.setText("");
                ed_passwokmoi_doimk_hsql.getEditText().setText("");
                ed_xacnhanmk_doimk_hsql.getEditText().setText("");
            }
        });
        tbar_back_dmk_ql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
