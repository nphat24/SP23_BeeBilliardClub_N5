package mob104.fpoly.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.adapter.QLPhongbanAdapter;
import mob104.fpoly.myapplication.models.BanchoiModel;

public class Activity_themban extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themban_quanly);

        EditText edt_soban = findViewById(R.id.ed_stt_themban_ql);
        EditText edt_gia = findViewById(R.id.ed_gia_gio_themban_ql);
        Button btn_huy = findViewById(R.id.btn_Cancel_thembanql);
        Button btn_them = findViewById(R.id.btn_Them_thembanql);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_soban.getText().length() < 1){
                    Toast.makeText(Activity_themban.this, "Tên không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    DatabaseReference ref = database.getReference();

                    List<BanchoiModel> banchoiModels = new ArrayList<>();

                    ref.child("Table").addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            boolean checkban = false;
                            String strban = "Bàn " + edt_soban.getText();
                            Log.d("bbb", "strban " + strban);
                            for(DataSnapshot contentDataSnapshot: dataSnapshot.getChildren()){
                                BanchoiModel banchoiModel = contentDataSnapshot.getValue(BanchoiModel.class);
                                Log.d("bbb", "banchoiModel" +banchoiModel.getName());
                                if (banchoiModel.getName().equals(strban)) {
                                    checkban = true;
                                    Toast.makeText(Activity_themban.this, "Tên đã tồn tại", Toast.LENGTH_SHORT).show();
                                    break; // Dừng vòng lặp khi tìm thấy tên đã tồn tại
                                }
                            }
                            if (!checkban) {
                                Toast.makeText(Activity_themban.this, "Thêm bàn thành công", Toast.LENGTH_SHORT).show();
                                DatabaseReference ref = database.getReference("Table");
                                if (isNumeric(edt_soban.getText()+"") && isNumeric(edt_gia.getText()+"")){
                                    ref.push().setValue(new BanchoiModel("Bàn " + edt_soban.getText(),"","", edt_gia.getText()+""));
                                }else{
                                    Toast.makeText(Activity_themban.this, "Số bàn và giá tiền phải là số", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    finish();
                }
            }
        });



    }
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}