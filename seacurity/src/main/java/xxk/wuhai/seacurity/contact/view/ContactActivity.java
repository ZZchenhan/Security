package xxk.wuhai.seacurity.contact.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;

public class ContactActivity extends BaseActivity {

    @Override
    public int layoutId() {
        return R.layout.activity_contact;
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "通讯录";
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
    ContactFragment contactFragment;
    @Override
    public void initView() {
        contactFragment = new ContactFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",getIntent().getStringExtra("title"));
        bundle.putInt("depId",getIntent().getIntExtra("depId",0));
        contactFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.content,contactFragment).show(contactFragment).commit();
    }

    @Override
    public void findViews() {

    }

    public  static void openActivity(Context context,String title,int depId){
        context.startActivity(new Intent(context,ContactActivity.class).putExtra("title",title).putExtra("depId",depId));
    }
}
