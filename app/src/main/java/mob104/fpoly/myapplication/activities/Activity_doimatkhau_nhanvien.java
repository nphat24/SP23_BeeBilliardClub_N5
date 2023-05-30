package mob104.fpoly.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.frag.HosoNhanvienFragment;

public class Activity_doimatkhau_nhanvien extends AppCompatActivity {
    Toolbar tbar_back_dmk_nv;
    TextView tv_hoten_doimk_nv, tv_taikhoan_doimk_nv;
    TextInputLayout ed_password_doimk_nv,ed_passwordmoi_doimk_nv,ed_xacnhanmk_doimk_nv;
    Button btn_Cancel_doimk_hsnv, btn_hoantat_doimk_hsnv;
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

        tbar_back_dmk_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

            }
        });
    }
}

