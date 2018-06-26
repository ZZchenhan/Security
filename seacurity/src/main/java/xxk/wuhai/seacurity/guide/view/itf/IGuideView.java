package xxk.wuhai.seacurity.guide.view.itf;

import xxk.wuhai.seacurity.bean.CompanyBean;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/24 20:04
 */
public interface IGuideView {
    void loadCompanyView(CompanyBean companyBean);

    void versionName(String apkName,String versionName);

    void handover();

    void showPermisionUnAccept();
}
