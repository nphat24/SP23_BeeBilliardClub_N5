package mob104.fpoly.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.frag.HosoNhanvienFragment;
import mob104.fpoly.myapplication.frag.HosoQuanliFragment;

public class Activity_doimatkhau_quanly extends AppCompatActivity {
    Toolbar tbar_back_dmk_ql;
    TextView tv_hoten_doimk_hsql , tv_taikhoan_doimk_hsql;
    EditText ed_taikhoan_doimk_hsql , ed_sdt_doimk_hsql;
    TextInputLayout ed_passwokmoi_doimk_hsql , ed_xacnhanmk_doimk_hsql;
    Button btn_Cancel_dmk_hsql , btn_hoantat_doimk_hsql;
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

        btn_hoantat_doimk_hsql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_Cancel_dmk_hsql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_passwokmoi_doimk_hsql.getEditText().setText("");
                ed_xacnhanmk_doimk_hsql.getEditText().setText("");
            }
        });
        tbar_back_dmk_ql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
