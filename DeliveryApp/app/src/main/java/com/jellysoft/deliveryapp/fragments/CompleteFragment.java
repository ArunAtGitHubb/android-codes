//package com.jellysoft.deliveryapp.fragments;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.databinding.DataBindingUtil;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.retrytech.deliveryboy.R;
//import com.retrytech.deliveryboy.SessionManager;
//import com.retrytech.deliveryboy.adapters.CompleteOrderAdapter;
//import com.retrytech.deliveryboy.databinding.FragmentCompleteBinding;
//import com.retrytech.deliveryboy.models.CompleteOrderRoot;
//import com.retrytech.deliveryboy.retrofit.Const;
//import com.retrytech.deliveryboy.retrofit.RetrofitBuilder;
//import com.retrytech.deliveryboy.retrofit.RetrofitService;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//
//public class CompleteFragment extends Fragment {
//
//    FragmentCompleteBinding binding;
//    SessionManager sessionManager;
//    CompleteOrderAdapter completeOrderAdapter = new CompleteOrderAdapter();
//    private String token;
//    private boolean isLoding = false;
//    private int start = 0;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_complete, container, false);
//
//
//        return binding.getRoot();
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        sessionManager = new SessionManager(getActivity());
//        if (sessionManager.getBooleanValue(Const.IS_LOGIN)) {
//            token = sessionManager.getUser().getData().getToken();
//            binding.shimmer.startShimmer();
//            binding.rvOrders.setAdapter(completeOrderAdapter);
//
//            getData();
//            initListnear();
//        }
//    }
//
//    private void initListnear() {
//        binding.rvOrders.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (!binding.rvOrders.canScrollVertically(1)) {
//                    LinearLayoutManager manager = (LinearLayoutManager) binding.rvOrders.getLayoutManager();
//
//                    int visibleItemcount = manager.getChildCount();
//                    int totalitem = manager.getItemCount();
//                    int firstvisibleitempos = manager.findFirstCompletelyVisibleItemPosition();
//
//                    if (!isLoding && (visibleItemcount + firstvisibleitempos >= totalitem) && firstvisibleitempos >= 0) {
//
//                        start = start + Const.LIMIT;
//                        binding.pd2.setVisibility(View.VISIBLE);
//                        getData();
//                    }
//                }
//            }
//        });
//
//
//    }
//
//    private void getData() {
//        isLoding = true;
//        binding.lyt404.setVisibility(View.GONE);
//        RetrofitService service = RetrofitBuilder.create();
//        Call<CompleteOrderRoot> call = service.getCompleteOrders(Const.DEV_KEY, token, start, Const.LIMIT);
//        call.enqueue(new Callback<CompleteOrderRoot>() {
//            @Override
//            public void onResponse(Call<CompleteOrderRoot> call, Response<CompleteOrderRoot> response) {
//                if (response.code() == 200) {
//                    isLoding = false;
//                    if (response.body() != null && response.body().isStatus() && response.body().getData() != null && !response.body().getData().isEmpty()) {
//                        List<CompleteOrderRoot.DataItem> data = response.body().getData();
//                        completeOrderAdapter.addData(data);
//                    } else {
//                        if (start == 0) {
//                            binding.lyt404.setVisibility(View.VISIBLE);
//                            binding.shimmer.setVisibility(View.GONE);
//                        }
//                    }
//                }
//                binding.pd.setVisibility(View.GONE);
//                binding.pd2.setVisibility(View.GONE);
//                binding.shimmer.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onFailure(Call<CompleteOrderRoot> call, Throwable t) {
//                binding.lyt404.setVisibility(View.VISIBLE);
//            }
//        });
//    }
//}