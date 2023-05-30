package mob104.fpoly.myapplication.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.activities.Activity_doimatkhau_quanly;

public class HosoQuanliFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoso_quanly,container,false);

        Button btn_doimatkhau_hsql = view.findViewById(R.id.btn_doimatkhau_hsql);
        TextView tv_hoten_hsql = view.findViewById(R.id.tv_hoten_hsql);
        TextView tv_trangthai_hsql = view.findViewById(R.id.tv_trangthai_hsql);
        TextView tv_taikhoan_hsql = view.findViewById(R.id.tv_taikhoan_hsql);
        TextView tv_luong_hsql = view.findViewById(R.id.tv_luong_hsql);
        TextView tv_sogiolam_hsql = view.findViewById(R.id.tv_sogiolam_hsql);

        btn_doimatkhau_hsql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_doimatkhau_quanly.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
