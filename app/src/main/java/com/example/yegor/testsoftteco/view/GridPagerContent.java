package com.example.yegor.testsoftteco.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.example.yegor.testsoftteco.R;

import java.util.List;

public class GridPagerContent extends LinearLayout implements View.OnClickListener {

    public static final int MAX_ELEMENTS = 6;

    private List<GridPagerItemAttributes> params;
    private OnChoose onChoose;

    public GridPagerContent(Context context, List<GridPagerItemAttributes> params, OnChoose listener) {
        super(context);

        if (params.size() > MAX_ELEMENTS)
            throw new RuntimeException("Can't be more than " + MAX_ELEMENTS);

        this.params = params;
        this.onChoose = listener;

        init(params);
    }

    public GridPagerContent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridPagerContent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GridPagerContent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setParams(List<GridPagerItemAttributes> params) {
        this.params = params;
        init(params);
    }

    public void setOnChoose(OnChoose onChoose) {
        this.onChoose = onChoose;
    }

    private int getPositionById(int id) {

        for (int i = 0; i < params.size(); i++)
            if (params.get(i).getId() == id)
                return i;

        throw new RuntimeException("No such position.");
    }

    @Override
    public void onClick(View view) {

        if (onChoose == null)
            throw new UnsupportedOperationException("You must implement callback operation");

        onChoose.onToggleChoose(view.getId(), getPositionById(view.getId()));
    }


    private void init(List<GridPagerItemAttributes> params) {

        this.setOrientation(VERTICAL);

        LayoutParams layoutParams1 = new LayoutParams(LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.item_height));

        LinearLayout layout1 = new LinearLayout(getContext());
        LinearLayout layout2 = new LinearLayout(getContext());
        layout1.setLayoutParams(layoutParams1);
        layout1.setOrientation(HORIZONTAL);
        layout2.setLayoutParams(layoutParams1);
        layout2.setOrientation(HORIZONTAL);


        View horizontal_divider = new View(getContext());
        horizontal_divider.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 1));
        horizontal_divider.setBackgroundResource(R.color.divider);
        horizontal_divider.setVisibility(params.size() > 3 ? VISIBLE : INVISIBLE);

        this.addView(layout1);
        this.addView(horizontal_divider);
        this.addView(layout2);


        LayoutInflater inflater = LayoutInflater.from(getContext());

        LayoutParams vertDividerParams;

        int topMargin = (int) getResources().getDimension(R.dimen.some_padding_top);

        View view, verticalDivider;
        ImageView image;
        TextView text;
        GridPagerItemAttributes param;

        int addedElementsCount = params.size() - 1;

        for (int i = 0; i < MAX_ELEMENTS; i++) {

            view = inflater.inflate(
                    R.layout.include_item,
                    i < MAX_ELEMENTS / 2 - 1 ? layout1 : layout2,
                    false);


            if (i > addedElementsCount) {

                Log.w("init", "addView Space");
                Space space = new Space(getContext());
                space.setLayoutParams(view.getLayoutParams());
                this.addView(space);

                //continue;
            } else {

                image = (ImageView) view.findViewById(R.id.icon);
                text = (TextView) view.findViewById(R.id.text);

                param = params.get(i);

                view.setId(param.getId());
                image.setImageResource(param.getImage());
                text.setText(param.getText());

                view.setOnClickListener(this);

                if (i < MAX_ELEMENTS / 2)
                    layout1.addView(view);
                else
                    layout2.addView(view);

                Log.w("init", "addView view");

                if ((i + 1) % (MAX_ELEMENTS / 2) != 0) {

                    vertDividerParams = new
                            LayoutParams(1, LayoutParams.MATCH_PARENT);
                    verticalDivider = new View(getContext());
                    verticalDivider.setBackgroundResource(R.color.divider);

                    Log.w("init", "addView verticalDivider");

                    Log.w("init", " " + addedElementsCount + " " + (MAX_ELEMENTS / 2));

                    if (i < MAX_ELEMENTS / 2) {//if > 3
                        if ((addedElementsCount + 1) < MAX_ELEMENTS / 2) {
                            vertDividerParams.bottomMargin = topMargin;
                            vertDividerParams.topMargin = topMargin;
                        } else
                            vertDividerParams.topMargin = topMargin;
                    } else
                        vertDividerParams.bottomMargin = topMargin;

                    verticalDivider.setLayoutParams(vertDividerParams);

                    if (i < MAX_ELEMENTS / 2)
                        layout1.addView(verticalDivider);
                    else
                        layout2.addView(verticalDivider);
                }

            }

        }

    }

    public interface OnChoose {
        void onToggleChoose(@IdRes int id, int position);
    }

}
