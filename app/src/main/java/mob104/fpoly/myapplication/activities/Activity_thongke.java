package mob104.fpoly.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Locale;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.adapter.UserAdapter;
import mob104.fpoly.myapplication.models.NhanvienModel;

public class Activity_thongke extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference tableReference = database.getReference().child("Table");
    DatabaseReference billReference = database.getReference().child("Bill");
    int countBill = 0;
    int countTable = 0;
    TextView tvCountBill;
    TextView tvCountTable;
    TextView tvCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        tvCountBill = findViewById(R.id.tv_count_bill);
        tvCountTable = findViewById(R.id.tv_count_table);
        tvCurrent = findViewById(R.id.tv_current);
        tvCurrent.setText(currentDate);



        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        billReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Dữ liệu tồn tại trong bảng User
                    Log.d("User", " Dữ liệu tồn tại trong bảng Bill");
                    for (DataSnapshot billSnapshot: snapshot.getChildren()){
                        String day = billSnapshot.child("day").getValue(String.class);
                        if (day.equals(currentDate)){
                            countBill++;
                        }


                    }
                    tvCountBill.setText(countBill+" hóa đơn");








                } else {
                    // Không có dữ liệu trong bảng "User"
                    Log.d("User", "Không có dữ liệu trong bảng Bill");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });



        tableReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Dữ liệu tồn tại trong bảng User
                    Log.e("Table", " Dữ liệu tồn tại trong bảng Table");
                    for (DataSnapshot tableSnapshot: snapshot.getChildren()){
                        String start = tableSnapshot.child("start").getValue(String.class);
                        if (!start.equals("")){
                            countTable++;
                        }

                    }
                    tvCountTable.setText(countTable+"/10");



                } else {
                    // Không có dữ liệu trong bảng "User"
                    Log.e("Table", "Không có dữ liệu trong bảng table");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}