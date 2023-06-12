package mob104.fpoly.myapplication.frag;

import android.content.Intent;
import android.os.Bundle;
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
import mob104.fpoly.myapplication.activities.Activity_thongke;
import mob104.fpoly.myapplication.activities.AddNvActivity;
import mob104.fpoly.myapplication.adapter.BanchoiAdapter;
import mob104.fpoly.myapplication.adapter.GiochoiAdapter;
import mob104.fpoly.myapplication.adapter.HoadonAdapter;
import mob104.fpoly.myapplication.adapter.QLPhongbanAdapter;
import mob104.fpoly.myapplication.models.BanchoiModel;
import mob104.fpoly.myapplication.models.HoadonModel;

public class QLHoadonFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private List<HoadonModel> hoadonModels = new ArrayList<>();
    private ListView listView;
    private HoadonAdapter hoadonAdapter;

    FloatingActionButton floatingActionButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlhoadon,container,false);
        loadHoadon(view);
        return view;
    }
    private void loadHoadon(View view){
        DatabaseReference ref = database.getReference();
        listView = view.findViewById(R.id.lv_qlhoadon);
        hoadonAdapter = new HoadonAdapter(getContext(),hoadonModels);
        listView.setAdapter(hoadonAdapter);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        ref.child("Bill").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hoadonModels.clear();

                for(DataSnapshot contentDataSnapshot: dataSnapshot.getChildren()){
                    HoadonModel hoadonModel = contentDataSnapshot.getValue(HoadonModel.class);
                    hoadonModels.add(hoadonModel);

                }
                hoadonAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        floatingActionButton.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), Activity_thongke.class);
            startActivity(intent);
        });


    }
}
