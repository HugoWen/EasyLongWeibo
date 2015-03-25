package com.tutor.easychangweibo;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;



/**
 * 关于
 */
@SuppressLint({ "ParserError", "ParserError", "ParserError", "ParserError" })
public class aboutActivity extends Activity{

	private Button btnReturn;
	private Button btnConcern;
	private Button btnHelp;
	private Button btnSendEmail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setContentView(R.layout.about);
		
		
        
		btnReturn = (Button) findViewById(R.id.btnReturn);
		btnReturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
		
		btnConcern = (Button) findViewById(R.id.btnFollow);
		btnConcern.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("http://weibo.com/happytutor");
				Intent uriIntent  = new Intent(Intent.ACTION_VIEW,uri);
				startActivity(uriIntent);				

			}
		});
		
		
		btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
		btnSendEmail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Uri uri = Uri.parse("mailto:happytutor@gmail.com");   
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
				startActivity(emailIntent);
			}
		});
		
		btnHelp = (Button) findViewById(R.id.btnHelp);
		btnHelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//显示关于的信息
        		Intent intent = new Intent();
        		intent.setClass(aboutActivity.this, helpActivity.class);
        		startActivity(intent);
			}
		});
		
		//按钮点击效果
        TouchShow mTouchShow = new TouchShow();
        btnReturn.setOnTouchListener(mTouchShow);
        btnConcern.setOnTouchListener(mTouchShow);
        btnSendEmail.setOnTouchListener(mTouchShow);
        btnHelp.setOnTouchListener(mTouchShow);
	}
}
