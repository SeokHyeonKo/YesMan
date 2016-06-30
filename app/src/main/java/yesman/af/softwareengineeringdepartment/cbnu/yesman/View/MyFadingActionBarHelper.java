package yesman.af.softwareengineeringdepartment.cbnu.yesman.View;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelperBase;

/**
 * Created by seokhyeon on 2016-07-01.
 */
public class MyFadingActionBarHelper extends FadingActionBarHelperBase {

    private ActionBar mActionBar;

    @Override
    protected int getActionBarHeight() {
        return mActionBar.getHeight();
    }

    @Override
    protected boolean isActionBarNull() {
        return mActionBar == null;
    }

    @Override
    protected void setActionBarBackgroundDrawable(Drawable drawable) {
        mActionBar.setBackgroundDrawable(drawable);
    }

    @Override
    public void initActionBar(Activity activity) {
        mActionBar = ((ActionBarActivity) activity).getSupportActionBar();
        super.initActionBar(activity);
    }
}
