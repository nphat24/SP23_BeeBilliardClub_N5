package mob104.fpoly.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mob104.fpoly.myapplication.adapter.QLPhongbanAdapter;
import mob104.fpoly.myapplication.models.BanchoiModel;

public class SearchActivity extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    List<BanchoiModel> banchoiModels;
    QLPhongbanAdapter qlPhongbanAdapter;
    TextView tv_noFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.search_view);
        listView = findViewById(R.id.rcv_search);
        tv_noFind = findViewById(R.id.tv_noFind);
        tv_noFind.setVisibility(View.INVISIBLE);
        banchoiModels=new ArrayList<>();
        qlPhongbanAdapter = new QLPhongbanAdapter(this,banchoiModels);
        listView.setAdapter(qlPhongbanAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                banchoiModels.clear();
                getListDataSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                listReview.clear();
//                listFearture.clear();
//                getListDataSearch(newText);
                return false;
            }
        });
    }

    public void getListDataSearch(String text){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Table");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    BanchoiModel dfearturedModel = dataSnapshot.getValue(BanchoiModel.class);
                    if(dfearturedModel.getName().toLowerCase().contains(text.toLowerCase())){
                        banchoiModels.add(dfearturedModel);
                    }
                }
                if(validate() > 0){
                    tv_noFind.setVisibility(View.INVISIBLE);
                }else{
                    tv_noFind.setVisibility(View.VISIBLE);
                }

                qlPhongbanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public int validate(){
        int check = -1;
        if(banchoiModels.size() > 0){
            check = 1;
        }
        return check;
    }
}