package mob104.fpoly.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import mob104.fpoly.myapplication.frag.HomeFragment;
import mob104.fpoly.myapplication.frag.HosoFragment;
import mob104.fpoly.myapplication.frag.LienheFragment;
import mob104.fpoly.myapplication.frag.QLHoadonFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    QLHoadonFragment qlHoadonFragment = new QLHoadonFragment();
    LienheFragment lienheFragment = new LienheFragment();
    HosoFragment hosoFragment = new HosoFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_Trangchu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,homeFragment).commit();
                        return true;
                    case R.id.nav_Qlhoadon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,qlHoadonFragment).commit();
                        return true;
                    case R.id.nav_Lienhe:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,lienheFragment).commit();
                        return true;
                    case R.id.nav_Hoso:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,hosoFragment).commit();
                        return true;

                }
                return false;
            }
        });

    }

    // start frg
    private void replaceFragment(Fragment frg){
        FragmentManager frm = getFragmentManager();
        frm.beginTransaction().replace(R.id.content_frame,frg).commit();
    }
}