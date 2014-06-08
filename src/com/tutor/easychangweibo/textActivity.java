package com.tutor.easychangweibo;

/**
 * EditText内容处理类
 * 
 * @author wenhan
 *
 * @官方微博   http://weibo.com/whfallen
 */
public class textActivity {

	private int height;			//EditText内文本的行数
	private String[] weiboContext = new String[1000];			//文本内容数组
	
	/**
	 * 构造函数
	 * EditText内容处理函数
	 * @param wordNum 	每行的字数
	 * @param context 	EditText内容
	 * @author wenhan
	 */
	public textActivity(int wordNum, String context){
		
		String[] textsplit = new String[10000];
		textsplit = context.split("\n");
		
		int i = 0;
		
		for(int j = 0; j < textsplit.length; j++){
			context = textsplit[j];
			if(context.length() > wordNum){
				int k = 0;
				while(k + wordNum <= context.length()){
					
					weiboContext[i++] = context.substring(k, k+wordNum);
					k = k + wordNum;
					
				}
				weiboContext[i++] = context.substring(k, context.length());
				
			}
			else{
				weiboContext[i++] = context;
			}
		}
		
		this.height = i;

		
	}
	 
	/**
	 * 返回EditText内容的高度
	 * @return
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * 返回生成的长微博内容
	 * @return
	 */
	public String[] getContext(){
		return weiboContext;
	}
}
