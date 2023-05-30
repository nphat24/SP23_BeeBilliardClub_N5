package mob104.fpoly.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import mob104.fpoly.myapplication.MainActivity;
import mob104.fpoly.myapplication.R;

public class SignInActivity extends AppCompatActivity {
    TextInputLayout ed_DN_password;
    EditText  ed_DN_username;
    Button btn_sign_in;
    TextView tv_quenmk;
    CheckBox cb_luumk;
    String strUser, strPass;
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

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
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
        strUser = ed_DN_username.getText().toString();
        strPass = ed_DN_password.getEditText().getText().toString();

        if(strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            rememberUser(strUser, strPass, cb_luumk.isChecked());
            Intent intent1 = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(intent1);
        }
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