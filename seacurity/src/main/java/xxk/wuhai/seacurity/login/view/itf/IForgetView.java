package xxk.wuhai.seacurity.login.view.itf;

import sz.tianhe.baselib.view.IBaseView;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/25 22:29
 */
public interface IForgetView extends IBaseView {
    /**
     * 修改密码成功
     */
    void updateForgetSuccess();

    /**
     * 修改密码失败
     */
    void updateForgetFaile(String msg);
}
