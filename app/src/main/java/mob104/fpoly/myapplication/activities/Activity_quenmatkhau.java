package mob104.fpoly.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;

import mob104.fpoly.myapplication.MainActivity;
import mob104.fpoly.myapplication.R;

public class Activity_quenmatkhau extends AppCompatActivity {
    EditText ed_cccd_quenmk, ed_xacnhanmk_quenmk , ed_user_name_quenmk;
    Button btn_thaydmk_quenmk;
    TextInputLayout ed_password_quenmk;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://bee-billiard-club-default-rtdb.asia-southeast1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_quenmatkhau);

        ed_cccd_quenmk = findViewById(R.id.ed_cccd_quenmk);
        ed_password_quenmk = findViewById(R.id.ed_password_quenmk);
        ed_user_name_quenmk = findViewById(R.id.ed_user_name_quenmk);
        ed_xacnhanmk_quenmk = findViewById(R.id.ed_xacnhanmk_quenmk);
        btn_thaydmk_quenmk = findViewById(R.id.btn_thaydmk_quenmk);

        btn_thaydmk_quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_quenmatkhau.this, SignInActivity.class) ;
                startActivity(intent);
            }
        });
    }
}