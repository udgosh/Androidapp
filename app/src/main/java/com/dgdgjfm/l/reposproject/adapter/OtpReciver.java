package com.dgdgjfm.l.reposproject.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtpReciver extends BroadcastReceiver {

    private OtpReciverListener otpReciverListener;

    public OtpReciver() {
    }
   public  void  inItListener(OtpReciverListener otpReciverListener){
        this.otpReciverListener=otpReciverListener;
   }
    @Override
    public void onReceive(Context context, Intent intent) {
     if(SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())){
        Bundle bundle=intent.getExtras();
        if(bundle!=null) {
            Status status = (Status) bundle.get(SmsRetriever.EXTRA_STATUS);
            if (status != null) {
                switch (status.getStatusCode()) {
                    case CommonStatusCodes
                            .SUCCESS:
                        String message = (String) bundle.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                        if (message != null) {
                            Pattern pattern = Pattern.compile("\\d{4}");
                            Matcher matcher = pattern.matcher(message);
                            if (matcher.find()) {
                                String myOtp = matcher.group(0);
                                if (this.otpReciverListener != null) {
                                    this.otpReciverListener.onOtpSuccess(myOtp);

                                } else {
                                    if (this.otpReciverListener != null) {
                                        this.otpReciverListener.onOtpTimeOut();
                                    }
                                }
                            }
                        }
                        break;
                    case CommonStatusCodes.TIMEOUT:
                        if (this.otpReciverListener != null) {
                            this.otpReciverListener.onOtpTimeOut();
                        }
                        break;
                }
            }
          }
        }
    }
    public  interface OtpReciverListener{
        void onOtpSuccess(String otp);
        void onOtpTimeOut();
    }
}
