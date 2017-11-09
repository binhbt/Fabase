package com.vn.fa.base.util;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by binhbt on 10/10/2016.
 */
public class FontHelper {
    private Typeface typeface;
    static volatile FontHelper instance;
    public static FontHelper getDefault(AssetManager assets, String assetsFontFileName) {
        if (instance == null) {
            synchronized (FontHelper.class) {
                if (instance == null) {
                    instance = new FontHelper(assets, assetsFontFileName);
                }
            }
        }
        return instance;
    }
    public FontHelper(Typeface typeface)
    {
        this.typeface = typeface;
    }

    public FontHelper(AssetManager assets, String assetsFontFileName)
    {
        typeface = Typeface.createFromAsset(assets, assetsFontFileName);
    }

    public void replaceFonts(ViewGroup viewTree)
    {
        View child;
        for(int i = 0; i < viewTree.getChildCount(); ++i)
        {
            child = viewTree.getChildAt(i);
            if(child instanceof ViewGroup)
            {
                // recursive call
                replaceFonts((ViewGroup)child);
            }
            else if(child instanceof TextView)
            {
                // base case
                ((TextView) child).setTypeface(typeface);
            }
        }
    }
}