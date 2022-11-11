package com.jellysoft.deliveryapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.jellysoft.deliveryapp.ItemOrder;
import com.jellysoft.deliveryapp.R;
import com.jellysoft.deliveryapp.adapters.PendingOrderAdapter;
import com.jellysoft.deliveryapp.databinding.FragmentPendingBinding;

import java.util.ArrayList;

public class PendingFragment extends Fragment {
    FragmentPendingBinding binding;
    PendingOrderAdapter pendingOrderAdapter;
    String username;

    public PendingFragment(String username){
        this.username = username;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pending, container, false);
        ArrayList<ItemOrder> orders = new ArrayList<>();
        ItemOrder order = new ItemOrder();

        for(int i = 0; i < 5; i++){
            order.setDate("11-11-2022");
            order.setFirstname("John");
            order.setArea("Spic");
            order.setCity("Tuty");
            order.setLandmark("church");
            order.setPincode(1234);
            order.setOrderId("Order-" + i);

            orders.add(order);
        }
        pendingOrderAdapter = new PendingOrderAdapter(orders, username);
        binding.rvOrders.setAdapter(pendingOrderAdapter);
        binding.tvname.setText("Hello, " + username);

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}