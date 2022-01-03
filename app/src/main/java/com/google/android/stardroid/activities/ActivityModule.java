package com.google.android.stardroid.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.stardroid.R;
import com.google.android.stardroid.activities.dialogs.EulaDialogFragment;
import com.google.android.stardroid.activities.dialogs.HelpDialogFragment;
import com.google.android.stardroid.activities.dialogs.LocationPermissionRationaleFragment;
import com.google.android.stardroid.activities.dialogs.MultipleSearchResultsDialogFragment;
import com.google.android.stardroid.activities.dialogs.NoSearchResultsDialogFragment;
import com.google.android.stardroid.activities.dialogs.NoSensorsDialogFragment;
import com.google.android.stardroid.activities.dialogs.TimeTravelDialogFragment;
import com.google.android.stardroid.activities.dialogs.WhatsNewDialogFragment;
import com.google.android.stardroid.util.MiscUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * Dagger module
 * Created by johntaylor on 3/29/16.
 */
@Module
@InstallIn(ActivityComponent.class)
public class ActivityModule {  // TODO Rename me
  private static final String TAG = MiscUtil.getTag(DynamicStarMapModule.class);

  @Provides
  EulaDialogFragment provideEulaDialogFragment() {
    return new EulaDialogFragment();
  }

  @Provides
  TimeTravelDialogFragment provideTimeTravelDialogFragment() {
    return new TimeTravelDialogFragment();
  }

  @Provides
  HelpDialogFragment provideHelpDialogFragment() {
    return new HelpDialogFragment();
  }

  @Provides
  NoSearchResultsDialogFragment provideNoSearchResultsDialogFragment() {
    return new NoSearchResultsDialogFragment();
  }

  @Provides
  MultipleSearchResultsDialogFragment provideMultipleSearchResultsDialogFragment() {
    return new MultipleSearchResultsDialogFragment();
  }

  @Provides
  NoSensorsDialogFragment provideNoSensorsDialogFragment() {
    return new NoSensorsDialogFragment();
  }

  @Provides
  @Named("timetravel")
  MediaPlayer provideTimeTravelNoise(Activity activity) {
    return MediaPlayer.create(activity, R.raw.timetravel);
  }

  @Provides
  @Named("timetravelback")
  MediaPlayer provideTimeTravelBackNoise(Activity activity) {
    return MediaPlayer.create(activity, R.raw.timetravelback);
  }

  @Provides
  @Named("flash")
  Animation provideTimeTravelFlashAnimation(Activity activity) {
    return AnimationUtils.loadAnimation(activity, R.anim.timetravelflash);
  }

  @Provides
  @Named("fadeout")
  Animation provideFadeoutAnimation(Activity activity) {
    return AnimationUtils.loadAnimation(activity, R.anim.fadeout);
  }

  @Provides
  Handler provideHandler() {
    return new Handler();
  }

  @Provides
  LocationPermissionRationaleFragment provideLocationFragment() {
    return new LocationPermissionRationaleFragment();
  }

  @Provides
  WhatsNewDialogFragment provideWhatsNewDialogFragment(Activity activity) {
    WhatsNewDialogFragment whatsNewDialogFragment = new WhatsNewDialogFragment();
    whatsNewDialogFragment.setCloseListener((SplashScreenActivity) activity);
    return whatsNewDialogFragment;
  }

  @Provides
  FragmentManager provideFragmentManager(Activity activity) {
    return activity.getFragmentManager();
  }
}

