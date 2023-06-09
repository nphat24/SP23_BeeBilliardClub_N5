package mob104.fpoly.myapplication.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import mob104.fpoly.myapplication.R;
import mob104.fpoly.myapplication.adapter.HomeAdapter;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        // chỗ này của Lão Tôn
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);

        HomeAdapter adapter = new HomeAdapter(getActivity());
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
               if(position == 0){
                   tab.setText("Tất cả");
               }
               if(position == 1){
                   tab.setText("Sử dụng");
               }
               if(position == 2){
                   tab.setText("Còn trống");
               }
            }
        }).attach();

        return view;
    }

}
