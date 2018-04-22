package com.booking.retrofit;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class RetrofitClient {
	
	public static Retrofit retrofit;
	
	public static Retrofit buildRetrofitClient(String baseUrl) {
		retrofit=new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		return retrofit;
	}
	
	
}
