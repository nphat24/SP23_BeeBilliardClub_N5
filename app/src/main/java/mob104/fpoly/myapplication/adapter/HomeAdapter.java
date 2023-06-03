package mob104.fpoly.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import mob104.fpoly.myapplication.frag.Home_Controng_Fragment;
import mob104.fpoly.myapplication.frag.Home_Sudung_Fragment;
import mob104.fpoly.myapplication.frag.Home_Tatca_Fragment;

public class HomeAdapter extends FragmentStateAdapter {
    public HomeAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Home_Tatca_Fragment();
            case 1:
                return new Home_Sudung_Fragment();
            case 2:
                return new Home_Controng_Fragment();
            default:
                return new Home_Tatca_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
