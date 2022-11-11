//package com.jellysoft.deliveryapp.fragments;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import androidx.databinding.DataBindingUtil;
//
//import com.bumptech.glide.Glide;
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//import com.retrytech.deliveryboy.R;
//import com.retrytech.deliveryboy.SessionManager;
//import com.retrytech.deliveryboy.VegiUtils;
//import com.retrytech.deliveryboy.adapters.OrderItemAdapter;
//import com.retrytech.deliveryboy.databinding.FragmentOrderDetailsBinding;
//import com.retrytech.deliveryboy.models.OrderDetailRoot;
//import com.retrytech.deliveryboy.models.RestResponse;
//import com.retrytech.deliveryboy.models.User;
//import com.retrytech.deliveryboy.popups.CompleteOrderPopup;
//import com.retrytech.deliveryboy.popups.HoldPopup;
//import com.retrytech.deliveryboy.retrofit.Const;
//import com.retrytech.deliveryboy.retrofit.RetrofitBuilder;
//
//import java.text.DecimalFormat;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//
//public class OrderDetailsFragment extends BottomSheetDialogFragment {
//
//    private static final String TAG = "orderdetailfrag";
//    SessionManager sessionManager;
//    FragmentOrderDetailsBinding binding;
//    private DecimalFormat df;
//    private OrderDetailRoot.Data data;
//    private User user;
//    private String from;
//    private OrderDetailRoot.Orderaddress address;
//
//    public OrderDetailsFragment(OrderDetailRoot.Data data, User user, String from) {
//        // Required empty public constructor
//        this.data = data;
//        this.user = user;
//        this.from = from;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for getActivity() fragment
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_details, container, false);
//        sessionManager = new SessionManager(getActivity());
//        df = new DecimalFormat("###.##");
//
//        if (from != null && !from.equals("") && from.equals("complete")) {
//            binding.cardview.setVisibility(View.GONE);
//        }
//        setData();
//
//        manageStatus();
//
//        return binding.getRoot();
//    }
//
//    private void setData() {
//
//        // set User data
//        if (user != null) {
//
//            Glide.with(binding.getRoot().getContext())
//                    .load(Const.BASE_IMG_URL + user.getImage())
//                    // .placeholder(R.drawable.delivery_placeholder)
//                    //.error(R.drawable.delivery_placeholder)
//                    .circleCrop()
//                    .into(binding.imgUser);
//        }
//
//
//        binding.tvorderid.setText(data.getOrderId());
//
//        if (data.getOrderaddress() != null) {
//
//
//            try {
//                Log.d(TAG, "onResponse: lat  " + data.getOrderaddress().getLatitude());
//            } catch (Exception e) {
//                Log.d(TAG, "setData: location lat not");
//                e.printStackTrace();
//            }
//            ;
//            address = data.getOrderaddress();
//            binding.tvName.setText(address.getFirstname());
//
//            binding.tvaddress1.setText(address.getArea().concat(", ").concat(address.getCity()));
//            binding.tvaddress2.setText(address.getAddressLine()
//                    + " \n" + address.getArea() + " " + address.getLandmark() + " " + address.getPincode());
//            binding.tvMobile.setText(address.getNumber());
//            binding.tvdeliverytype.setText(VegiUtils.getDeliveryType(data.getPaymentType()));
//
//        }
//        if (data.getOrderproducts() != null) {
//            OrderItemAdapter orderItemAdapter = new OrderItemAdapter(data.getOrderproducts());
//            binding.rvorderitems.setAdapter(orderItemAdapter);
//        }
//        if (data.getShippingCharge() != null) {
//            binding.tvshippingcharge.setText(VegiUtils.getCurrency().concat(String.valueOf(df.format(Float.parseFloat(data.getShippingCharge())))));
//        }
//
//
//        initListnear();
//    }
//
//    private void initListnear() {
//        binding.tvStart.setOnClickListener(v -> startDelivery());
//        binding.tvComplete.setOnClickListener(v -> {
//            CompleteOrderPopup completeOrderPopup = new CompleteOrderPopup(getActivity(), data, new CompleteOrderPopup.OnCompletePopupClickListnear() {
//                @Override
//                public void onComplete() {
//                    completeOrder();
//                }
//            });
//        });
//        binding.tvHold.setOnClickListener(v -> {
//            HoldPopup holdPopup = new HoldPopup(getActivity(), new HoldPopup.OnHoldPopupClickListner() {
//                @Override
//                public void onClickHold(String i) {
//                    holdOrder(i);
//                }
//            });
//        });
//
//    }
//
//    private void completeOrder() {
//        binding.pd.setVisibility(View.VISIBLE);
//        Call<RestResponse> call = RetrofitBuilder.create().completeDelivery(Const.DEV_KEY, sessionManager.getUser().getData().getToken(), data.getOrderId(), data.getTotalAmount());
//        call.enqueue(new Callback<RestResponse>() {
//            @Override
//            public void onResponse(Call<RestResponse> call, Response<RestResponse> response) {
//                if (response.code() == 200 && response.body().isStatus()) {
//                    Log.d(TAG, "onResponse: colplere");
//                    Toast.makeText(getActivity(), "Completed", Toast.LENGTH_SHORT).show();
//
//                    getActivity().finish();
//                }
//                binding.pd.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onFailure(Call<RestResponse> call, Throwable t) {
////ll
//            }
//        });
//
//    }
//
//    private void holdOrder(String i) {
//        binding.pd.setVisibility(View.VISIBLE);
//        Call<RestResponse> call = RetrofitBuilder.create().onHoldDelivery(Const.DEV_KEY, sessionManager.getUser().getData().getToken(), data.getOrderId(), String.valueOf(i));
//        call.enqueue(new Callback<RestResponse>() {
//            @Override
//            public void onResponse(Call<RestResponse> call, Response<RestResponse> response) {
//                if (response.code() == 200 && response.body().isStatus()) {
//                    Log.d(TAG, "onResponse: hold");
//                    Toast.makeText(getActivity(), "order is on hold", Toast.LENGTH_SHORT).show();
//                    getActivity().finish();
//                }
//                binding.pd.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onFailure(Call<RestResponse> call, Throwable t) {
////ll
//            }
//        });
//
//    }
//
//    private void startDelivery() {
//        binding.pd.setVisibility(View.VISIBLE);
//        Call<RestResponse> call = RetrofitBuilder.create().startDelivery(Const.DEV_KEY, sessionManager.getUser().getData().getToken(), data.getOrderId());
//        call.enqueue(new Callback<RestResponse>() {
//            @Override
//            public void onResponse(Call<RestResponse> call, Response<RestResponse> response) {
//                if (response.code() == 200 && response.body().isStatus()) {
//                    Toast.makeText(getActivity(), "Delivery Started", Toast.LENGTH_SHORT).show();
//                    binding.lytCompleteHold.setVisibility(View.VISIBLE);
//                    binding.lytStart.setVisibility(View.GONE);
//                }
//                binding.pd.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onFailure(Call<RestResponse> call, Throwable t) {
////ll
//            }
//        });
//    }
//
//    private void manageStatus() {
//        if (data.getStatus() == 1) {
//
//        } else if (data.getStatus() == 2) {
//            binding.lytStart.setVisibility(View.VISIBLE);
//        } else if (data.getStatus() == 3) {
//
//        } else if (data.getStatus() == 4) {
//
//        } else if (data.getStatus() == 5) {
//
//        }
//        if (data.getStartDelivery() == 6) {
//            binding.lytCompleteHold.setVisibility(View.VISIBLE);
//            binding.lytStart.setVisibility(View.GONE);
//        } else {
//            //  binding.lytStart.setVisibility(View.VISIBLE);
//        }
//    }
//
//}