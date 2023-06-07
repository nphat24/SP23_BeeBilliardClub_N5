package mob104.fpoly.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import mob104.fpoly.myapplication.frag.Frag_Quanlyphongban;
import mob104.fpoly.myapplication.frag.GiochoinvFragment;
import mob104.fpoly.myapplication.frag.HomeFragment;
import mob104.fpoly.myapplication.frag.HosoNhanvienFragment;
import mob104.fpoly.myapplication.frag.HosoQuanliFragment;
import mob104.fpoly.myapplication.frag.LienheFragment;
import mob104.fpoly.myapplication.frag.QLHoadonFragment;
import mob104.fpoly.myapplication.frag.QLNhanvienFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    QLHoadonFragment qlHoadonFragment = new QLHoadonFragment();
    LienheFragment lienheFragment = new LienheFragment();
    HosoNhanvienFragment hosoNhanvienFragment = new HosoNhanvienFragment();
    Frag_Quanlyphongban fragquanlyphongban = new Frag_Quanlyphongban();
    HosoQuanliFragment hosoQuanliFragment = new HosoQuanliFragment();
    QLNhanvienFragment qlNhanvienFragment = new QLNhanvienFragment();
    GiochoinvFragment giochoinvFragment = new GiochoinvFragment();
    boolean isAdmin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.nav_view);

        // đoạn check admin
        Intent intent = getIntent();
        String quyen = intent.getStringExtra("quyen");
        if (quyen.equals("admin")){
            bottomNavigationView.getMenu().clear();
            admin();
        }else {
            bottomNavigationView.getMenu().clear();
            user();
        }

    }
    private void admin(){
        bottomNavigationView.inflateMenu(R.menu.bottom_nav_ad);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragquanlyphongban).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_Trangchu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragquanlyphongban).commit();
                        return true;
                    case R.id.nav_Qlhoadon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,qlHoadonFragment).commit();
                        return true;
                    case R.id.nav_QLNV:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,qlNhanvienFragment).commit();
                        return true;
                    case R.id.nav_Hoso:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, hosoQuanliFragment).commit();
                        return true;

                }
                return false;
            }
        });
    }
    private void user(){
            bottomNavigationView.inflateMenu(R.menu.bottom_nav_nv);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,homeFragment).commit();

            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_Trangchu:
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,homeFragment).commit();
                            return true;
                        case R.id.nav_Giochoi:
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,giochoinvFragment).commit();
                            return true;
                        case R.id.nav_Lienhe:
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,lienheFragment).commit();
                            return true;
                        case R.id.nav_Hoso:
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, hosoNhanvienFragment).commit();
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