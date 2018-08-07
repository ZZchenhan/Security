package com.hz.junxinbaoan.weight.site;



import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.hz.junxinbaoan.bean.Result;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public interface Api {

    @GET("/client-api/area/getAllDistrict")
    Observable<Result<List<District>>> getAllDistrict(@Query("cityId")int cityId);


    @GET("/client-api/area/getAllCity ")
    Observable<Result<List<City>>> getAllCity(@Query("provinceId")int provinceId);

    @GET("/client-api/area/getAllProvince")
    Observable<Result<List<Province>>> getAllProvince();
}
