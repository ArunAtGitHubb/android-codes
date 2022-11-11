package com.retrytech.veginew.activites;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.retrytech.veginew.R;
import com.retrytech.veginew.adapters.NotificationPagerAdapter;
import com.retrytech.veginew.databinding.ActivityNotificationBinding;

import io.reactivex.disposables.CompositeDisposable;

public class NotificationActivity extends BaseActivity {
    ActivityNotificationBinding binding;
    CompositeDisposable disposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification);

        initView();
    }


    public void onClickBack(View view) {
        onBackPressed();
    }

    private void initView() {
        binding.tablayout.removeAllTabs();
        binding.tablayout.addTab(binding.tablayout.newTab().setText("Notifications"));
        binding.tablayout.addTab(binding.tablayout.newTab().setText("Orders Updates"));
        binding.tablayout.setupWithViewPager(binding.viewpager);
        NotificationPagerAdapter tickitPagerAdapter = new NotificationPagerAdapter(getSupportFragmentManager());
        binding.viewpager.setAdapter(tickitPagerAdapter);
        binding.viewpager.setVisibility(View.VISIBLE);

    }

}