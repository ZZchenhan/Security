package com.hz.junxinbaoan.msg;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.msg.bean.GetMessageDetailResult;
import com.hz.junxinbaoan.msg.bean.MsgResult;
import com.hz.junxinbaoan.msg.vo.GetMessageDetailVO;
import com.hz.junxinbaoan.msg.vo.GetMessageListVo;

/**
 * Created by 86936 on 2018/7/5.
 *
 * @QQ 869360026
 */

public interface MsgApi {

    @POST("/client-api/messageDetailsApi/getMessageList")
    Observable<Result<MsgResult>> getMsgList(@Body GetMessageListVo vo);

    @POST("/client-api/messageDetailsApi/getMessageDetail")
    Observable<Result<GetMessageDetailResult>> getMsgDetails(@Body GetMessageDetailVO getMessageDetailVO);
}
