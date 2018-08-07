package com.hz.junxinbaoan.contact;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.contact.bean.ContanctResult;
import com.hz.junxinbaoan.contact.vo.ApiGetDirectoryVo;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public interface ContactApi {

    @POST("/client-api/staff/getDirectory")
    Observable<Result<ContanctResult>> getDirectory(@Body ApiGetDirectoryVo vo);
}
