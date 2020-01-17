package com.syt.cellphone.ui;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;

import com.syt.cellphone.R;
import com.syt.cellphone.adapter.TestAdapter;
import com.syt.cellphone.pojo.TestPojo;
import com.syt.cellphone.util.TestLayoutManager;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author syt
 */
public class TestActivity extends AppCompatActivity {


    /**
     *  itemWidth   每个子item 的宽度 dp
     *  itemHeight  每个子item 的高度 dp
     */
    private static final String TAG = "TestActivity";
    @BindView(R.id.rv_test_view)
    RecyclerView rvTestView;
    @BindView(R.id.bt_test_change)
    Button btTestChange;
    private TestAdapter testAdapter;
    private List<TestPojo> testPojos;
    private int     itemWidth;
    private int     itemHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        getAndroiodScreenProperty();
        initRv();
    }

    private void initRv() {
        testPojos = new LinkedList<>();

        // 假数据
        testPojos.add(new TestPojo(1, 0, 1, 1, 1));
        testPojos.add(new TestPojo(2, 0, 2, 1, 1));
        testPojos.add(new TestPojo(3, 1, 3, 2, 1));
//        testPojos.add(new TestPojo(4, 3, 0, 0, 0));
        testPojos.add(new TestPojo(5, 0, 4, 1, 1));
        testPojos.add(new TestPojo(6, 0, 5, 1, 1));
        testPojos.add(new TestPojo(7, 2, 7, 1, 2));
        testPojos.add(new TestPojo(8, 0, 7, 1, 1));
        testPojos.add(new TestPojo(9, -1, 8, 1, 1));
        testPojos.add(new TestPojo(10, 3, 9, 1, 1));
        // 空格占位
//        testPojos.add(new TestPojo(11, -1, 0, 0, 0));
        testPojos.add(new TestPojo(12, 0, 10, 1, 1));
        // 瀑布流
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
//         卡片管理器
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return testPojos.get(position).getWidth() == 0 ? 1 : testPojos.get(position).getWidth();
//            }
//        });
        TestLayoutManager layoutManager = new TestLayoutManager(itemWidth, itemHeight, testPojos);

        //设置宽度
//        ViewGroup.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
//                getApplicationContext().getResources().getDisplayMetrics().widthPixels, 950);
//        rvTestView.setLayoutParams(layoutParams);

//        rvTestView.getLayoutParams().height = height/3;

        rvTestView.setLayoutManager(layoutManager);
        testAdapter = new TestAdapter();
        testAdapter.addData(testPojos);
//        rvTestView.addItemDecoration(new EvenItemDecoration(DpPx.Dp2Px(getApplicationContext(), 16), 4));
        rvTestView.setAdapter(testAdapter);
    }

    /**
     * 屏幕参数
     */
    public void getAndroiodScreenProperty() {
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        // 屏幕宽度（像素）
        int width = dm.widthPixels;
        // 屏幕高度（像素）
        int height = dm.heightPixels;
        // 屏幕密度（0.75 / 1.0 / 1.5）
        float density = dm.density;
        // 屏幕密度dpi（120 / 160 / 240）
        int densityDpi = dm.densityDpi;
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        // 屏幕宽度(dp)
        // 屏幕高度(dp)
        int screenWidth = (int) (width / density);
        int screenHeight = (int) (height / density);
        Log.d(TAG, "屏幕宽度（像素）：" + width);
        Log.d(TAG, "屏幕高度（像素）：" + height);
        Log.d(TAG, "屏幕密度（0.75 / 1.0 / 1.5）：" + density);
        Log.d(TAG, "屏幕密度dpi（120 / 160 / 240）：" + densityDpi);
        Log.d(TAG, "屏幕宽度（dp）：" + screenWidth);
        Log.d(TAG, "屏幕高度（dp）：" + screenHeight);
        itemWidth = screenWidth;
        itemHeight = screenHeight;

    }

    @OnClick(R.id.bt_test_change)
    public void onViewClicked() {
        testPojos.get(8).setWidth(3);
        testPojos.get(8).setHeight(3);
//        testAdapter.notify();
        testAdapter.notifyDataSetChanged();
    }

    class EvenItemDecoration extends ItemDecoration {

        /**
         * space        间隔
         * column       行数
         */
        private int space;
        private int column;

        public EvenItemDecoration(int space, int column) {
            this.space = space;
            this.column = column;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            // 每个 span分配的间隔大小
            int spanSpace = space * (column + 1) / column;
            // 列索引
            int colIndex = position % column;
            // 列左、右间隙
            outRect.left = space * (colIndex + 1) - spanSpace * colIndex;
            outRect.right = spanSpace * (colIndex + 1) - space * (colIndex + 1);
            // 行间距
            if (position >= column) {
                outRect.top = space;
            }
        }
    }
}
