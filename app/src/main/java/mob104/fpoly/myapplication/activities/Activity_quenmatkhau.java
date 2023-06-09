package mob104.fpoly.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mob104.fpoly.myapplication.MainActivity;
import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.models.BanchoiModel;
import mob104.fpoly.myapplication.models.NhanvienModel;

public class Activity_quenmatkhau extends AppCompatActivity {
    EditText ed_cccd_quenmk, ed_xacnhanmk_quenmk , ed_user_name_quenmk;
    Button btn_thaydmk_quenmk;
    TextInputLayout ed_password_quenmk;
    TextView tv_tb_qmk;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userReference = database.getReference().child("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_quenmatkhau);

        ed_cccd_quenmk = findViewById(R.id.ed_cccd_quenmk);
        ed_password_quenmk = findViewById(R.id.ed_password_quenmk);
        ed_user_name_quenmk = findViewById(R.id.ed_user_name_quenmk);
        ed_xacnhanmk_quenmk = findViewById(R.id.ed_xacnhanmk_quenmk);
        btn_thaydmk_quenmk = findViewById(R.id.btn_thaydmk_quenmk);
        tv_tb_qmk = findViewById(R.id.tv_tb_qmk);



        btn_thaydmk_quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cccd = ed_cccd_quenmk.getText().toString().trim();
                String username = ed_user_name_quenmk.getText().toString().trim();
                String passwd = ed_password_quenmk.getEditText().toString().trim();
                String passl2 = ed_xacnhanmk_quenmk.getText().toString().trim();

                if (cccd.isEmpty() || username.isEmpty() || passwd.isEmpty() || passl2.isEmpty()) {
                    Toast.makeText(Activity_quenmatkhau.this, "Vui lòng điền đủ thông tin !", Toast.LENGTH_SHORT).show();
                } else {
                    if (passwd.equals(passl2)) {

                        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    // Dữ liệu tồn tại trong bảng User
                                    Log.d("User", " Dữ liệu tồn tại trong bảng User");

                                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                        // thuộc tính userName của user trong child là tên cột getva là kiểu dữ liệu
                                        String username1 = userSnapshot.child("username").getValue(String.class);
                                        String cccd1 = userSnapshot.child("cccd").getValue(String.class);

                                        if (username.equals(username1) && cccd.equals(cccd1)) {
                                            tv_tb_qmk.setText("");
                                            String path = "Table/";
                                            DatabaseReference ref = database.getReference(path);
                                            ref.child("passwd").setValue(passwd);
                                            Intent intent = new Intent(Activity_quenmatkhau.this, SignInActivity.class);
                                            startActivity(intent);
                                        } else {
                                            tv_tb_qmk.setText("Thông tin bạn nhập không chính xác!");
                                        }

                                    }
                                } else {
                                    // Không có dữ liệu trong bảng "User"
                                    Log.d("User", "Không có dữ liệu trong bảng User");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                // Xử lý lỗi nếu cần
                            }
                        });
                    } else {
                        Toast.makeText(Activity_quenmatkhau.this, "Mật khẩu xác nhận không chính xác !", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }
}