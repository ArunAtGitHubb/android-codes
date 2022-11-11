package com.retrytech.veginew.activites;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.retrytech.veginew.R;
import com.retrytech.veginew.adapters.FaqAdapter;
import com.retrytech.veginew.databinding.ActivityFaqBinding;
import com.retrytech.veginew.models.FaqRoot;
import com.retrytech.veginew.retrofit.Const;
import com.retrytech.veginew.retrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FaqActivity extends AppCompatActivity {
    ActivityFaqBinding binding;
    CompositeDisposable disposable = new CompositeDisposable();
    private List<FaqRoot.DataItem> faqs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_faq);

        getFaqs();

    }

    private void getFaqs() {
        disposable.add(RetrofitBuilder.create().getFaqs(Const.DEVKEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> {
                    binding.shimmer.setVisibility(View.VISIBLE);
                })
                .doOnDispose(() -> {
                    binding.shimmer.setVisibility(View.VISIBLE);
                })
                .subscribe((faqRoot, throwable) -> {
                    binding.shimmer.setVisibility(View.GONE);
                    //     Log.d("TAG", "getData: err "+throwable.getMessage());
                    if (faqRoot != null && faqRoot.isStatus() && !faqRoot.getData().isEmpty()) {
                        faqs = faqRoot.getData();

                        initView();
                    }
                }));

    }

    private void initView() {
        binding.rvfaqs.setAdapter(new FaqAdapter(faqs));
    }

    public void onClickBack(View view) {
        onBackPressed();
    }
}