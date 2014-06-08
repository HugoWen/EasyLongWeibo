package com.tutor.easychangweibo;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.os.Environment;

/**
 * 发布微博内容文字显示成图片处理
 * @author wenhan
 * 
 * @param 官方微博
 * http://weibo.com/whfallen
 * 
 */
public class textViewActivity {

	/**
	 * 构造函数
	 * 发布微博内容文字显示成图片处理
	 * @param wordNum		每行的字数
	 * @param width		图像显示的宽度
	 * @param context		EditText的内容
	 * @param height		图像显示的高度
	 */
	public static void textViewActivity(int wordNum, int width, int height, String[] context) {
		
		try {
			
			//设置文字在图片中的显示间距
			int x = 10, y = 20;			
			
			Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
			
			//创建画布
			Canvas canvas = new Canvas(bitmap);
			//设置画布背景颜色
			canvas.drawARGB(255,135,135,135);

			//创建画笔
			Paint paint = new Paint();
			//通过画笔设置字体的大小、格式、颜色
			paint.setTextSize(14);
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			//paint.setColor(color.black);
			
			//将处理后的内容画到画布上
			String []ss = context;
			for(int i = 0; i < height; i++){
				canvas.drawText(ss[i], x, y, paint);
				y = y + 20;
			}
			
			//工具签名
			canvas.drawText("通过ANDROID【长微博轻松发】工具发布 ", x, y, paint);
			canvas.drawText("【http://weibo.com/happytutor】 ", x, y + 20, paint);
			
			canvas.save(Canvas.ALL_SAVE_FLAG);
			canvas.restore();
			
			//设置保存路径
			String path = Environment.getExternalStorageDirectory() + "/332.png";
			
			FileOutputStream os = new FileOutputStream(new File(path));
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
			//Toast.makeText(this, "图片保存到路径：" + path, Toast.LENGTH_SHORT);
			os.flush();
			os.close();
			

			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
