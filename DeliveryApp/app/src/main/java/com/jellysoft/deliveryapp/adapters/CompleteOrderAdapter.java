//package com.jellysoft.deliveryapp.adapters;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.ColorStateList;
//import android.os.Build;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.google.gson.Gson;
//import com.retrytech.deliveryboy.R;
//import com.retrytech.deliveryboy.activites.OrderDetailActivity;
//import com.retrytech.deliveryboy.databinding.ItemOrderlistBinding;
//import com.retrytech.deliveryboy.models.CompleteOrderRoot;
//import com.retrytech.deliveryboy.retrofit.Const;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CompleteOrderAdapter extends RecyclerView.Adapter<CompleteOrderAdapter.OrderViewHolder> {
//    private List<CompleteOrderRoot.DataItem> data = new ArrayList<>();
//    private Context context;
//
//
//    @NonNull
//    @Override
//    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context = parent.getContext();
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orderlist, parent, false);
//        return new OrderViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
//        Log.d("TAG", "onBindViewHolder: " + position + data.get(position).getOrderId());
//        holder.setModel(data.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//    public void addData(List<CompleteOrderRoot.DataItem> data) {
//        for (int i = 0; i < data.size(); i++) {
//            this.data.add(data.get(i));
//            notifyItemInserted(this.data.size() - 1);
//        }
//
//    }
//
//    public class OrderViewHolder extends RecyclerView.ViewHolder {
//        ItemOrderlistBinding binding;
//
//        public OrderViewHolder(@NonNull View itemView) {
//            super(itemView);
//            binding = ItemOrderlistBinding.bind(itemView);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                binding.orderid.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.green));
//            }
//        }
//
//        public void setModel(CompleteOrderRoot.DataItem datum) {
//            binding.lytDistance.setVisibility(View.INVISIBLE);
//            binding.orderid.setText("Order ID: ".concat(datum.getOrderId()));
//            binding.orderid.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(itemView.getContext(), R.color.green)));
//
//            try {
//                CompleteOrderRoot.Orderaddress addressObj = datum.getOrderaddress();
//                if (addressObj != null) {
//
//                    binding.tvaddress1.setText(addressObj.getArea().concat(", ").concat(addressObj.getCity()));
//                    binding.tvaddress2.setText(addressObj.getAddressLine().concat(", ")
//                            + " \n" + addressObj.getArea() + " " + addressObj.getLandmark() + " " + addressObj.getPincode());
//
//
//                    binding.tvCustomername.setText(addressObj.getFirstname());
//                }
//            } catch (Exception o) {
//                Log.d("TAG", "setModel: " + o.toString());
//            }
//
//
//            if (datum.getUser() != null) {
//
//                Glide.with(binding.getRoot().getContext())
//                        .load(Const.BASE_IMG_URL + datum.getUser().getImage())
//                        .placeholder(R.drawable.user_place_holder)
//                        .error(R.drawable.user_place_holder)
//                        .circleCrop()
//                        .into(binding.imgCustomer);
//
//                itemView.setOnClickListener(v -> {
//                    Intent intent = new Intent(context, OrderDetailActivity.class);
//                    intent.putExtra("uid", datum.getUserId());
//                    intent.putExtra("oid", datum.getOrderId());
//                    intent.putExtra("from", "complete");
//                    intent.putExtra("user", new Gson().toJson(datum.getUser()));
//                    context.startActivity(intent);
//                });
//                binding.imgarrow.setOnClickListener(v -> {
//                    Intent intent = new Intent(context, OrderDetailActivity.class);
//                    intent.putExtra("uid", datum.getUserId());
//                    intent.putExtra("oid", datum.getOrderId());
//                    intent.putExtra("from", "complete");
//                    intent.putExtra("user", new Gson().toJson(datum.getUser()));
//                    context.startActivity(intent);
//                });
//            }
//        }
//    }
//}
