package com.example.artemqa.secondlabis.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.artemqa.secondlabis.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAboutAlg,btnAboutProg,btnEncodeFile,btnDecodeFile,btnEncodeStr,btnDecodeStr;
    EditText etEncryptStr,etDecryptStr;
    private final static String LOG = "MyLog";
    private final static int REQUEST_CODE_CHOOSE_FILE_ENCRYPT = 0;
    private final static int REQUEST_CODE_CHOOSE_FILE_DECRYPT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAboutAlg =  findViewById(R.id.btn_about_algorithm_main_a);
        btnAboutAlg.setOnClickListener(this);
        btnAboutProg = findViewById(R.id.btn_about_program_main_a);
        btnAboutProg.setOnClickListener(this);
        btnEncodeFile = findViewById(R.id.btn_encrypt_file_main_a);
        btnEncodeFile.setOnClickListener(this);
        btnDecodeFile = findViewById(R.id.btn_decrypt_file_main_a);
        btnDecodeFile.setOnClickListener(this);
        btnEncodeStr = findViewById(R.id.btn_encode_string_main_a);
        btnEncodeStr.setOnClickListener(this);
        btnDecodeStr = findViewById(R.id.btn_decode_string_main_a);
        btnDecodeStr.setOnClickListener(this);

        etEncryptStr = findViewById(R.id.et_encrypt_string_main_a);
        etDecryptStr = findViewById(R.id.et_decrypt_string_main_a);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_about_algorithm_main_a:
                intent = new Intent(this,AboutAlgorithmActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_about_program_main_a:
                intent = new Intent(this,AboutProgramActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_encrypt_file_main_a:
                chooseFileEncrypt();
                break;

            case R.id.btn_decrypt_file_main_a:
                chooseFileDecrypt();
                break;

            case R.id.btn_encode_string_main_a:
                encryptString(etEncryptStr.getText().toString());
                break;

            case R.id.btn_decode_string_main_a:
                decryptString(etDecryptStr.getText().toString());
                break;

        }
    }


    private void encryptFile(Uri uriChooseFile ) {
            try {
                FileInputStream fis = new FileInputStream(new File(uriChooseFile.getPath()));
                Log.d(LOG,"Path to choose file" + uriChooseFile.getPath().toString() );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    private void chooseFileEncrypt() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_FILE_ENCRYPT);
    }

    private void chooseFileDecrypt() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_FILE_DECRYPT);
    }

    private void decryptFile(Uri uriChooseFile){
        Log.d(LOG,"decryptFile data :" + uriChooseFile.toString());
    }
    private void encryptString(String strForEncrypt) {
        // описать шифрование строки и сохрение результатов в etDecryptStr.getText().toString()
    }

    private void decryptString(String strForDecrypt) {
        // описать дешифрование строки и сохранение результатов в etEncryptStr.getText().toString()
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uriChooseFile;
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE_CHOOSE_FILE_ENCRYPT:
                    uriChooseFile = data.getData();
                    encryptFile(uriChooseFile);
                    Log.d(LOG,"Uri choose encrypting file " + uriChooseFile.toString());
                    break;
                case REQUEST_CODE_CHOOSE_FILE_DECRYPT:
                    uriChooseFile = data.getData();
                    decryptFile(uriChooseFile);
                    Log.d(LOG,"Uri choose decrypting file " + uriChooseFile.toString());
                    break;
            }
        }
    }

}
