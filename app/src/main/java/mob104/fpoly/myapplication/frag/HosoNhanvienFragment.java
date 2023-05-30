package mob104.fpoly.myapplication.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.activities.Activity_doimatkhau_nhanvien;
import mob104.fpoly.myapplication.activities.Activity_thongtinnhanvien;

public class HosoNhanvienFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoso_nhanvien,container,false);

        TextView tv_thongtinnhanvien = view.findViewById(R.id.tv_thongtinnhanvien);
        TextView tv_trangthai_nhanvien = view.findViewById(R.id.tv_trangthai_nhanvien);
        EditText ed_taikhoan_hsnv = view.findViewById(R.id.ed_taikhoan_hsnv);
        EditText ed_sogiolam_hsnv = view.findViewById(R.id.ed_sogiolam_hsnv);
        Button btn_doimatkhau_hsnv = view.findViewById(R.id.btn_doimatkhau_hsnv);

        tv_thongtinnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_thongtinnhanvien.class);
                startActivity(intent);
            }
        });
        btn_doimatkhau_hsnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_doimatkhau_nhanvien.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
