package com.tutor.easychangweibo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.view.KeyEvent;

/**
 * 设置
 * @author wenhan
 */

@SuppressLint("ParserError")
public class settingActivity extends PreferenceActivity{


	public static ListPreference fontsize;
	public static ListPreference fontstyle;
	public static ListPreference fontcolor;
	public static ListPreference backgroundcolor;
	static int mfontsize = 30;		//设置默认的字体大小
	static int mfontstyle = 0;		//设置默认的字体格式
	static int mfontcolor = 19;		//设置默认的字体颜色
	static int mbackgroundcolor = 20;//设置默认的背景颜色
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.settting);
		
		fontsize = (ListPreference) this.findPreference("fontsize");
		fontstyle = (ListPreference) this.findPreference("fontstyle");
		fontcolor = (ListPreference) this.findPreference("fontcolor");
		backgroundcolor = (ListPreference) this.findPreference("backgroundcolor");
		
		
		//字体大小更改保存设置
		fontsize.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				
				//字体大小
				fontsize.setValue(newValue.toString());
				mfontsize = Integer.parseInt(fontsize.getValue());
				return false;
			}
		});
		
		
		
		//字体格式更改保存设置
		fontstyle.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				
				//字体格式
				fontstyle.setValue(newValue.toString());
				mfontstyle = Integer.parseInt(fontstyle.getValue());
				return false;
			}
		});
		
		
		
		//字体颜色更改保存设置
		fontcolor.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				
				//字体颜色
				fontcolor.setValue(newValue.toString());
				mfontcolor = Integer.parseInt(fontcolor.getValue());
				return false;
			}
		});
		
		

		//背景颜色更改保存设置
		backgroundcolor.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				
				//背景颜色
				backgroundcolor.setValue(newValue.toString());
				mbackgroundcolor = Integer.parseInt(backgroundcolor.getValue());
				return false;
			}
		});
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // 如果是返回键,直接返回到桌面
	        if(keyCode == KeyEvent.KEYCODE_BACK){
	        	startActivity(new Intent(this, MainActivity.class));
	        }

	        return super.onKeyDown(keyCode, event);
	    }
	
	
	/**
	 * 获取设置的字体大小
	 * @return 字体大小
	 * @author wenhan
	 */
	public static int getfontsize() {
		return mfontsize;
		
	}
	
	/**
	 * 获取设置的字体格式
	 * @return 字体格式
	 * @author wenhan
	 */
	public static Typeface getfontstyle(){
//		return mfontstyle;
		Typeface style;
		switch (mfontstyle) {
		case 0:
			style = Typeface.DEFAULT;
			break;
		case 1:
			style = Typeface.DEFAULT_BOLD;
			break;
		case 2:
			style = Typeface.MONOSPACE;
			break;
		case 3:
			style = Typeface.SANS_SERIF;
			break;
		case 4:
			style = Typeface.SERIF;
			break;
			
		default:
			style = Typeface.DEFAULT;
			break;
		}
		return style;
	}
	
	/**
	 * 获取设置的字体颜色
	 * @return 字体颜色
	 * @author wenhan
	 */
	public static int[] getfontcolor(){
		int [] colorArry = {255,255,255};
		//设置画布背景颜色
		switch (mfontcolor) {
		case 0:
			//胭脂 #9D2932
			colorArry[0] = 157;
			colorArry[1] = 41;
			colorArry[2] = 50;
			break;
		case 1:
			//牙白 #efdeb0
			colorArry[0] = 239;
			colorArry[1] = 222;
			colorArry[2] = 176;
			break;
		case 2:
			//竹青 #789262
			colorArry[0] = 120;
			colorArry[1] = 146;
			colorArry[2] = 98;
			break;
		case 3:
			//黛 #494166
			colorArry[0] = 73;
			colorArry[1] = 65;
			colorArry[2] = 102;
			break;
		case 4:
			//驼色 #a88462
			colorArry[0] = 168;
			colorArry[1] = 132;
			colorArry[2] = 98;
			break;
		case 5:
			//秋香色 R:217  G:182 B:18    #b9b612
			colorArry[0] = 217;
			colorArry[1] = 182;
			colorArry[2] = 18;
			break;
		case 6:
			//靛青 R:23  G:124 B:176    #177cb0
			colorArry[0] = 23;
			colorArry[1] = 124;
			colorArry[2] = 176;
			break;
		case 7:
			//荼白 R:243  G:248 B:241    #f3f8f1
			colorArry[0] = 243;
			colorArry[1] = 248;
			colorArry[2] = 241;
			break;
		case 8:
			//鸦青 R:66  G:75 B:80    #424b50
			colorArry[0] = 66;
			colorArry[1] = 75;
			colorArry[2] = 80;
			break;
		case 9:
			//檀 R:179  G:109 B:970    #b36d61
			colorArry[0] = 179;
			colorArry[1] = 109;
			colorArry[2] = 97;
			break;
		case 10:
			//赤 R:195  G:39 B:43    #c3272b
			colorArry[0] = 195;
			colorArry[1] = 39;
			colorArry[2] = 43;
			break;
		case 11:
			//綰 R:169  G:129 B:117    #a98175
			colorArry[0] = 169;
			colorArry[1] = 129;
			colorArry[2] = 117;
			break;
		case 12:
			//水绿 R:212  G:242 B:232    #d4f2e8
			colorArry[0] = 212;
			colorArry[1] = 242;
			colorArry[2] = 232;
			break;
		case 13:
			//炎 R:255  G:51 B:0    #ff3300
			colorArry[0] = 255;
			colorArry[1] = 51;
			colorArry[2] = 0;
			break;
		case 14:
			//妃色 R:237  G:287 B:254    #ed5736
			colorArry[0] = 237;
			colorArry[1] = 287;
			colorArry[2] = 254;
			break;
		case 15:
			//黎 R:118  G:102 B:77    #76664d
			colorArry[0] = 118;
			colorArry[1] = 102;
			colorArry[2] = 77;
			break;
		case 16:
			//艾青 R:163  G:226 B:197    #a3e2c5
			colorArry[0] = 163;
			colorArry[1] = 226;
			colorArry[2] = 197;
			break;
		case 17:
			//黛蓝 R:65  G:80 B:101    #415065
			colorArry[0] = 65;
			colorArry[1] = 80;
			colorArry[2] = 101;
			break;
		case 18:
			//月白 R:215  G:236 B:241    #d7ecf1
			colorArry[0] = 215;
			colorArry[1] = 236;
			colorArry[2] = 241;
			break;
		case 19:
			//纯黑
			colorArry[0] = 0;
			colorArry[1] = 0;
			colorArry[2] = 0;
			break;
		case 20:
			//纯白
			colorArry[0] = 255;
			colorArry[1] = 255;
			colorArry[2] = 255;
			break;
		default:
//			canvas.drawARGB(255, 255, 255, 255);
			colorArry[0] = 255;
			colorArry[1] = 255;
			colorArry[2] = 255;
			break;
			
		}
		
		return colorArry;
	}
	
	/**
	 * 获取设置的背景颜色
	 * @return 背景颜色
	 * @author wenhan
	 */
	@SuppressLint({ "ParserError", "ParserError" })
	public static int[] getbackgroudcolor(){
		int [] colorArry = {255,255,255};
		//设置画布背景颜色
		switch (mbackgroundcolor) {
		case 0:
			//胭脂 #9D2932
			colorArry[0] = 157;
			colorArry[1] = 41;
			colorArry[2] = 50;
			break;
		case 1:
			//牙白 #efdeb0
			colorArry[0] = 239;
			colorArry[1] = 222;
			colorArry[2] = 176;
			break;
		case 2:
			//竹青 #789262
			colorArry[0] = 120;
			colorArry[1] = 146;
			colorArry[2] = 98;
			break;
		case 3:
			//黛 #494166
			colorArry[0] = 73;
			colorArry[1] = 65;
			colorArry[2] = 102;
			break;
		case 4:
			//驼色 #a88462
			colorArry[0] = 168;
			colorArry[1] = 132;
			colorArry[2] = 98;
			break;
		case 5:
			//秋香色 R:217  G:182 B:18    #b9b612
			colorArry[0] = 217;
			colorArry[1] = 182;
			colorArry[2] = 18;
			break;
		case 6:
			//靛青 R:23  G:124 B:176    #177cb0
			colorArry[0] = 23;
			colorArry[1] = 124;
			colorArry[2] = 176;
			break;
		case 7:
			//荼白 R:243  G:248 B:241    #f3f8f1
			colorArry[0] = 243;
			colorArry[1] = 248;
			colorArry[2] = 241;
			break;
		case 8:
			//鸦青 R:66  G:75 B:80    #424b50
			colorArry[0] = 66;
			colorArry[1] = 75;
			colorArry[2] = 80;
			break;
		case 9:
			//檀 R:179  G:109 B:970    #b36d61
			colorArry[0] = 179;
			colorArry[1] = 109;
			colorArry[2] = 97;
			break;
		case 10:
			//赤 R:195  G:39 B:43    #c3272b
			colorArry[0] = 195;
			colorArry[1] = 39;
			colorArry[2] = 43;
			break;
		case 11:
			//綰 R:169  G:129 B:117    #a98175
			colorArry[0] = 169;
			colorArry[1] = 129;
			colorArry[2] = 117;
			break;
		case 12:
			//水绿 R:212  G:242 B:232    #d4f2e8
			colorArry[0] = 212;
			colorArry[1] = 242;
			colorArry[2] = 232;
			break;
		case 13:
			//炎 R:255  G:51 B:0    #ff3300
			colorArry[0] = 255;
			colorArry[1] = 51;
			colorArry[2] = 0;
			break;
		case 14:
			//妃色 R:237  G:287 B:254    #ed5736
			colorArry[0] = 237;
			colorArry[1] = 287;
			colorArry[2] = 254;
			break;
		case 15:
			//黎 R:118  G:102 B:77    #76664d
			colorArry[0] = 118;
			colorArry[1] = 102;
			colorArry[2] = 77;
			break;
		case 16:
			//艾青 R:163  G:226 B:197    #a3e2c5
			colorArry[0] = 163;
			colorArry[1] = 226;
			colorArry[2] = 197;
			break;
		case 17:
			//黛蓝 R:65  G:80 B:101    #415065
			colorArry[0] = 65;
			colorArry[1] = 80;
			colorArry[2] = 101;
			break;
		case 18:
			//月白 R:215  G:236 B:241    #d7ecf1
			colorArry[0] = 215;
			colorArry[1] = 236;
			colorArry[2] = 241;
			break;
		case 19:
			//纯黑
			colorArry[0] = 0;
			colorArry[1] = 0;
			colorArry[2] = 0;
			break;
		case 20:
			//纯白
			colorArry[0] = 255;
			colorArry[1] = 255;
			colorArry[2] = 255;
			break;
		default:
//			canvas.drawARGB(255, 255, 255, 255);
			colorArry[0] = 255;
			colorArry[1] = 255;
			colorArry[2] = 255;
			break;
		}
		
		return colorArry;
	}
	
	/**
     * 响应返回键
     * @author Anders Jing
     * */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
    	// TODO Auto-generated method stub
    	int keyCode = event.getKeyCode();
    	if (event.getAction() == KeyEvent.ACTION_DOWN) {
    		if (keyCode == KeyEvent.KEYCODE_BACK) {
    			startActivity(new Intent(this, MainActivity.class));
    			this.finish();
				return true;
			}
		}
    	return super.dispatchKeyEvent(event);
    }
}

