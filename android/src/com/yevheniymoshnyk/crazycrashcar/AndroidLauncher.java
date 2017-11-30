package com.yevheniymoshnyk.crazycrashcar;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdView;
import com.yevheniymoshnyk.crazycrashcar.CrazyCrashCar;
import com.yevheniymoshnyk.crazycrashcar.utils.GameCallback;

public class AndroidLauncher extends AndroidApplication {

	private RelativeLayout mainView;

	private AdView bannerView;
	private ViewGroup bannerContainer;
	private RelativeLayout.LayoutParams bannerParams;


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		mainView = new RelativeLayout(this);
		setContentView(mainView);

		View gameView = initializeForView(new CrazyCrashCar(gameCallback));
		mainView.addView(gameView);

		bannerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		bannerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		bannerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bannerContainer = new LinearLayout(this);

		mainView.addView(bannerContainer, bannerParams);
		bannerContainer.setVisibility(View.GONE);


	}

	private GameCallback gameCallback = new GameCallback() {
		@Override
		public void sendMessage(int message) {

		}
	};
}
