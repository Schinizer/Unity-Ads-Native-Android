package com.schinizer.unityads.sample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindString(R.string.UNITY_ADS_GAMEID)
    String gameID;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);

        UnityAds.initialize(this, gameID, new IUnityAdsListener() {
            @Override
            public void onUnityAdsReady(String s) {
                progressBar.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
                Snackbar.make(frameLayout, "Unity Ads ready!", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onUnityAdsStart(String s) {
            }

            @Override
            public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
                Snackbar.make(frameLayout, "Something went wrong..", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.button)
    void showUnityAds() {
        if (UnityAds.isReady()) {
            UnityAds.show(this);
        }
    }
}
