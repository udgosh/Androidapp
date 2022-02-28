package com.dgdgjfm.l.reposproject.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dgdgjfm.l.reposproject.databinding.FragmentOrdersBinding;
import com.google.android.material.navigation.NavigationBarMenuView;

public class OrdersFragment extends Fragment {

    private OrdersViewModel ordersViewModel;
    private FragmentOrdersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ordersViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);

        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        /*binding.currentOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // FragmentTransaction fragmentTransaction=getS


            }
        });*/

        final TextView textView = binding.ordersText;
        ordersViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}