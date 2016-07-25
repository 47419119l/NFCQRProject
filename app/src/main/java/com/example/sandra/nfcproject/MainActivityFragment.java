package com.example.sandra.nfcproject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentResult;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    ImageButton btnnfc;
    ImageButton btnqr;
    TextView result;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        btnnfc = (ImageButton) rootView.findViewById(R.id.nfc);
        btnqr = (ImageButton) rootView.findViewById(R.id.qr);
        result = (TextView)rootView.findViewById(R.id.result);
        configButtonNFC();
        configButtonQR();


        return rootView;
    }

    /**
     * Metode per la configuració del butó NFC
     */
    public void configButtonNFC(){
        btnnfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * Metode per la configuració del butó QR
     */
    public void configButtonQR(){

        btnqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    startActivityForResult(intent, 0);
                } catch (ActivityNotFoundException exception) {

                }
            }
        });
    }


    /**
     * Metode que s'executara al sortir el resultat de l bacecore reader.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == 0) && (resultCode == -1)) {

            updateUITextViews(data.getStringExtra("SCAN_RESULT"));

        } else {
            Toast.makeText(getContext(), "No s'ha pogut lleguir cap codi Qr", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Metode per gestional el resultat.
     * @param scanResult
     */
    private void handleResult(IntentResult scanResult) {
        if (scanResult != null) {
            updateUITextViews(scanResult.getContents());
        } else {
            Toast.makeText(getContext(), "No s'ha pogut lleguir cap codi Qr", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Metode per mostrar el resultat llegit per el codiQr.
     * @param scan_result
     */
    private void updateUITextViews(String scan_result) {
        result.setText(scan_result);
    }
}
