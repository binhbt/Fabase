package com.example.binhbt.farecyclerview.multipleviewtype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.binhbt.demo.R;

/**
 * Created by binhbt on 7/22/2016.
 */
public class MultipleDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multipleview_demo);

        findViewById(R.id.button_list_sample).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultipleDemoActivity.this, ListVerticalMultipleViewActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button_list_sample_horizontal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultipleDemoActivity.this, ListHorizontalMultipleViewActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button_grid_horizontal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultipleDemoActivity.this, GridHorizontalMultipleViewActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button_grid_sample).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultipleDemoActivity.this, GridVerticalMultipleViewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button_staggered_sample).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultipleDemoActivity.this, StaggedVerticalMultipleViewActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button_stagged_grid_horizontal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultipleDemoActivity.this, StaggedHorizontalMultipleViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
