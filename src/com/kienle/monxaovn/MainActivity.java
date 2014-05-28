package com.kienle.monxaovn;

import com.kienle.monxaovn.adapter.MonXaoAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ListView mLvMonXao;
	private MonXaoAdapter mMonXaoAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
	}
}
