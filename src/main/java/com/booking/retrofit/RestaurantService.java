package com.booking.retrofit;

import java.util.List;

import com.booking.model.Restaurant;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestaurantService {

	@GET("getRestaurantsByName/{restaurantName}/{numberOfTables}")
	Call<List<Restaurant>> getRestaurantsByName(
			@Path("restaurantName") String restaurantName,
			@Path("numberOfTables") Integer numberOfTablesRequired,
			@Header("Authorization") String credentials);
	
	@GET("getRestaurantsByAddress/{address}/{numberOfTables}")
	Call<List<Restaurant>> getRestaurantsByAddress(
			@Path("address") String address,
			@Path("numberOfTables") Integer numberOfTablesRequired,
			@Header("Authorization") String credentials);
	
	@GET("getRestaurantById/{restaurantId}")
	Call<ResponseBody> getRestaurantById(
			@Path("restaurantId") Integer restaurantId,
			@Header("Authorization") String credentials);
	
	@PUT("update")
	Call<Restaurant> updateRestaurantDetails(
			@Body RequestBody restaurant,
			@Header("Authorization") String credentials);
	
}
