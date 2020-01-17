package com.syt.cellphone.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.syt.cellphone.R;
import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.pojo.TestPojo;
import com.syt.cellphone.util.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shenyutian
 * @data 2020-01-15 17:55
 * 功能 测试列表布局
 * 参考 https://blog.csdn.net/huachao1001/article/details/51594004
 */
public class TestAdapter extends BaseProviderMultiAdapter<TestPojo> {

    public TestAdapter() {
        super();
        /**
         * 添加三种布局类型，第一种 1x1 第二种 1x2  第三种 2x1 宽x高
         */
        addItemProvider(new ZeroProvider());
        addItemProvider(new OneProvider());
        addItemProvider(new TwoProvider());
        addItemProvider(new NonoProvider());
        addItemProvider(new ThreeProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends TestPojo> list, int i) {
        return list.get(i).getType();
    }

    /**
     * 第0种布局 空布局
     */
    class NonoProvider extends BaseItemProvider<TestPojo> {

        /**
         *     item 类型
         */
        @Override
        public int getItemViewType() {
            return TestPojo.TYPE_NONO;
        }

        /**
         * @return 返回 item 布局 layout
         */
        @Override
        public int getLayoutId() {
            return R.layout.item_test0;
        }

        @Override
        public void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable TestPojo testPojo) {
            // 设置 item 数据
        }

        @NotNull
        @Override
        public BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
            // 创建布局
            FrameLayout frameLayout = new FrameLayout(getContext());
            TextView textView = new TextView(context);
            textView.setText("我是空布局，你信吗？");
            textView.setTextSize(40);
            frameLayout.addView(textView);
//            frameLayout.setBackgroundColor(Color.YELLOW);
//            return super.onCreateViewHolder(parent, viewType);
            return createBaseViewHolder(frameLayout);
        }
    }

    /**
     * 第一种布局
     */
    class ZeroProvider extends BaseItemProvider<TestPojo> {

        /**
         *     item 类型
         */
        @Override
        public int getItemViewType() {
            return TestPojo.TYPE_ZERO;
        }

        /**
         * @return 返回 item 布局 layout
         */
        @Override
        public int getLayoutId() {
            return R.layout.item_test;
        }

        @Override
        public void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable TestPojo testPojo) {

            // 设置 item 数据
        }

        @NotNull
        @Override
        public BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
            // 创建布局
            FrameLayout frameLayout = new FrameLayout(getContext());
            TextView textView = new TextView(context);
            textView.setText("最基础的布局, 班级信息");
            textView.setTextSize(30);
            frameLayout.addView(textView);
            frameLayout.setBackgroundColor(Color.YELLOW);
//            return super.onCreateViewHolder(parent, viewType);
            return createBaseViewHolder(frameLayout);
        }
    }

    /**
     * 第二种布局
     */
    class OneProvider extends BaseItemProvider<TestPojo> {

        /**
         *     item 类型
         */
        @Override
        public int getItemViewType() {
            return TestPojo.TYPE_ONE;
        }

        /**
         * @return 返回 item 布局 layout
         */
        @Override
        public int getLayoutId() {
            return R.layout.item_test1;
        }

        @NotNull
        @Override
        public BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
            // 创建布局
            FrameLayout frameLayout = new FrameLayout(getContext());
            TextView textView = new TextView(context);
            textView.setText("第二个布局,倒计时");
            textView.setTextSize(30);
            frameLayout.addView(textView);
            frameLayout.setBackgroundColor(Color.GREEN);
//            return super.onCreateViewHolder(parent, viewType);
            return createBaseViewHolder(frameLayout);
        }

        @Override
        public void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable TestPojo testPojo) {
            // 设置 item 数据

        }

    }

    /**
     * 第三种布局
     */
    class TwoProvider extends BaseItemProvider<TestPojo> {

        /**
         *     item 类型
         */
        @Override
        public int getItemViewType() {
            return TestPojo.TYPE_TWO;
        }

        /**
         * @return 返回 item 布局 layout
         */
        @Override
        public int getLayoutId() {
            return R.layout.item_test2;
        }

        @Override
        public void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable TestPojo testPojo) {
            // 设置 item 数据
            if (baseViewHolder.getAdapterPosition() % 3 == 0) {

            }
        }

        @NotNull
        @Override
        public BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
            // 创建布局
            FrameLayout frameLayout = new FrameLayout(getContext());
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(getContext().getResources().getDisplayMetrics().widthPixels, 350);
            TextView textView = new TextView(context);
            textView.setText("第三个布局,班级荣誉");
            textView.setTextSize(30);
            frameLayout.addView(textView);
            frameLayout.setBackgroundColor(Color.BLUE);
//            return super.onCreateViewHolder(parent, viewType);
            return createBaseViewHolder(frameLayout);
        }
    }

    /**
     * 第四种布局
     * 暂定轮播图
     */
    class ThreeProvider extends BaseItemProvider<TestPojo> {

        /**
         *     item 类型
         */
        @Override
        public int getItemViewType() {
            return TestPojo.TYPE_THTEE;
        }

        /**
         * @return 返回 item 布局 layout
         */
        @Override
        public int getLayoutId() {
            return R.layout.item_test2;
        }

        @Override
        public void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable TestPojo testPojo) {
            // 设置 item 数据
            if (baseViewHolder.getAdapterPosition() % 3 == 0) {

            }
        }

        @NotNull
        @Override
        public BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
            // 创建布局
            FrameLayout frameLayout = new FrameLayout(getContext());
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(getContext().getResources().getDisplayMetrics().widthPixels, 350);

            List<PhoneRecommend> recommends = new LinkedList<>();
            recommends.add(new PhoneRecommend(1, 1, "https://dg-fd.zol-img.com.cn/t_s2000x2000/g4/M05/06/05/ChMly113aC-IVGMlAAG11ap9YqUAAXnogBWIakAAbXt993.jpg"));
            recommends.add(new PhoneRecommend(2, 2, "https://dg-fd.zol-img.com.cn/t_s2000x2000/g1/M0A/02/03/ChMljl2EabyIPg2aAAFbqHHjP_gAAP3CgAVUUYAAVvA000.jpg"));
            recommends.add(new PhoneRecommend(3, 3, "https://dg-fd.zol-img.com.cn/t_s2000x2000/g4/M07/09/05/ChMlzF2LDVeIDWpCAAEy5pe9cV0AAXzvALgkHcAATL-905.jpg"));
            recommends.add(new PhoneRecommend(4, 4, "https://dg-fd.zol-img.com.cn/t_s2000x2000/g4/M07/06/01/ChMlzF110BOICajxAAEChBTt38gAAXmwAEL5J8AAQKc522.jpg"));

            //标题 + 图片url
            List<String> titles;
            List<String> imgurls;

            Banner banner = new Banner(context);

            // 设置轮播图的宽高
//            banner.setLayoutParams(new ViewGroup.LayoutParams(context.getResources().getDisplayMetrics().widthPixels, 350));
            // 如果api高，就用steam。不行就用老方法
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                titles = recommends.stream().map(e -> e.getPhoneId()+"").collect(Collectors.toList());
                banner.setBannerTitles(titles);

                imgurls = recommends.stream().map(PhoneRecommend::getRecommendReimage).collect(Collectors.toList());
                banner.setImages(imgurls);
            } else {
                titles = new LinkedList<>();
                imgurls = new LinkedList<>();
                for (PhoneRecommend recommend : recommends) {
                    titles.add(recommend.getPhoneId()+"");
                    imgurls.add(recommend.getRecommendReimage());
                }
//            banner.setBannerTitles(titles);
                banner.setImages(imgurls);
            }
            banner.setOnBannerListener(position ->
                    ToastUtil.makeText("点击了 " + titles.get(position))
            );
            //设置轮播图加载方式
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            }).start();

            frameLayout.addView(banner);

//            return super.onCreateViewHolder(parent, viewType);
            return createBaseViewHolder(frameLayout);
        }
    }

}
