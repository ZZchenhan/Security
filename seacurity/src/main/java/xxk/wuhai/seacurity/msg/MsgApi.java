package xxk.wuhai.seacurity.msg;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.msg.bean.MsgResult;
import xxk.wuhai.seacurity.msg.vo.GetMessageListVo;

/**
 * Created by 86936 on 2018/7/5.
 *
 * @QQ 869360026
 */

public interface MsgApi {

    @POST("/client-api//messageDetailsApi/getMessageList ")
    Observable<Result<MsgResult>> getMsgList(@Body GetMessageListVo vo);
}
