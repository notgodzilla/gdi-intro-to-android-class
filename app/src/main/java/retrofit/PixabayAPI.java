package retrofit;

import java.util.Map;

import models.ImageList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


/**
 * Created by fgodi on 4/12/2018.
 */

public interface PixabayAPI {
    @GET("api")
    Call<ImageList> baseApiCall(@QueryMap Map<String, String> parameter);
}