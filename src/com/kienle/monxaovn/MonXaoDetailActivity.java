package com.kienle.monxaovn;

import java.io.InputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import com.kienle.monxaovn.entity.MonXao;
import com.kienle.monxaovn.util.DialogUtils;

public class MonXaoDetailActivity extends Activity {
	
	private TouchImageView mIvDetail;
	private ProgressDialog mProgressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_mon_xao_detail);
		
		mIvDetail = (TouchImageView) findViewById(R.id.iv_detail);
		mProgressDialog = DialogUtils.createProgressDialog(this, "Loading ...");
		
		Intent intent = getIntent();
		MonXao monXao = (MonXao) intent.getSerializableExtra(MainActivity.MON_XAO);
		
		new DownloadImageTask(mIvDetail)
        .execute(monXao.getImage());
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		  ImageView bmImage;

		  public DownloadImageTask(ImageView bmImage) {
		      this.bmImage = bmImage;
		  }

		  @Override
		protected void onPreExecute() {
			mProgressDialog.show();
			super.onPreExecute();
		}
		  
		  protected Bitmap doInBackground(String... urls) {
		      String urldisplay = urls[0];
		      Bitmap mIcon11 = null;
		      try {
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		      } catch (Exception e) {
		          Log.d("KienLT", "DownloadImageTask error: " + e.getMessage());
		          e.printStackTrace();
		      }
		      return mIcon11;
		  }

		  protected void onPostExecute(Bitmap result) {
		      bmImage.setImageBitmap(result);
		      if (mProgressDialog.isShowing()) {
		    	  mProgressDialog.dismiss();
		      }
		  }
		}
	
}
