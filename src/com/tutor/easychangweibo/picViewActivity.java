package com.tutor.easychangweibo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.widget.ImageView;

/**
 * 获取本地图片显示
 * @author wenhan
 * @param 官方微博  http://weibo.com/happytutor
 */
public class picViewActivity extends Activity{

	//时间戳
	String year = MainActivity.getyear();
	String month = MainActivity.getmonth();
	String day = MainActivity.getday();
	String hour = MainActivity.gethour();
	String minute = MainActivity.getminute();
	String second = MainActivity.getsecond();
	
	//设置图片路径
	String path = Environment.getExternalStorageDirectory() + "/EasyChangWeibo/" + year + month + day + hour + minute + second + ".png";
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);
		
		ImageView imageView = (ImageView) findViewById(R.id.imageView);
		
		Bitmap bitmap = getLocalBitmap(path);
		
		imageView.setImageBitmap(bitmap);
		
	}
	
	/**
	 * 获取本地图片
	 * @param url
	 * @return
	 */
	public static Bitmap getLocalBitmap(String url) {
	     try {
	          FileInputStream fis = new FileInputStream(url);
	          return BitmapFactory.decodeStream(fis);
	     } catch (FileNotFoundException e) {
	          e.printStackTrace();
	          return null;
	     }
	}
	
	/**
	 * 按钮返回事件
	 * @author wenhan
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (event.getAction() == KeyEvent.ACTION_DOWN ) {
			if(keyCode == KeyEvent.KEYCODE_BACK){
				
				//删除本地预览图片
				File deleteFile = new File(path);
				deleteFile.delete();
			}
			
		}
		return super.dispatchKeyEvent(event);
	}
}
