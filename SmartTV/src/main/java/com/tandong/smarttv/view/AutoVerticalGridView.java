package com.tandong.smarttv.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.tandong.smarttv.R;


/**
 * Created by office on 2018/3/29.
 */

public class AutoVerticalGridView extends BaseGridView {

    public AutoVerticalGridView(Context context) {
        this(context, null);
    }

    public AutoVerticalGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoVerticalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        initAttributes(context, attrs);
    }

    protected void initAttributes(Context context, AttributeSet attrs) {
        initBaseGridViewAttributes(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.lbVerticalGridView);
        setColumnWidth(a);
        setNumColumns(a.getInt(R.styleable.lbVerticalGridView_numberOfColumns, 1));
        a.recycle();
    }

    void setColumnWidth(TypedArray array) {
        TypedValue typedValue = array.peekValue(R.styleable.lbVerticalGridView_columnWidth);
        if (typedValue != null) {
            int size = array.getLayoutDimension(R.styleable.lbVerticalGridView_columnWidth, 0);
            setColumnWidth(size);
        }
    }

    /**
     * Sets the number of columns.  Defaults to one.
     */
    public void setNumColumns(int numColumns) {
        mLayoutManager.setNumRows(numColumns);
        requestLayout();
    }

    /**
     * Sets the column width.
     *
     * @param width May be {@link android.view.ViewGroup.LayoutParams#WRAP_CONTENT}, or a size
     *              in pixels. If zero, column width will be fixed based on number of columns
     *              and view width.
     */
    public void setColumnWidth(int width) {
        mLayoutManager.setRowHeight(width);
        requestLayout();
    }
}
