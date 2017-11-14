package com.vn.fa.base.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by leobui on 11/13/2017.
 */

public interface IMenu {
    public void openMenu();
    public void closeMenu();
    public void onMenuOpened(View menuView);
    public void onMenuClosed(View menuView);
    public void onMenuItemSelected(View itemView);
    public void goHome();
    public void goScreen(Fragment fragment, Bundle args, int contentId);
}
