package com.kienle.monxaovn;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.kienle.monxaovn.adapter.MonXaoAdapter;
import com.kienle.monxaovn.entity.MonXao;
import com.kienle.monxaovn.util.DialogUtils;
import com.kienle.monxaovn.util.JsonUtil;
import com.kienle.monxaovn.util.NetworkUtils;

public class MainActivity extends Activity implements OnItemClickListener {
	public static final String MON_XAO = "mon_xao";
	private ListView mLvMonXao;
	private MonXaoAdapter mMonXaoAdapter;
	private ProgressDialog mProgressDialog;
	private List<MonXao> mMonXaos;
	private AdView mAdView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		if (!NetworkUtils.isNetworkOnline(MainActivity.this)) {
			DialogUtils.showSettingsAlert(MainActivity.this, R.string.wifi_setting_title,
                    R.string.wifi_setting_message, android.provider.Settings.ACTION_WIFI_SETTINGS);
			return;
		}
		
		mProgressDialog = DialogUtils.createProgressDialog(this, "Loading ...");
		
		mLvMonXao = (ListView) findViewById(R.id.lvMonXao);
		mLvMonXao.setOnItemClickListener(this);
		
		GetMonXaoContentTask getMonXaoContentTask = new GetMonXaoContentTask();
		getMonXaoContentTask.execute();
		
		mAdView = new AdView(this);
//        mAdView.setAdUnitId(getResources().getString(R.string.ad_unit_id));
        mAdView.setAdSize(AdSize.BANNER);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        layout.addView(mAdView, layout.getChildCount(), params);
        mAdView.loadAd(new AdRequest.Builder().build());
	}
	
	private class GetMonXaoContentTask extends AsyncTask<Void, Void, List<MonXao>> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog.setMessage("Loading data ...");
			if (!mProgressDialog.isShowing()) {
				mProgressDialog.show();
			}
		}
		
        @Override
        protected List<MonXao> doInBackground(Void... params) {
			String json = JsonUtil.getJsonContent(MainActivity.this);
			return JsonUtil.parseJsonToEntity(json);
        }

        @Override
        protected void onPostExecute(List<MonXao> result) {        	
            super.onPostExecute(result);
            
            if (mProgressDialog.isShowing()) {
            	mProgressDialog.dismiss();
            }
            
            if (result != null) {
            	mMonXaos = result;
            	mMonXaoAdapter = new MonXaoAdapter(MainActivity.this, mMonXaos);
        		mLvMonXao.setAdapter(mMonXaoAdapter);
            } else {
            	Toast.makeText(MainActivity.this, "Get content error!", Toast.LENGTH_SHORT).show();
            }
        }
        
        @Override
        protected void onCancelled() {
        	super.onCancelled();
        	
        	if (mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
        }
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		MonXao monXao = mMonXaos.get(position);
		Intent intent = new Intent(MainActivity.this, MonXaoDetailActivity.class);
		intent.putExtra(MON_XAO, monXao);
		startActivity(intent);
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showDialogConfirmExit();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void showDialogConfirmExit() {
        DialogUtils.createConfirmExistDialog(this, confirmExitListenner, R.string.confirm_exit);
    }
    
    DialogInterface.OnClickListener confirmExitListenner = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            moveTaskToBack(true);
            finish();
        }
    };
}
