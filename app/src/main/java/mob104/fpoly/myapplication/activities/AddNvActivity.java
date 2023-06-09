package mob104.fpoly.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.models.NhanvienModel;
import mob104.fpoly.myapplication.models.User;

public class AddNvActivity extends AppCompatActivity {
    DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("User");
    EditText ed_name;
    EditText ed_username;
    EditText ed_password;
    EditText ed_re_password;
    EditText ed_birth_day;
    EditText ed_cccd;
    EditText ed_luong;
    Button btn_add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nv);
        ed_name = findViewById(R.id.ed_full_name);
        ed_username = findViewById(R.id.ed_account);
        ed_password = findViewById(R.id.ed_password_nv);
        ed_re_password = findViewById(R.id.ed_re_password);
        ed_birth_day = findViewById(R.id.ed_birth_date);
        ed_cccd = findViewById(R.id.ed_cccd);
        ed_luong = findViewById(R.id.ed_luong);
//        btn
        btn_add = findViewById(R.id.btn_add);
        // string
        btn_add.setOnClickListener(view ->{
            String strName = ed_name.getText().toString().trim();
            checkNull(strName);
            String strUserName = ed_username.getText().toString().trim();
            checkNull(strUserName);
            String strPassword = ed_password.getText().toString().trim();
            checkNull(strPassword);
            String strRePassword = ed_re_password.getText().toString().trim();
            checkNull(strRePassword);
            String strBirth = ed_birth_day.getText().toString().trim();
            checkNull(strBirth);
            String strLuong = ed_luong.getText().toString().trim();
            checkNull(strLuong);
            String strCccd = ed_cccd.getText().toString().trim();
            checkNull(strCccd);
            NhanvienModel nv = new NhanvienModel();
            nv.setName(strName);
            nv.setUsername(strUserName);
            nv.setPasswd(strPassword);
            nv.setBirth_date(strBirth);
            nv.setCccd(strCccd);
            nv.setGroup("user");
            nv.setLuong(Double.parseDouble(strLuong));
            Log.d("OBJ", nv.toString());
            pushUser(nv);


        });

    }

    private void checkNull(String str){
        if (str == null || str.length() == 0 || str.equals("")){
            Toast.makeText(this, "Dữ liệu chưa nhập hết", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    private void pushUser(NhanvienModel user){
        // func check push dc khoong
        userReference.push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Thêm thành công
                    Log.d("User", "User added successfully");
                    Toast.makeText(AddNvActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

                } else {
                    // Xử lý lỗi
                    Log.e("User", "Failed to add user: " + task.getException().getMessage());
                    Toast.makeText(AddNvActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}