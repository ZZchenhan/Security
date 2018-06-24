package sz.tianhe.etc_wallet;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.navagation.SimpleNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;

/**
 * 主Activity
 *
 * @author ch
 * @email 869360026@qq.com
 */
public class MainActivity extends BaseActivity {

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public IBaseNavagation navagation() {
        return new SimpleNavagation(this) {
            @Override
            public String title() {
                return "首页";
            }
        }.setTitleColor(R.color.white).
                setNavagationBackgroudColor(R.color.colorPrimary)
                ;
    }

    @Override
    public void initView() {

    }

}
