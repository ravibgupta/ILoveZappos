package in.ravi.ilovezappos.rest;

import in.ravi.ilovezappos.model.ProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("Search")
    Call<ProductResponse> getProductsByTerm(@Query("term") String term, @Query("key") String key);
    
}