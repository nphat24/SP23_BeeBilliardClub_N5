package mob104.fpoly.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = database.getReference("Table");
                if (isNumeric(edt_soban.getText()+"") && isNumeric(edt_gia.getText()+"")){
                    ref.push().setValue(new BanchoiModel("Bàn " + edt_soban.getText(),"","", edt_gia.getText()+""));
                }else{
                    Toast.makeText(Activity_themban.this, "Số bàn và giá tiền phải là số", Toast.LENGTH_SHORT).show();
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