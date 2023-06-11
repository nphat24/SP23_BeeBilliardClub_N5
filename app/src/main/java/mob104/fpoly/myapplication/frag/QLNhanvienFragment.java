package mob104.fpoly.myapplication.frag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.activities.AddNvActivity;
import mob104.fpoly.myapplication.adapter.UserAdapter;
import mob104.fpoly.myapplication.models.NhanvienModel;

public class QLNhanvienFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userReference = database.getReference().child("User");
    ArrayList<NhanvienModel> list = new ArrayList<NhanvienModel>();
    UserAdapter adapter;
    ListView listView;
    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanlinhanvien,container,false);
        listView = view.findViewById(R.id.list_user_nv);
            showListUser();

        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), AddNvActivity.class);
            startActivity(intent);
        });

        return view;
    }


    private void showListUser(){
        list.clear();
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Dữ liệu tồn tại trong bảng User
                    Log.d("User", " Dữ liệu tồn tại trong bảng User");

                    for (DataSnapshot userSnapshot: snapshot.getChildren()){
                        String key = userSnapshot.getKey();
                        String name = userSnapshot.child("name").getValue(String.class);
                        String username = userSnapshot.child("username").getValue(String.class);
                        String password = userSnapshot.child("passwd").getValue(String.class);
                        String group = userSnapshot.child("group").getValue(String.class);
                        Log.d("OBj", ""+key+" "+ username);
                        if (group.equals("user")) {


                            String cccd = userSnapshot.child("cccd").getValue(String.class);
                            String birth = userSnapshot.child("birth_date").getValue(String.class);
                            double luong = userSnapshot.child("luong").getValue(double.class);
                            Log.d("User KEy", ""+key);
                            Log.e("Luong", ""+luong);
                            NhanvienModel nv = new NhanvienModel();
                            nv.setId(key);
                            nv.setName(name);
                            nv.setUsername(username);
                            nv.setPasswd(password);
                            nv.setBirth_date(birth);
                            nv.setLuong(luong);
                            nv.setCccd(cccd);
                            nv.setGroup(group);
                            list.add(nv);


                        }


                        // thuộc tính userName của user trong child là tên cột getva là kiểu dữ liệu

                    }
                    Log.e("List size", ""+list.size());
                    adapter = new UserAdapter(list,getContext());
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    // Không có dữ liệu trong bảng "User"
                    Log.d("User", "Không có dữ liệu trong bảng User");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();


    }
}
