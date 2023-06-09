package mob104.fpoly.myapplication.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.activities.Activity_themban;
import mob104.fpoly.myapplication.adapter.BanchoiAdapter;
import mob104.fpoly.myapplication.adapter.QLPhongbanAdapter;
import mob104.fpoly.myapplication.models.BanchoiModel;

public class Frag_Quanlyphongban extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private List<BanchoiModel> banchoiModels = new ArrayList<>();
    private ListView listView;
    private QLPhongbanAdapter qlPhongbanAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlphongban,container,false);
        FirebaseApp.initializeApp(getContext());
        FloatingActionButton fab_button = view.findViewById(R.id.fab_add);

        fab_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), Activity_themban.class);
                startActivity(intent);

            }
        });


        loadBanchoi(view);
        return view;
    }

    private void loadBanchoi(View view){
        DatabaseReference ref = database.getReference();
        listView = view.findViewById(R.id.lv_quanLyPhongBan);
        listView.setDividerHeight(50);
        qlPhongbanAdapter = new QLPhongbanAdapter(getContext(),banchoiModels);
        listView.setAdapter(qlPhongbanAdapter);


        ref.child("Table").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                banchoiModels.clear();

                for(DataSnapshot contentDataSnapshot: dataSnapshot.getChildren()){
                    BanchoiModel banchoiModel = contentDataSnapshot.getValue(BanchoiModel.class);
                    banchoiModels.add(banchoiModel);

                }
                qlPhongbanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}