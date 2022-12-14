//package com.jellysoft.deliveryapp.fragments;
//
//import static android.app.Activity.RESULT_OK;
//import static android.provider.MediaStore.MediaColumns.DATA;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.databinding.DataBindingUtil;
//import androidx.fragment.app.Fragment;
//
//import com.bumptech.glide.Glide;
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.retrytech.deliveryboy.R;
//import com.retrytech.deliveryboy.SessionManager;
//import com.retrytech.deliveryboy.VegiUtils;
//import com.retrytech.deliveryboy.activites.LoginActivity;
//import com.retrytech.deliveryboy.databinding.BottomSheetEditprofileBinding;
//import com.retrytech.deliveryboy.databinding.FragmentProfileBinding;
//import com.retrytech.deliveryboy.models.DeliveryBoyRoot;
//import com.retrytech.deliveryboy.models.ProfileRoot;
//import com.retrytech.deliveryboy.models.RestResponse;
//import com.retrytech.deliveryboy.popups.ConfirmationPopup;
//import com.retrytech.deliveryboy.retrofit.Const;
//import com.retrytech.deliveryboy.retrofit.RetrofitBuilder;
//import com.retrytech.deliveryboy.retrofit.RetrofitService;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.io.File;
//import java.text.DecimalFormat;
//import java.util.HashMap;
//
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.RequestBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//
//public class ProfileFragment extends Fragment {
//    private static final int GALLERY_CODE = 100;
//    private static final String TAG = "profilefrag";
//    private static final int PERMISSION_REQUEST_CODE = 101;
//    FragmentProfileBinding binding;
//    SessionManager sessionManager;
//    RetrofitService service;
//    Uri selectedImage;
//    private String token;
//    private ProfileRoot.Data data;
//    private BottomSheetDialog bottomSheetDialog;
//    private BottomSheetEditprofileBinding editprofileBinding;
//    private String picturePath;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
//        sessionManager = new SessionManager(getContext());
//        service = RetrofitBuilder.create();
//        return binding.getRoot();
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if (sessionManager.getBooleanValue(Const.IS_LOGIN)) {
//            token = sessionManager.getUser().getData().getToken();
//            getData();
//            initListnear();
//        }
//    }
//
//    private void initListnear() {
//        binding.switchAvailable.setOnClickListener(v -> {
//            Log.d(TAG, "initListnear: " + binding.switchAvailable.isChecked());
//            if (binding.switchAvailable.isChecked()) {
//                changeStatus(1L);
//
//            } else {
//                changeStatus(0L);
//
//            }
//
//        });
//
//
//    }
//
//    private void openEditProfileSheet() {
//        bottomSheetDialog = new BottomSheetDialog(getActivity());
//        editprofileBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.bottom_sheet_editprofile, null, false);
//        bottomSheetDialog.setContentView(editprofileBinding.getRoot());
//        bottomSheetDialog.show();
//
//        editprofileBinding.etName.setText(data.getFullname());
//        Glide.with(binding.getRoot().getContext())
//                .load(Const.BASE_IMG_URL + data.getImage())
//                .circleCrop()
//                .placeholder(R.drawable.user_place_holder)
//                .error(R.drawable.user_place_holder)
//                .circleCrop()
//
//                .into(editprofileBinding.imgUser);
//        editprofileBinding.btnPencil.setOnClickListener(v -> choosePhoto());
//        editprofileBinding.btnclose.setOnClickListener(v -> bottomSheetDialog.dismiss());
//        editprofileBinding.tvSubmit.setOnClickListener(v -> updateProfile());
//    }
//
//    private void updateProfile() {
//        binding.pd.setVisibility(View.VISIBLE);
//        service = RetrofitBuilder.create();
//        RequestBody firstname = RequestBody.create(MediaType.parse("text/plain"), editprofileBinding.etName.getText().toString());
//
//        HashMap<String, RequestBody> map = new HashMap<>();
//
//        MultipartBody.Part body = null;
//        if (picturePath != null) {
//            File file = new File(picturePath);
//            RequestBody requestFile =
//                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
//            body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
//
//        }
//        map.put("fullname", firstname);
//        Call<DeliveryBoyRoot> call = service.updateUser(Const.DEV_KEY, token, map, body);
//        call.enqueue(new Callback<DeliveryBoyRoot>() {
//            @Override
//            public void onResponse(Call<DeliveryBoyRoot> call, Response<DeliveryBoyRoot> response) {
////                if (response.code() == 200) {
//                if (response.body().getData() != null) {
//
//                    Glide.with(binding.getRoot().getContext())
//                            .load(Const.BASE_IMG_URL + response.body().getData().getImage())
//                            .circleCrop()
//                            .placeholder(R.drawable.user_place_holder)
//                            .error(R.drawable.user_place_holder)
//                            .circleCrop()
//
//                            .into(binding.imgUser);
//                    binding.tvName.setText(response.body().getData().getFullname());
//                    Toast.makeText(getContext(), getString(R.string.profileupdatesuccessfully), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//                binding.pd.setVisibility(View.GONE);
//                bottomSheetDialog.dismiss();
////                }
//            }
//
//            @Override
//            public void onFailure(Call<DeliveryBoyRoot> call, Throwable t) {
//                binding.pd.setVisibility(View.GONE);
//                bottomSheetDialog.dismiss();
//            }
//        });
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && null != data) {
//
//            selectedImage = data.getData();
//
//            Glide.with(getActivity())
//                    .load(selectedImage)
//                    .placeholder(R.drawable.user_place_holder)
//                    .error(R.drawable.user_place_holder)
//                    .circleCrop()
//                    .into(editprofileBinding.imgUser);
//            String[] filePathColumn = {DATA};
//
//            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            picturePath = cursor.getString(columnIndex);
//            cursor.close();
//
//
//        }
//    }
//
//    private void choosePhoto() {
//        if (checkPermission()) {
//            Intent i = new Intent(Intent.ACTION_PICK,
//                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(i, GALLERY_CODE);
//        } else {
//            requestPermission();
//        }
//    }
//
//    private boolean checkPermission() {
//        int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
//        return result == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private void requestPermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
//            Toast.makeText(getActivity(), getString(R.string.permissonmsg), Toast.LENGTH_LONG).show();
//        } else {
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Log.e("value", "Permission Granted, Now you can use local drive .");
//                choosePhoto();
//            } else {
//                Log.e("value", "Permission Denied, You cannot use local drive .");
//            }
//        }
//    }
//
//    private void changeStatus(Long i) {
//        binding.pd.setVisibility(View.VISIBLE);
//        Call<RestResponse> call = service.changeStatus(Const.DEV_KEY, token, i);
//        call.enqueue(new Callback<RestResponse>() {
//            @Override
//            public void onResponse(Call<RestResponse> call, Response<RestResponse> response) {
//                if (response.code() == 200) {
//                    if (response.body().isStatus() && getActivity() != null) {
//                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                        getData();
//                    }
//                    binding.pd.setVisibility(View.GONE);
//                }
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
//    private void openLogoutSheet() {
//        new ConfirmationPopup(getActivity(), "Are you really want to logout?", "", new ConfirmationPopup.OnPopupClickListner() {
//            @Override
//            public void onPositive() {
//                logoutUser();
//            }
//
//            @Override
//            public void onNegative() {
//
//            }
//        }, "Yes", "No");
//
//    }
//
//    private void logoutUser() {
//        Call<RestResponse> call = service.logOut(Const.DEV_KEY, token);
//        call.enqueue(new Callback<RestResponse>() {
//            @Override
//            public void onResponse(Call<RestResponse> call, Response<RestResponse> response) {
//                if (response.code() == 200) {
//                    if (response.body() != null && response.body().isStatus()) {
//                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                        sessionManager.saveUser(new DeliveryBoyRoot());
//                        sessionManager.saveBooleanValue(Const.IS_LOGIN, false);
//                        startActivity(new Intent(getContext(), LoginActivity.class));
//                        getActivity().finishAffinity();
//                    } else {
//                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RestResponse> call, Throwable t) {
////ll
//            }
//        });
//    }
//
//    private void getData() {
//        binding.pd.setVisibility(View.VISIBLE);
//        Call<ProfileRoot> call = service.getProfile(Const.DEV_KEY, token);
//        call.enqueue(new Callback<ProfileRoot>() {
//            @Override
//            public void onResponse(Call<ProfileRoot> call, Response<ProfileRoot> response) {
//                if (response.code() == 200 && response.body().isStatus() && response.body().getData() != null) {
//                    data = response.body().getData();
//                    setData();
//                }
//                binding.pd.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onFailure(Call<ProfileRoot> call, Throwable t) {
////ll
//            }
//        });
//    }
//
//    private void setData() {
//        binding.lytLogOut.setOnClickListener(v -> openLogoutSheet());
//        binding.btnEdit.setOnClickListener(v -> openEditProfileSheet());
//        DecimalFormat df = new DecimalFormat("###.##");
//        // binding.deliveryCount.setText(String.valueOf(data.getTotalDeliveries()));
//        binding.amountToPay.setText(VegiUtils.currency + df.format(Float.parseFloat(data.getPayment())));
//        binding.tvName.setText(data.getFullname());
//        binding.tvUserName.setText("@" + data.getUsername());
//        Glide.with(binding.getRoot().getContext())
//                .load(Const.BASE_IMG_URL + data.getImage())
//                .circleCrop()
//                .placeholder(R.drawable.user_place_holder)
//                .error(R.drawable.user_place_holder)
//                .circleCrop()
//
//                .into(binding.imgUser);
//        if (data.getIsAvialable() == 1) {
//            binding.switchAvailable.setTrackTintList(ContextCompat.getColorStateList(getActivity(), R.color.color_black));
//            binding.switchAvailable.setChecked(true);
//        } else {
//            binding.switchAvailable.setTrackTintList(ContextCompat.getColorStateList(getActivity(), R.color.color_gray_lightbtn));
//            binding.switchAvailable.setChecked(false);
//        }
//    }
//}