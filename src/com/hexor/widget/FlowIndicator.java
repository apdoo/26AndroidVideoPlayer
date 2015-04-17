package com.hexor.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.hexor.common.Constans;
import com.hexor.activity.R;

/**
 * 自定义view子类
 *
 */
public class FlowIndicator extends View {
	private int count;
	private float space, radius;
	private int point_normal_color, point_seleted_color;

	// 选中
	private int seleted = 0;

	// background seleted normal

	/** 参考：http://blog.csdn.net/qinjuning/article/details/7310620
	 * Context:   1、它描述的是一个应用程序环境的信息，即上下文。
				 2、该类是一个抽象(abstract class)类，Android提供了该抽象类的具体实现类(后面我们会讲到是ContextIml类)。
				 3、通过它我们可以获取应用程序的资源和类，也包括一些应用级别操作，例如：启动一个Activity，发送广播，接受Intent
				 信息 等。。
	 */
	public FlowIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		/**
		 * 从上下文中获得自定义组件的参数信息
		 */
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.FlowIndicator);
		/**
		 * TypedArray是一个用于存放恢复obtainStyledAttributes(AttributeSet, int[], int, int)或 obtainAttributes(AttributeSet, int[])  值的一个数组容器
		 * 从自定义组件的参数信息typeArray获取相应值，第二个参数为默认值，如第一个参数在atts.xml中没有定义，返回第二个参数值
		 * 从自定义的组件获得参数规则:组件名_组件属性名 如: FlowIndicator_count 组件名:FlowIndicator 属性之一名:count
 		 */
		count = a.getInteger(R.styleable.FlowIndicator_count, 4);
		space = a.getDimension(R.styleable.FlowIndicator_space, 9);
		radius = a.getDimension(R.styleable.FlowIndicator_point_radius, 9);
		point_normal_color = a.getColor(
				R.styleable.FlowIndicator_point_normal_color, 0x000000);
		point_seleted_color = a.getColor(
				R.styleable.FlowIndicator_point_seleted_color, 0xffff07);
		int sum = attrs.getAttributeCount();
		if (Constans.DEBUG) {//如果开启debug模式
			String str = "";
			for (int i = 0; i < sum; i++) {
				String name = attrs.getAttributeName(i);
				String value = attrs.getAttributeValue(i);
				str += "attr_name :" + name + ": " + value + "\n";
			}
			Log.i("attribute", str);
		}
		/**
		 * 最后一定不要忘记typeArray.recycle()：
		 * 当操作完成以后，一定要调用recycle()方法。用于检索的索引值在这个结构对应的位置给obtainStyledAttributes属性。
		 */
		a.recycle();
	}

	public void setSeletion(int index) {
		this.seleted = index;
		invalidate();
	}

	public void setCount(int count) {
		this.count = count;
		invalidate();
	}

	public void next() {
		if (seleted < count - 1)
			seleted++;
		else
			seleted = 0;
		invalidate();
	}

	public void previous() {
		if (seleted > 0)
			seleted--;
		else
			seleted = count - 1;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setAntiAlias(true);

		float width = (getWidth() - ((radius * 2 * count) + (space * (count - 1)))) / 2.f;

		for (int i = 0; i < count; i++) {
			if (i == seleted)
				paint.setColor(point_seleted_color);
			else
				paint.setColor(point_normal_color);
			canvas.drawCircle(width + getPaddingLeft() + radius + i
					* (space + radius + radius), getHeight() / 2, radius, paint);

		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));
	}

	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = (int) (getPaddingLeft() + getPaddingRight()
					+ (count * 2 * radius) + (count - 1) * radius + 1);
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	private int measureHeight(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = (int) (2 * radius + getPaddingTop() + getPaddingBottom() + 1);
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

}
