package sz.tianhe.baselib.api;

import io.reactivex.Observable;
import retrofit2.http.POST;
import sz.tianhe.baselib.model.bean.Result;

/**
 * 项目名称:etc_wallet
 * 类描述 api接口
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 17:43
 */
public interface Api {
    @POST("/getData")
    Observable<Result> getData(String params);
}
