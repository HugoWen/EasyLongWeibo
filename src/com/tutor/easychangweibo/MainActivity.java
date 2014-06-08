package com.tutor.easychangweibo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 发布长微博主界面
 * @author wenhan
 * @官方微博   http://weibo.com/happytutor
 */

public class MainActivity extends Activity {

	private Button btnOk;
	private Button btnClear;
	private EditText editText;
	private Button settingButton;
	
	private int fontsize;
	private Typeface fontstyle;
	private int[] fontcolor;
	private int[] backgroundcolor;
	
	//时间戳
	public static String year;
	public static String month;
	public static String day;
	public static String hour;
	public static String minute;
	public static String second;
	
	private TextView textNumber;
	public static String sText = "";	//获取写入的字符
	public static String SETTING_NAME = "easychangweiboSettings";
	public static boolean isfirstStart = true;
    @SuppressLint({ "ParserError", "ParserError", "ParserError" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        //获取屏幕分辨率
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int widthpixels = dm.widthPixels;
        
        refreshSettings();	//刷新设置的值
        //EditText
        editText = (EditText) this.findViewById(R.id.editText);
        editText.setText(sText);
        textNumber = (TextView) this.findViewById(R.id.textNumber);
        textNumber.setText(""+sText.length());
        //实时监控显示字符
        editText.addTextChangedListener(mTextWatcher);
		
        editText.setTextSize(widthpixels/30);		//设置显示字体大小
        editText.setTypeface(fontstyle);
        editText.setTextColor(Color.rgb(fontcolor[0],fontcolor[1],fontcolor[2]));
        editText.setBackgroundColor(Color.rgb(backgroundcolor[0],backgroundcolor[1],backgroundcolor[2]));
        
        //设置按钮
		settingButton = (Button)this.findViewById(R.id.btnabout);
		settingButton.setOnClickListener(new Button.OnClickListener(){
        	@SuppressLint("ParserError")
			@Override
        	public void onClick(View v){
        		//显示设置的信息
        		Intent intent = new Intent();
        		intent.setClass(MainActivity.this, settingActivity.class);
        		startActivity(intent);
        		MainActivity.this.finish();
        	}
        });
        
        //清除按钮
        btnClear = (Button) this.findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//清除EditText的内容
				editText.setText("");
			}
		});
       
        //确定按钮
        btnOk = (Button) this.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(editText.getText().toString().trim().equals("")){
					Toast.makeText(getBaseContext(), getResources().getString(R.string.MainActivity_isnullToast), Toast.LENGTH_SHORT).show();
					return;
				}
				
				final String[] arraylist = new String[]{
						getResources().getString(R.string.MainActivity_AlertDialog_saveHere), 
						getResources().getString(R.string.MainActivity_AlertDialog_preview), 
						getResources().getString(R.string.MainActivity_AlertDialog_share), 
						getResources().getString(R.string.MainActivity_AlertDialog_cancle)
						};
				Dialog dialog = new AlertDialog.Builder(MainActivity.this).
						setTitle(getResources().getString(R.string.MainActivity_AlertDialog_functionSelect)).
						setItems(arraylist, new DialogInterface.OnClickListener() {
							
							@SuppressLint("ShowToast")
							@Override
							public void onClick(DialogInterface dialog, int which) {
																
								
								
								int d_width = 1000;    //适应新浪微博解析分辨率
								int WORDNUM = (1000/(fontsize))-1;  //转化成图片时  每行显示的字数
								
								//设置文字在图片中的显示间距
								int x = 10;
								float y = (float) (fontsize*0.8);
								
								//读出EditText的内容
								String s = editText.getText().toString();
								
								//获取签名信息
								String autographText = getResources().getString(R.string.MainActivity_AlertDialog_autographText);
								String autographUrl = getResources().getString(R.string.MainActivity_AlertDialog_autographUrl);
								
								//工具签名
								s = s + "\n";
							    s = s + "--------------------\n";
							    s = s + autographText;
							    s = s + "\n" + autographUrl;
							  						
								//处理EditText内容
								textActivity textactivity = new textActivity(WORDNUM, s);

								//获取当前系统时间
								Time t = new Time();
								t.setToNow();		//取得系统当前时间
								year = "" + t.year;
								month = "" + t.month;
								day = "" + t.monthDay;
								hour = "" + t.hour;
								minute = "" + t.minute;
								second = "" + t.second;
								
								//功能选择
								switch (which) {
								case 0:		//保存图片到本地
									
									try {
										Bitmap bitmap = Bitmap.createBitmap(d_width, 35*(textactivity.getHeight() + 1), Config.ARGB_8888);
										//创建画布
										Canvas canvas = new Canvas(bitmap);										
										//设置画布背景颜色
										canvas.drawARGB(255, backgroundcolor[0], backgroundcolor[1], backgroundcolor[2]);
										//创建画笔
										Paint paint = new Paint();
										//通过画笔设置字体的大小、格式、颜色
										paint.setTextSize(fontsize);										
										//选择字体格式
										paint.setTypeface(fontstyle);										
										//选择字体颜色
										paint.setARGB(255, fontcolor[0], fontcolor[1], fontcolor[2]);										
										y = y + 10;										
										//将处理后的内容画到画布上
										String []ss = textactivity.getContext();
										for(int i = 0; i < textactivity.getHeight(); i++){
											canvas.drawText(ss[i], x, y, paint);
											y = y + 35;
										}
										
										canvas.save(Canvas.ALL_SAVE_FLAG);
										canvas.restore();
										
										File sd = Environment.getExternalStorageDirectory();
										String fpath = sd.getPath() + "/EasyChangWeibo";
										
										//设置保存路径
										String path = sd.getPath() + "/EasyChangWeibo/" + year + month + day + hour + minute + second + ".png";
										
										File file = new File(fpath);
										if(!file.exists()){
											file.mkdir();
										}
										
										FileOutputStream os = new FileOutputStream(path);
										bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);

										os.flush();
										os.close();
										
										Toast.makeText(getBaseContext(), "长微博成功保存到 "+ path, Toast.LENGTH_LONG).show();

									} catch (Exception e) {
										// TODO: handle exception
									}
									
									break;

								case 1:
									//预览
									try {
										Bitmap bitmap = Bitmap.createBitmap(d_width, 35*(textactivity.getHeight() + 1), Config.ARGB_8888);
										
										//创建画布
										Canvas canvas = new Canvas(bitmap);
										//设置画布背景颜色
										canvas.drawARGB(255, backgroundcolor[0], backgroundcolor[1], backgroundcolor[2]);
										Log.i("背景颜色为", "" + backgroundcolor[0]+backgroundcolor[1]+backgroundcolor[2]);
										//canvas.drawARGB(255,235,235,235);

										//创建画笔
										Paint paint = new Paint();
										//通过画笔设置字体的大小、格式、颜色
										paint.setTextSize(fontsize);										
										paint.setTypeface(fontstyle);
										paint.setARGB(255, fontcolor[0], fontcolor[1], fontcolor[2]);										
										y = y + 10;										
										//将处理后的内容画到画布上
										String []ss = textactivity.getContext();
										for(int i = 0; i < textactivity.getHeight(); i++){
											canvas.drawText(ss[i], x, y, paint);
											y = y + 35;
										}										
										canvas.save(Canvas.ALL_SAVE_FLAG);
										canvas.restore();
										
										File sd = Environment.getExternalStorageDirectory();
										String fpath = sd.getPath() + "/EasyChangWeibo";
										
										//设置保存路径
										String path = sd.getPath() + "/EasyChangWeibo/" + year + month + day + hour + minute + second + ".png";

										File file = new File(fpath);
										if(!file.exists()){
											file.mkdir();
										}
										FileOutputStream os = new FileOutputStream(new File(path));
										bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
										os.flush();
										os.close();
										
									} catch (Exception e) {
										// TODO: handle exception
									}
																		
									//转到图片显示picViewActivity
									Intent intent = new Intent();
									intent.setClass(MainActivity.this, picViewActivity.class);
									startActivity(intent);
																		
									break;

								case 2:
									//分享
									try {
																										
										Bitmap bitmap = Bitmap.createBitmap(d_width, 35*(textactivity.getHeight() + 1), Config.ARGB_8888);
										//创建画布
										Canvas canvas = new Canvas(bitmap);
										//设置画布背景颜色
										canvas.drawARGB(255, backgroundcolor[0], backgroundcolor[1], backgroundcolor[2]);
										//创建画笔
										Paint paint = new Paint();
										//通过画笔设置字体的大小、格式、颜色
										paint.setTextSize(fontsize);
										paint.setTypeface(fontstyle);
										paint.setARGB(255, fontcolor[0], fontcolor[1], fontcolor[2]);										
										y = y + 10;										
										//将处理后的内容画到画布上
										String []ss = textactivity.getContext();
										for(int i = 0; i < textactivity.getHeight(); i++){
											canvas.drawText(ss[i], x, y, paint);
											y = y + 35;
										}										
										canvas.save(Canvas.ALL_SAVE_FLAG);
										canvas.restore();								
										
										File sd = Environment.getExternalStorageDirectory();
										String fpath = sd.getPath() + "/EasyChangWeibo";
										
										//设置保存路径
										String path = sd.getPath() + "/EasyChangWeibo/" + year + month + day + hour + minute + second + ".png";

										File file = new File(fpath);
										if(!file.exists()){
											file.mkdir();
										}

										FileOutputStream os = new FileOutputStream(new File(path));
										bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
										
										os.flush();
										os.close();
										
										Intent shareIntent = new Intent(Intent.ACTION_SEND);
										
						                File file2 = new File(path);
						                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file2));
						                
						                shareIntent.setType("image/*");
						                startActivity(Intent.createChooser(shareIntent, "发布"));

									} catch (Exception e) {
										// TODO: handle exception
									}
									
								default:
									break;
								}
								
							}
						}).create();
				
				dialog.show();
			}
		});
        
        
        //按钮点击效果
        TouchShow mTouchShow = new TouchShow();
        settingButton.setOnTouchListener(mTouchShow);
        btnClear.setOnTouchListener(mTouchShow);
        btnOk.setOnTouchListener(mTouchShow);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //调用onOptionItemSelected()方法实现菜单项的选择
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.menu_about:
			
			startActivity(new Intent(this, aboutActivity.class));
			

			return true;
			//更多的设置选项在此实现
			//..............
		case R.id.menu_setting:
			
			Intent intent = new Intent();
    		intent.setClass(MainActivity.this, settingActivity.class);
    		startActivity(intent);
    		MainActivity.this.finish();

			return true;
			
		case R.id.menu_helpinfo:
			startActivity(new Intent(this, helpActivity.class));

			return true;
			
		case R.id.menu_updateinfo:
			startActivity(new Intent(this, updateInfoActivity.class));

			return true;
		}
    	return false;
    }
    
    /**
     * 设置语言为中文简体
     * @author Anders Jing
     * */
    public void setChineseSimple(){
    	Toast.makeText(getBaseContext(), "设置成中文简体", Toast.LENGTH_LONG).show();
		Resources resources = getResources();//获得res资源对象
		Configuration config = resources.getConfiguration();//获得设置对象
		DisplayMetrics dm = resources .getDisplayMetrics();//获得屏幕参数：主要是分辨率，像素等。
		config.locale = Locale.SIMPLIFIED_CHINESE; //简体中文
		resources.updateConfiguration(config, dm);
	}
    
    
    /**
     * 响应返回键
     * @author Anders Jing
     * */
    
    /**
     * 实时监控输入多少字符
     * @author Anders Jing
     * */
    TextWatcher mTextWatcher = new TextWatcher() {  
        private CharSequence temp;
        @Override  
        public void onTextChanged(CharSequence s, int start, int before, int count) {  
            // TODO Auto-generated method stub  
             temp = s;
        }
        
        @Override  
        public void beforeTextChanged(CharSequence s, int start, int count,  
                int after) {  
            // TODO Auto-generated method stub 
        }

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			textNumber.setText(""+temp.length());
			sText = editText.getText().toString();	//动态获取写入的字符
		}  
    };
	
	/**
     * 获取/保存设置的值
     * @author Anders Jing
     * */
	public void refreshSettings(){
		SharedPreferences settings = getSharedPreferences(SETTING_NAME, MODE_PRIVATE);
		
		if(isfirstStart){	//如果第一次启动，获取上次设置的值
			settingActivity.mfontsize = settings.getInt("mfontsize", 30);		//获取设置的字体大小
	        settingActivity.mfontstyle = settings.getInt("mfontstyle", 0);		//获取设置的字体格式
	        settingActivity.mfontcolor = settings.getInt("mfontcolor", 19);		//获取设置的字体颜色
	        settingActivity.mbackgroundcolor = settings.getInt("mbackgroundcolor", 20);//获取设置的背景颜色
        	
	        isfirstStart = false;
        }
		//设置
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("mfontsize", settingActivity.mfontsize);
        editor.putInt("mfontstyle", settingActivity.mfontstyle);
        editor.putInt("mfontcolor", settingActivity.mfontcolor);
        editor.putInt("mbackgroundcolor", settingActivity.mbackgroundcolor);        
        editor.commit();
        
        //赋值
        fontsize = settingActivity.getfontsize();
		fontstyle = settingActivity.getfontstyle();
		fontcolor = settingActivity.getfontcolor();
		backgroundcolor = settingActivity.getbackgroudcolor();
		
    }
    
    /**
     * 返回当前年份
     * @return
     */
    public static String getyear(){
    	return year;
    }
    
    /**
     * 返回当前月份
     * @return
     */
    public static String getmonth(){
    	return month;
    }
    
    /**
     * 返回当前天
     * @return
     */
    public static String getday(){
    	return day;
    }
    
    /**
     * 返回当前小时
     * @return
     */
    public static String gethour(){
    	return hour;
    }
    
    /**
     * 返回当前分钟
     * @return
     */
    public static String getminute(){
    	return minute;
    }
    
    /**
     * 返回当前秒
     * @return
     */
    public static String getsecond(){
    	return second;
    }

}
