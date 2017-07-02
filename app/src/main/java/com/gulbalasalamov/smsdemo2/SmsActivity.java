package com.gulbalasalamov.smsdemo2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity {

    private static final int SMS_REQUEST = 1;
    Button btn_gonder;
    EditText mesaj, numara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        btn_gonder = (Button) findViewById(R.id.button_gonder);
        numara = (EditText) findViewById(R.id.numara);
        mesaj = (EditText) findViewById(R.id.mesaj);

        if (!izinVarMi()) {
            kullaniciIzni();
        }

        try {
            btn_gonder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gonderSMS();
                    Toast.makeText(SmsActivity.this, "Mesajınız başarıyla gönderildi. ", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Mesaj gönderilemedi. ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void gonderSMS() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(numara.getText().toString(), null, mesaj.getText().toString(), null, null);
    }

    private void kullaniciIzni() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_REQUEST);
    }

    private boolean izinVarMi() {
        int resultSms = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (resultSms == PackageManager.PERMISSION_GRANTED)
            return true;
        return false;
    }


}

