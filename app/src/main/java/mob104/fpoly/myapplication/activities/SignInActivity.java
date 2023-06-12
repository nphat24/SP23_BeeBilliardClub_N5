package mob104.fpoly.myapplication.activities;

import static android.content.ContentValues.TAG;

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
    TextView tv_quenmk, tv_thongbao;
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
        tv_thongbao = findViewById(R.id.tv_thongbao);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME","");
        String pass = pref.getString("PASSWORD","");
        Boolean luuMk = pref.getBoolean("REMEMBER", false);
        ed_DN_password.getEditText().setText(pass);
        ed_DN_username.setText(user);
        cb_luumk.setChecked(luuMk);


        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strUser = ed_DN_username.getText().toString().trim();
                strPass = ed_DN_password.getEditText().getText().toString().trim();
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
                                String passwd = userSnapshot.child("passwd").getValue(String.class);
                                String group = userSnapshot.child("group").getValue(String.class);

                                if(strUser.isEmpty()||strPass.isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                                }
                                if (strUser.equals(username) && strPass.equals(passwd)){

                                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    rememberUser(strUser, strPass, cb_luumk.isChecked());
                                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                    intent.putExtra("user", strUser);
                                    if (group.equals("admin")){
                                        intent.putExtra("quyen","admin");
                                    }else {
                                        intent.putExtra("quyen", "user");
                                    }
                                    startActivity(intent);
                                    tv_thongbao.setText("");
                                }else {
                                    tv_thongbao.setText("Tài khoản hoặc mật khẩu nhập sai!");
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