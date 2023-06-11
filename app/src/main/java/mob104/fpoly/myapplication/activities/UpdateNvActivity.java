package mob104.fpoly.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.models.NhanvienModel;

public class UpdateNvActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("User");

    EditText edName;
    EditText edUserName;
    EditText edBirthDate;
    EditText edCccd;
    EditText edLuong;
    NhanvienModel object = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nv);
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        Log.i("Key update", key);

        edName = findViewById(R.id.ed_full_name);
        edUserName = findViewById(R.id.ed_account);
        edBirthDate = findViewById(R.id.ed_brid_day);
        edCccd = findViewById(R.id.ed_cccd);
        edLuong = findViewById(R.id.ed_luong);

        MaterialToolbar toolbar = findViewById(R.id.materialToolbar);
        DatabaseReference selectedUserReference = userReference.child(key);
        selectedUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Lấy đối tượng từ DataSnapshot
                    object = snapshot.getValue(NhanvienModel.class);
                    Log.d("OBJ"," đã nhận đc dữ liệu");
                    // Xử lý đối tượng tại đây
                    edName.setText(object.getName());
                    edUserName.setText(object.getUsername());
                    edBirthDate.setText(object.getBirth_date());
                    edCccd.setText(object.getCccd());
                    edLuong.setText(object.getLuong()+"");


                } else {
                    // Không tìm thấy đối tượng với khóa tương ứng
                    Log.e("OBJ"," Không nhận đc dữ liệu");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu có
            }
        });
        findViewById(R.id.btn_save).setOnClickListener(view -> {
            String strName = edName.getText().toString().trim();
            String strUserName = edUserName.getText().toString().trim();
            String strBirthDate = edBirthDate.getText().toString().trim();
            String strCccd = edCccd.getText().toString().trim();
            String strLuong = edLuong.getText().toString().trim();

            object.setName(strName);
            object.setUsername(strUserName);
            object.setBirth_date(strBirthDate);
            object.setCccd(strCccd);
            object.setLuong(Double.parseDouble(strLuong));
            DatabaseReference updatedUserReference = userReference.child(key);
            updatedUserReference.setValue(object)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Cập nhật thành công
                            Log.d("Update","update thành công");
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Xử lý khi cập nhật thất bại
                            Log.e("Update","update không thành công");
                        }
                    });



        });




        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}