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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mob104.fpoly.myapplication.MainManhinhcho;
import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.activities.Activity_doimatkhau_quanly;

public class HosoQuanliFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference quanlyReference = database.getReference().child("User");
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
        Button btn_dangxuat_hsql = view.findViewById(R.id.btn_dangxuat_hsql);

        String username = getArguments().getString("admin");

        quanlyReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                        String username1 = userSnapshot.child("username").getValue(String.class);

                        if (username.equals(username1)){
                            String name = userSnapshot.child("name").getValue(String.class);
                            String luong = userSnapshot.child("luong").getValue(String.class);
                            tv_hoten_hsql.setText("Họ tên: " + name);
                            tv_trangthai_hsql.setText("Trạng thái: Đang làm việc");
                            tv_taikhoan_hsql.setText(username1);
                            tv_luong_hsql.setText(luong);
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });

        btn_doimatkhau_hsql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_doimatkhau_quanly.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        btn_dangxuat_hsql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainManhinhcho.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
