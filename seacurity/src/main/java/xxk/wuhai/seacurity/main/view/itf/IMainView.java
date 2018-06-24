package xxk.wuhai.seacurity.main.view.itf;

import sz.tianhe.baselib.view.IBaseView;
import xxk.wuhai.seacurity.bean.CompanyBean;
import xxk.wuhai.seacurity.bean.DutyInfoBean;
import xxk.wuhai.seacurity.bean.UserInfoBean;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/24 22:01
 */
public interface IMainView extends IBaseView {
    /**
     * 设置用户信息
     *
     * @param userInfo
     */
    void setUserInfo(UserInfoBean userInfo);

    /**
     * 设置公司信息
     *
     * @param companyInfo
     */
    void setCompanyInfo(CompanyBean companyInfo);

}
