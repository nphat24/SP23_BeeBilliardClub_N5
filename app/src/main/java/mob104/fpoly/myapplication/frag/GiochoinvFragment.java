package mob104.fpoly.myapplication.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.adapter.GiochoiAdapter;
import mob104.fpoly.myapplication.models.BanchoiModel;

public class GiochoinvFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private List<BanchoiModel> banchoiModels = new ArrayList<>();
    private GiochoiAdapter giochoiAdapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlhoadon,container,false);
        loadBanchoi(view);
        return view;
    }
    private void loadBanchoi(View view) {
        DatabaseReference ref = database.getReference();
        listView = view.findViewById(R.id.lv_banchoi);

        giochoiAdapter = new GiochoiAdapter(getContext(), banchoiModels);
        listView.setAdapter(giochoiAdapter);


        ref.child("Table").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                banchoiModels.clear();
                for (DataSnapshot contentDataSnapshot : dataSnapshot.getChildren()) {
                    BanchoiModel banchoiModel = contentDataSnapshot.getValue(BanchoiModel.class);
                    banchoiModels.add(banchoiModel);
                }
                giochoiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}