package mob104.fpoly.myapplication.frag;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.adapter.BanchoiAdapter;
import mob104.fpoly.myapplication.models.BanchoiModel;

public class Home_Tatca_Fragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://bee-billiard-club-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private List<BanchoiModel> banchoiModels = new ArrayList<>();
    private GridView gridView;
    private BanchoiAdapter banchoiAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_tatca,container,false);
        FirebaseApp.initializeApp(getContext());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadBanchoi(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        banchoiAdapter.notifyDataSetChanged();
    }



    private void loadBanchoi(View view){
        DatabaseReference ref = database.getReference();
        gridView = view.findViewById(R.id.gv_tatca);

        banchoiAdapter = new BanchoiAdapter(getContext(),banchoiModels);
        gridView.setAdapter(banchoiAdapter);


        ref.child("Table").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                banchoiModels.clear();

                for(DataSnapshot contentDataSnapshot: dataSnapshot.getChildren()){
                    BanchoiModel banchoiModel = contentDataSnapshot.getValue(BanchoiModel.class);
                    banchoiModels.add(banchoiModel);

                }
               banchoiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
