package com.tutor.easychangweibo;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * 按钮点击效果
 * @author Anders Jing
 * */
public	class TouchShow implements Button.OnTouchListener{

		 public boolean onTouch(View v, MotionEvent event) {
		   if(event.getAction() == MotionEvent.ACTION_DOWN){
			   v.setBackgroundColor(0Xff00A600);
			  
		   }else if(event.getAction() == MotionEvent.ACTION_UP){
//			   try {
//					Thread.sleep(50);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			   
			   v.setBackgroundColor(0Xff6CA6CD);
		   }
		   return false;
	   }
		
	}