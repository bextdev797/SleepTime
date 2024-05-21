package com.bextdev.sleeptime;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.TimeUtils;
import java.util.concurrent.TimeUnit;

public class SleepTime extends AndroidNonvisibleComponent {
  private Activity activity;
  private Context context;

  public SleepTime(ComponentContainer container) {
    super(container.$form());
    this.activity = container.$context();
    this.context = container.$context();
  }

  @SimpleFunction
  public void SetSleepTime(String message, int hour, int minute) {
    // Taken from here: https://stackoverflow.com/questions/8664670/how-to-launch-alarm-clock-screen-using-intent-in-android
    Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
    i.putExtra(AlarmClock.EXTRA_MESSAGE, message);
    i.putExtra(AlarmClock.EXTRA_HOUR, hour);
    i.putExtra(AlarmClock.EXTRA_MINUTES, minute);
    form.startActivity(i);
  }

  @SimpleProperty
  public int SLEEP_TIME_HOUR_6() {
    return 6;
  }

  @SimpleProperty
  public int SLEEP_TIME_HOUR_7() {
    return 7;
  }

  @SimpleProperty
  public int SLEEP_TIME_HOUR_8() {
    return 8;
  }

  @SimpleProperty
  public int SLEEP_TIME_HOUR_9() {
    return 9;
  }

  @SimpleProperty
  public int SLEEP_TIME_HOUR_10() {
    return 10;
  }

  @SimpleProperty
  public int SLEEP_TIME_HOUR_11() {
    return 11;
  }

  @SimpleProperty
  public int SLEEP_TIME_HOUR_12() {
    return 12;
  }

  @SimpleProperty
  public int SLEEP_TIME_MINUTE_5(){
    return 5;
  }

  @SimpleProperty
  public int SLEEP_TIME_MINUTE_10(){
    return 10;
  }

  @SimpleProperty
  public int SLEEP_TIME_MINUTE_15(){
    return 15;
  }

  @SimpleProperty
  public int SLEEP_TIME_MINUTE_20(){
    return 20;
  }

  @SimpleProperty
  public int SLEEP_TIME_MINUTE_25(){
    return 25;
  }

  
  @SimpleProperty
  public int SLEEP_TIME_MINUTE_30(){
    return 30;
  }

  @SimpleProperty
  public int SLEEP_TIME_MINUTE_35(){
    return 35;
  }

  @SimpleProperty
  public int SLEEP_TIME_MINUTE_40(){
    return 40;
  }

  @SimpleProperty
  public int SLEEP_TIME_MINUTE_45(){
    return 45;
  }

  @SimpleProperty
  public int SLEEP_TIME_MINUTE_50(){
    return 50;
  }

  @SimpleProperty
  public int SLEEP_TIME_MINUTE_55(){
    return 55;
  }

  //Taken from: Mahmoud's Extension: https://github.com/MahmoudHooda2019/ScreenOffTimeout---MIT_AppInventor/blob/main/src/com/aemo/screenofftime/ScreenOffTime.java
  @SimpleFunction
  public void SetScreenTimeout(int time){
    boolean value = false;
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
      value = Settings.System.canWrite(context.getApplicationContext());
      if(value){
        Settings.System.putInt(activity.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, time);
        Set();
      } else {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        activity.startActivity(intent);
      }
    } else {
       Settings.System.putInt(activity.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, time);
    }
  }

  @SimpleProperty
  public int TIME_OFF_15s(){
    return 15000;
  }

  @SimpleProperty
  public int TIME_OFF_30s(){
    return 30000;
  }

  @SimpleProperty
  public int TIME_OFF_1m(){
    return 60000;
  }

  @SimpleProperty
  public int TIME_OFF_10m(){
    return 600000;
  }

  @SimpleProperty
  public int TIME_OFF_30m(){
    return 1800000;
  }

  @SimpleFunction
  public int MinutesToMillis(int minutes){
    return (int) TimeUnit.MINUTES.toMillis(minutes);
  }

  @SimpleFunction
  public int SecondsToMillis(int seconds){
    return (int) TimeUnit.SECONDS.toMillis(seconds);
  }

  @SimpleEvent
  public void Set(){
    EventDispatcher.dispatchEvent(this, "Set");
  }
}