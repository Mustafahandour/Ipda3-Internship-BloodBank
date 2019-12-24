package com.ghandour.bbank.data.api;

import com.ghandour.bbank.data.model.contactUs.ContactUs;
import com.ghandour.bbank.data.model.contactUs.setting.Settings;
import com.ghandour.bbank.data.model.donation.Donation;
import com.ghandour.bbank.data.model.donation.donationDetails.DonationRequest;
import com.ghandour.bbank.data.model.donation.donationRequest.CreateDonationRequest;
import com.ghandour.bbank.data.model.generalResponse.GeneralResponse;
import com.ghandour.bbank.data.model.login.Login;
import com.ghandour.bbank.data.model.login.profile.Profile;
import com.ghandour.bbank.data.model.notification.Notification;
import com.ghandour.bbank.data.model.notification.notificationSetting.NotificationSetting;
import com.ghandour.bbank.data.model.posts.Post;
import com.ghandour.bbank.data.model.login.restPassword.RestPassword;
import com.ghandour.bbank.data.model.posts.favorite.Favorite;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    @POST("login")
    @FormUrlEncoded
    Call<Login> getClient(@Field("phone") String phone
            , @Field("password") String password);

    @POST("signup")
    @FormUrlEncoded
    Call<Login> getRegister(@Field("name") String name,
                            @Field("email") String email,
                            @Field("birth_date") String birth_date,
                            @Field("city_id") String city_id,
                            @Field("phone") String phone,
                            @Field("donation_last_date") String donation_last_date,
                            @Field("password") String password,
                            @Field("password_confirmation") String password_confirmation,
                            @Field("blood_type_id") String blood_type_id);

    @GET("governorates")
    Call<GeneralResponse> getGovernrates();

    @GET("cities")
    Call<GeneralResponse> getCity(@Query("governorate_id") int governorateId);

    @GET("notifications")
    Call<Notification> getNotification(@Query("api_token") String apiToken , @Query("page") int page );

    @GET("notifications-count")
    Call<Notification> getNotificationCount(@Query("api_token") String apiToken  );



    @GET("blood-types")
    Call<GeneralResponse> getBloodType();

    @POST("reset-password")
    @FormUrlEncoded
    Call<RestPassword> resetPassword(@Field("phone") String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<RestPassword> newPassword(@Field("phone") String phone,
                                   @Field("pin_code") String pin_code,
                                   @Field("password") String password,
                                   @Field("password_confirmation") String password_confirmation);

    @GET("posts")
    Call<Post> getPosts(@Query("api_token") String apiToken,
                        @Query("page") int page);

    @GET("my-favourites")
    Call<Favorite> getFavorite(@Query("api_token") String apiToken);


    @GET("donation-requests")
    Call<Donation> getDonationRequest(@Query("api_token") String apiToken,
                                      @Query("donation_id") int donation_id);

    @GET("donation-requests")
    Call<DonationRequest> getDonationDetails(@Query("api_token") String apiToken,
                                             @Query("page") int page);



    @POST("donation-request/create")
    @FormUrlEncoded
    Call<CreateDonationRequest> createDonationRequest(@Field("api_token") String apiToken,
                                                   @Field("patient_name") String patientName,
                                                   @Field("patient_age") String patientAge,
                                                   @Field("blood_type_id") String blood_type_id,
                                                   @Field("bags_num") String bags_num,
                                                   @Field("hospital_name") String hospital_name,
                                                   @Field("hospital_address") String hospital_address,
                                                   @Field("city_id") String city_id,
                                                   @Field("phone") String phone,
                                                   @Field("notes") String notes,
                                                   @Field("latitude") String latitude,
                                                   @Field("longitude") String longitude);


    @GET("categories")
    Call<GeneralResponse> getCategories();


    @POST("contact")
    @FormUrlEncoded
    Call<ContactUs> sendMessage(@Field("api_token") String api_token,
                                @Field("title") String title,
                                @Field("message") String message);

    @GET("settings")
    Call<Settings> appSetting(@Query("api_token") String api_token);

    @POST("profile")
    @FormUrlEncoded
    Call<Profile> getProfile(@Field("api_token") String api_token);



    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSetting> getNotificationsSettings(@Field("api_token") String api_token);
    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSetting> setNotificationsSettings(@Field("api_token") String api_token
                                                       ,@Field("governorates[]")List<Integer> governorates
                                                       ,@Field("blood_types[]") List<Integer> bloodTypes);
}
