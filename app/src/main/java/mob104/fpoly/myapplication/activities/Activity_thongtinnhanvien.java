package mob104.fpoly.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import mob104.fpoly.myapplication.MainActivity;
import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.frag.HosoNhanvienFragment;

public class Activity_thongtinnhanvien extends AppCompatActivity {
    TextView tv_hoten_thongtinnv, tv_taikhoan_thongtinnv, tv_ngaysinh_thongtincn_nv, tv_cccd_thongtincn_nv;
    Toolbar tbar_back_ttcn_nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtincanhan_nhanvien);

        tv_hoten_thongtinnv = findViewById(R.id.tv_hoten_thongtinnv);
        tv_taikhoan_thongtinnv = findViewById(R.id.tv_taikhoan_thongtinnv);
        tv_cccd_thongtincn_nv = findViewById(R.id.tv_cccd_thongtincn_nv);
        tv_ngaysinh_thongtincn_nv = findViewById(R.id.tv_ngaysinh_thongtincn_nv);
        tbar_back_ttcn_nv = findViewById(R.id.tbar_back_ttcn_nv);

        tbar_back_ttcn_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
