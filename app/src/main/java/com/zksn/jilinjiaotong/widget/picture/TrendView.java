package com.zksn.jilinjiaotong.widget.picture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import com.zksn.jilinjiaotong.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 天气趋势折线图
 *
 * @author Dave
 */
public class TrendView extends View {

    private Paint mPointPaint; // 点 画笔
    private Paint mTextPaint; // 度数 画笔
    private Paint mTitlePaint; // 标题“趋势” 画笔
    private Paint mTimePaint; // 时间 画笔
    private Paint mLinePaint1; // 上线 画笔
    private Paint mLinePaint2; // 下线 画笔

    private int x[] = new int[5]; // 横坐标 6个点的位置
    private float radius = 4; // 点 的半径
    private int h; // 纵坐标
    private List<String> topTem; // 最高温度的集合
    private List<String> lowTem; // 最低温度的集合


    private Context c;

    public TrendView(Context context) {
        super(context);
        this.c = context;
        init();
    }

    public TrendView(Context context, AttributeSet attr) {
        super(context, attr);
        this.c = context;
        init();
    }

    private void init() {

        topTem = new ArrayList<>();
        lowTem = new ArrayList<>();

        // 初始化各个画笔
        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);
        mPointPaint.setColor(Color.WHITE);

        mLinePaint1 = new Paint();
        mLinePaint1.setColor(Color.YELLOW);
        mLinePaint1.setAntiAlias(true);
        mLinePaint1.setStrokeWidth(4);
        mLinePaint1.setStyle(Style.FILL);

        mLinePaint2 = new Paint();
        mLinePaint2.setColor(Color.BLUE);
        mLinePaint2.setAntiAlias(true);
        mLinePaint2.setStrokeWidth(4);
        mLinePaint2.setStyle(Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(24);
        mTextPaint.setTextAlign(Align.CENTER);

        mTitlePaint = new Paint();
        mTitlePaint.setAntiAlias(true);
        mTitlePaint.setColor(Color.WHITE);
        mTitlePaint.setTextSize(24);
        mTitlePaint.setTextAlign(Align.CENTER);

        mTimePaint = new Paint();
        mTimePaint.setAntiAlias(true);
        mTimePaint.setColor(Color.WHITE);
        mTimePaint.setTextSize(24);
        mTimePaint.setTextAlign(Align.CENTER);
    }

    // 设置每个点的横坐标，等分12分，以及总的纵坐标
    public void setWidthHeight(int w, int h) {
        x[0] = w / 10;
        x[1] = w * 3 / 10;
        x[2] = w * 5 / 10;
        x[3] = w * 7 / 10;
        x[4] = w * 9 / 10;
        /*	x[5] = w * 11 / 14;
        x[6] = w * 13 / 14;*/
        this.h = h;
    }


    // 设置温度
    public void setTemperature(List<String> top, List<String> low) {
        this.topTem = top;
        this.lowTem = low;

        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float space = 0f;
        float space1 = 0f;
        int temspace =5;
        int picSize = (int) c.getResources().getDimension(R.dimen.picSize); // 图片的高
        FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        // 计算文字高度
        float fontHeight = fontMetrics.bottom - fontMetrics.top;

        int h = this.h / 8;// 初始坐标
        int h2 = (int) (h - fontHeight / 2); // 度数的纵坐标（高温）
        int h3 = (int) (h - fontHeight - picSize); // 图片的纵坐标（高温）

        int h4 = (int) (h + fontHeight); // 度数的纵坐标（低温）
        int h5 = (int) (h + fontHeight); // 图片的纵坐标（低温）
        int topY = h3; // 记录最高温度的纵坐标

        for (int i = 0; i < topTem.size(); i++) {
            space = (-Integer.valueOf(topTem.get(i))) * temspace;
            if (Integer.valueOf(topTem.get(i)) != 100) {
                if (i != topTem.size() - 1) {
                    // 画5条线
                    space1 = (-Integer.valueOf(topTem.get(i + 1))) * temspace;
                    canvas.drawLine(x[i], h + space, x[i + 1], h + space1, mLinePaint1);
                }
                canvas.drawCircle(x[i], h + space, radius, mPointPaint);
                // canvas.drawBitmap(topBmps[i], x[i] - topBmps[i].getWidth() /
                // 2, h3 + space, null);
            }
            if (topY > h3 + space) {
                topY = (int) (h3 + space);
            }
        }
        int lowY = h4; // 记录最低温度的纵坐标
        for (int i = 0; i < lowTem.size(); i++) {
            space = (-Integer.valueOf(lowTem.get(i))) * temspace;
            if (i != lowTem.size() - 1) {
                space1 = (-Integer.valueOf(lowTem.get(i + 1))) * temspace;
                canvas.drawLine(x[i], h + space, x[i + 1], h + space1, mLinePaint2);
            }
            //canvas.drawText(lowTem.get(i) + "°", x[i], h4 + space, mTextPaint);
            canvas.drawCircle(x[i], h + space, radius, mPointPaint);
            // canvas.drawBitmap(lowBmps[i], x[i] - lowBmps[i].getWidth() / 2,
            // h5 + space, null);
            if (lowY < (int) (h4 + space + fontHeight)) {
                lowY = (int) (h4 + space + fontHeight);
            }
        }

    }

}
