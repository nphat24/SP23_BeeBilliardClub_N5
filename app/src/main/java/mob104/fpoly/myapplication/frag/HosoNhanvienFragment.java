package mob104.fpoly.myapplication.frag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mob104.fpoly.myapplication.MainActivity;
import mob104.fpoly.myapplication.MainManhinhcho;
import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.activities.Activity_doimatkhau_nhanvien;
import mob104.fpoly.myapplication.activities.Activity_thongtinnhanvien;
import mob104.fpoly.myapplication.activities.SignInActivity;

public class HosoNhanvienFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference nhanvienReference = database.getReference().child("User");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoso_nhanvien,container,false);

        TextView tv_thongtinnhanvien = view.findViewById(R.id.tv_thongtinnhanvien);
        TextView tv_trangthai_nhanvien = view.findViewById(R.id.tv_trangthai_nhanvien);
        TextView tv_taikhoan_hsnv = view.findViewById(R.id.tv_taikhoan_hsnv);
        TextView tv_sogiolam_hsnv = view.findViewById(R.id.tv_sogiolam_hsnv);
        Button btn_doimatkhau_hsnv = view.findViewById(R.id.btn_doimatkhau_hsnv);
        Button btn_dangxuat_hsnv = view.findViewById(R.id.btn_dangxuat_hsnv);

        String username = getArguments().getString("username");
        String stringStartTime = getArguments().getString("start");


        nhanvienReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                        String username1 = userSnapshot.child("username").getValue(String.class);

                        if (username.equals(username1)){
                            String name = userSnapshot.child("name").getValue(String.class);
                            tv_thongtinnhanvien.setText("Họ tên: " + name);
                            tv_trangthai_nhanvien.setText("Trạng thái: Đang làm việc");
                            tv_taikhoan_hsnv.setText(username1);
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startTime = dateFormat.parse(stringStartTime);
            Date endDate = new Date(); // Lấy thời điểm hiện tại
            long duration = endDate.getTime() - startTime.getTime();
            long hours = duration / (60 * 60 * 1000);
            long minutes = (duration % (60 * 60 * 1000)) / (60 * 1000);
             long seconds = (duration % (60 * 1000)) / 1000;
            tv_sogiolam_hsnv.setText(hours + " giờ " + minutes +" phút " + seconds + " giây");

        } catch (ParseException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
            // aaa
        }

        tv_thongtinnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_thongtinnhanvien.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        btn_doimatkhau_hsnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_doimatkhau_nhanvien.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        btn_dangxuat_hsnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainManhinhcho.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
