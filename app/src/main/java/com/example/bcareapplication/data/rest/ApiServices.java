package com.example.bcareapplication.data.rest;

import com.example.bcareapplication.data.model.api_model.checkCopoun.CheckCopoun;
import com.example.bcareapplication.data.model.api_model.checkCopoun.WithoutCopoun;
import com.example.bcareapplication.data.model.api_model.cities.Cities;
import com.example.bcareapplication.data.model.api_model.countries.RegisterCountries;
import com.example.bcareapplication.data.model.api_model.favorite.Favorite;
import com.example.bcareapplication.data.model.api_model.register.Registeration;

import com.example.bcareapplication.data.model.api_model.salon_reserve.SalonReserve;
import com.example.bcareapplication.data.model.api_model.salon_services.SalonServices;
import com.example.bcareapplication.data.model.api_model.salons.Salons;
import com.example.bcareapplication.data.model.api_model.service.Service;
import com.example.bcareapplication.data.model.api_model.specialists.Specialists;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("getCountriesRegister")
    Call<RegisterCountries> getCountriesList(@Query("lang") String lang);

    @GET("getCitiesRegister")
    Call<Cities> getCitesList(@Query("lang") String lang,
                              @Query("country_id") String country_id);

    @POST("register")
    @FormUrlEncoded
    Call<Registeration> userRegisteration(@Field("name") String name,
                                          @Field("email") String email,
                                          @Field("password") String password,
                                          @Field("address") String address,
                                          @Field("mobile") String mobile,
                                          @Field("age") int age,
                                          @Field("city_id") int city_id,
                                          @Field("country_id") int country_id);

    @POST("getSalons")
    @FormUrlEncoded
    Call<Salons> getSalons(@Field("token") String token,
                           @Field("lang") String lang,
                           @Field("category_id") int category_id,
                           @Field("user_latitude") String user_latitude,
                           @Field("user_longitude") String user_longitude,
                           @Field("country_id") int country_id,
                           @Field("orderBy") String orderBy,
                           @Field("salontype_id") int salontype_id);

    @POST("getServices")
    @FormUrlEncoded
    Call<Service> getServices(@Field("token") String token,
                              @Field("lang") String lang,
                              @Field("salontype_id") int salontype_id);

    @POST("addFavorite")
    @FormUrlEncoded
    Call<Favorite> addFavorite(@Field("token") String token,
                               @Field("salon_id") int salon_id,
                               @Field("toggle") int toggle);

    @POST("getSpecialists")
    @FormUrlEncoded
    Call<Specialists> getSpecialists(@Field("token") String token,
                                     @Field("lang") String lang,
                                     @Field("country_id") int country_id,
                                     @Field("salontype_id") int salontype_id,
                                     @Field("specialistgroup_id") int specialistgroup_id,
                                     @Field("orderBy") String orderBy);

    @POST("getSalonServices")
    @FormUrlEncoded
    Call<SalonServices> getSalonServices(@Field("token") String token,
                                         @Field("lang") String lang,
                                         @Field("salon_id") int country_id);

    @POST("checkCopoun")
    @FormUrlEncoded
    Call<CheckCopoun> checkCopoun(@Field("token") String token,
                                  @Field("lang") String lang,
                                  @Field("copoun_code") String copoun_code);

    @POST("checkCopoun")
    @FormUrlEncoded
    Call<WithoutCopoun> withoutCopoun(@Field("token") String token,
                                    @Field("lang") String lang,
                                    @Field("copoun_code") String copoun_code);

    @POST("reserve")
    @FormUrlEncoded
    Call<SalonReserve> salonReserve(@Field("token") String token,
                                    @Field("lang") String lang,
                                    @Field("salon_id") int salon_id,
                                    @Field("total_price") float total_price,
                                    @Field("discount_percentage") int discount_percentage,
                                    @Field("price_after_discount") float price_after_discount,
                                    @Field("reservation_date") String reservation_date,
                                    @Field("client_name") String client_name,
                                    @Field("client_mobile") String client_mobile,
                                    @Field("place") String place,
                                    @Field("services_id[]")List<String> services_id);

}
