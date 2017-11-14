package com.vn.fa.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.fa.loader.widget.FAImageView;
import com.vn.fa.base.R;


public class AutoResizeImageView extends FAImageView {
	private int scaleWidth = 1;
	private int scaleHeight = 1;
	private boolean scaleBaseHeight = false;

	public AutoResizeImageView(final Context context) {
		this(context, null);
	}

	public AutoResizeImageView(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.circularImageViewStyle);
	}

	public AutoResizeImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// attrs.getAttributeIntValue(R.styleable., defaultValue)
		initLayout(context, attrs, defStyle);
	}

	private void initLayout(Context context, AttributeSet attrs, int defStyle) {
		// load the styled attributes and set their properties
		TypedArray attributes = context.obtainStyledAttributes(attrs,
				R.styleable.AutoResizeKeepRatio, defStyle, 0);

		scaleHeight = attributes.getInt(
				R.styleable.AutoResizeKeepRatio_ScaleHeight, 1);
		scaleWidth = attributes.getInt(
				R.styleable.AutoResizeKeepRatio_ScaleWidth, 1);
		scaleBaseHeight = attributes.getBoolean(
				R.styleable.AutoResizeKeepRatio_ScaleBaseHeight, false);
		attributes.recycle();
	}

	public void setScaleWidth(int _scaleWidth) {
		scaleWidth = _scaleWidth;
	}

	public void setScaleHeight(int _scaleHeight) {
		scaleHeight = _scaleHeight;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (scaleBaseHeight) {
			int height = MeasureSpec.getSize(heightMeasureSpec);
			int width = (height * scaleWidth) / scaleHeight;
			super.onMeasure(
					MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
					heightMeasureSpec);
		} else {
			int width = MeasureSpec.getSize(widthMeasureSpec);
			int height = (width * scaleHeight) / scaleWidth;
			super.onMeasure(widthMeasureSpec,
					MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
		}

	}
}
