package mob104.fpoly.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import mob104.fpoly.myapplication.MainActivity;
import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.models.BanchoiModel;
import mob104.fpoly.myapplication.models.NhanvienModel;

public class SignInActivity extends AppCompatActivity {
    TextInputLayout ed_DN_password;
    EditText  ed_DN_username;
    Button btn_sign_in;
    TextView tv_quenmk;
    CheckBox cb_luumk;
    String strUser, strPass;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userReference = database.getReference().child("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ed_DN_username = findViewById(R.id.ed_DN_username);
        ed_DN_password = findViewById(R.id.ed_DN_password);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        tv_quenmk = findViewById(R.id.tv_quenmk);
        cb_luumk = findViewById(R.id.cb_luumk);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME","");
        String pass = pref.getString("PASSWORD","");
        Boolean luuMk = pref.getBoolean("REMEMBER", false);
        ed_DN_password.getEditText().setText(pass);
        ed_DN_username.setText(user);
        cb_luumk.setChecked(luuMk);


        // select user firebase
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Dữ liệu tồn tại trong bảng User
                    Log.d("User", " Dữ liệu tồn tại trong bảng User");

                    for (DataSnapshot userSnapshot: snapshot.getChildren()){
                        // thuộc tính userName của user trong child là tên cột getva là kiểu dữ liệu
                        String username = userSnapshot.child("username").getValue(String.class);
                        System.out.println("Username: " + username);
                        Log.e("User", username );

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

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkLogin();

            }
        });

        tv_quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this,Activity_quenmatkhau.class);
                startActivity(intent);
            }
        });
    }






    public void checkLogin(){
        strUser = ed_DN_username.getText().toString().trim();
        strPass = ed_DN_password.getEditText().getText().toString().trim();

        DatabaseReference ref = database.getReference("User");
        ref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NhanvienModel nhanvienModel = snapshot.getValue(NhanvienModel.class);
                if(strUser.isEmpty()||strPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else if (nhanvienModel != null){
                    if (nhanvienModel.getUsername().equals(strUser) && nhanvienModel.getPasswd().equals(strPass)){
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        rememberUser(strUser, strPass, cb_luumk.isChecked());
                        Intent intent1 = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent1);
                    }else {
                        Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu của bạn nhập sai", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Hiện tại chưa có tài khoản hoạt động !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status){
            editor.clear();
        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }
}