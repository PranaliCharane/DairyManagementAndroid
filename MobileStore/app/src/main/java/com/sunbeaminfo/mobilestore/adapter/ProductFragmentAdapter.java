package com.sunbeaminfo.mobilestore.adapter;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sunbeaminfo.mobilestore.fragments.ProductListFragment;
import com.sunbeaminfo.mobilestore.fragments.OrdersFragment;
import com.sunbeaminfo.mobilestore.fragments.ProfileFragment;

public class ProductFragmentAdapter extends FragmentStateAdapter {
    public ProductFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ProductListFragment();
            case 1:
                return new OrdersFragment();
            case 2:
                return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
