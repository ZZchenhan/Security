package xxk.wuhai.seacurity.me.view;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.work.view.MyAplyListActivity;

public class MeInfoActivity extends BaseActivity {

    MeInfoFragment meInfoFragment = new MeInfoFragment();
    MeInfoUpdateFragment meInfoUpdateFragment = new MeInfoUpdateFragment();


    @Override
    public int layoutId() {
        return R.layout.activity_me_info;
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "个人资料";
            }
        }.setNavagationBackgroudColor(R.color.colorPrimary);
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        leftIconNavagation.setTitleColor(R.color.white);
        return leftIconNavagation;
    }

    @Override
    public void initView() {
        getSupportFragmentManager().beginTransaction().add(R.id.content,meInfoFragment).
                add(R.id.content,meInfoUpdateFragment)
                .show(meInfoFragment).hide(meInfoUpdateFragment).commit();
    }

    @Override
    public void findViews() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideKeyboard();
    }

    public void change(){
        getSupportFragmentManager().beginTransaction().hide(meInfoFragment).show(meInfoUpdateFragment).commit();
    }
    private void hideKeyboard() {
        try {
            InputMethodManager inputMethod = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethod.isActive()) {
                if (this.getCurrentFocus().getWindowToken() != null) {
                    inputMethod.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }catch (Exception e){}
    }
}
