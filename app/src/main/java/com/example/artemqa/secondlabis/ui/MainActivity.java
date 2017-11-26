package com.example.artemqa.secondlabis.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.artemqa.secondlabis.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAboutAlg,btnAboutProg,btnEncodeFile,btnDecodeFile,btnEncodeStr,btnDecodeStr;
    EditText etEncryptStr,etDecryptStr;
    private final static String ORIGINAL_TEXT_FILE_NAME = "textFile.txt";
    private final static String ENCRYPT_TEXT_FILE_NAME = "encryptTextFile.txt";
    private final static String DECRYPT_TEXT_FILE_NAME = "decryptTextFile.txt";
    private final static String ORIGINAL_IMG_FILE_NAME = "imgFile.png";
    private final static String ENCRYPT_IMG_FILE_NAME = "encryptImgFile.png";
    private final static String DECRYPT_IMG_FILE_NAME = "decryptImgFile.png";
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
                encryptFile(ORIGINAL_TEXT_FILE_NAME);
                break;

            case R.id.btn_decrypt_file_main_a:
                decryptFile(ENCRYPT_TEXT_FILE_NAME);
                break;

            case R.id.btn_encode_string_main_a:
                encryptString(etEncryptStr.getText().toString());
                break;

            case R.id.btn_decode_string_main_a:
                decryptString(etDecryptStr.getText().toString());
                break;

        }
    }



    private void encryptFile(String fileName) {
        // описать шифрование файла входного в цикл в файл  ENCRYPT_TEXT_FILE_NAME
    }
    private void decryptFile(String fileName) {
        // описать дешифрование зашифрованного файла ENCRYPT_TEXT_FILE_NAME в файл DECRYPT_TEXT_FILE_NAME
    }
    private void encryptString(String strForEncrypt) {
        // описать шифрование строки и сохрение результатов в etDecryptStr.getText().toString()
    }

    private void decryptString(String strForDecrypt) {
        // описать дешифрование строки и сохранение результатов в etEncryptStr.getText().toString()
    }




}
