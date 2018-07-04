package xxk.wuhai.seacurity.login.view.itf;

import sz.tianhe.baselib.view.IBaseView;
import xxk.wuhai.seacurity.bean.UserInfoBean;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/25 22:30
 */
public interface ILoginView extends IBaseView {
    void loginSuccess(UserDetailInfo userDetailInfo);

    void loginFaile(String msg);
}
