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
import com.example.artemqa.secondlabis.des.DESInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAboutAlg, btnAboutProg, btnEncodeFile, btnDecodeFile, btnEncodeStr, btnDecodeStr;
    EditText etEncryptStr, etDecryptStr;
    private final static String LOG = "MyLog";
    private final static int REQUEST_CODE_CHOOSE_FILE_ENCRYPT = 0;
    private final static int REQUEST_CODE_CHOOSE_FILE_DECRYPT = 1;
    private final static String CONCAT_ENCRYPT = "ENCRYPTED";
    private final static String CONCAT_DECRYPT = "DECRYPTED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAboutAlg = findViewById(R.id.btn_about_algorithm_main_a);
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
        switch (view.getId()) {
            case R.id.btn_about_algorithm_main_a:
                intent = new Intent(this, AboutAlgorithmActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_about_program_main_a:
                intent = new Intent(this, AboutProgramActivity.class);
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

    private void encryptFile(Uri uriChooseFile) {
        try {
            FileInputStream fis = new FileInputStream(new File(uriChooseFile.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer, 0, fis.available());

            DESInterface desInterface = new DESInterface();
            byte[] encryptedByteArray = desInterface.encrypt(buffer);
            writeEncryptByteArrayToFile(uriChooseFile, encryptedByteArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void decryptFile(Uri uriChooseFile) {
        try {
            FileInputStream fis = new FileInputStream(new File(uriChooseFile.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer, 0, fis.available());

            DESInterface desInterface = new DESInterface();
            byte[] decryptedByteArray = desInterface.encrypt(buffer);
            writeDecryptByteArrayToFile(uriChooseFile, decryptedByteArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void encryptString(String strForEncrypt) {
        byte[] arrayForEncrypting = strForEncrypt.getBytes();
        DESInterface desInterface = new DESInterface();
        byte[] encryptedBytesArray = desInterface.encrypt(arrayForEncrypting);
        etDecryptStr.setText(new String(encryptedBytesArray));
    }

    private void decryptString(String strForDecrypt) {
        byte[] arrayForDecrypting = strForDecrypt.getBytes();
        DESInterface desInterface = new DESInterface();
        byte[] decryptedBytesArray = desInterface.decrypt(arrayForDecrypting);
        etEncryptStr.setText(new String(decryptedBytesArray));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uriChooseFile;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CHOOSE_FILE_ENCRYPT:
                    uriChooseFile = data.getData();
                    encryptFile(uriChooseFile);
                    Log.d(LOG, "Uri choose encrypting file " + uriChooseFile.toString());
                    break;
                case REQUEST_CODE_CHOOSE_FILE_DECRYPT:
                    uriChooseFile = data.getData();
                    decryptFile(uriChooseFile);
                    Log.d(LOG, "Uri choose decrypting file " + uriChooseFile.toString());
                    break;
            }
        }
    }

    private void writeEncryptByteArrayToFile(Uri encryptUriFile, byte[] encryptedByteArray) {

        String forEncryptingPath = encryptUriFile.getPath();
        String forEncryptingFileName = encryptUriFile.getLastPathSegment();
        String encryptedFileName = CONCAT_ENCRYPT + forEncryptingFileName;
        int endDir = forEncryptingPath.length() - forEncryptingFileName.length();
        String bufferStr = forEncryptingPath.substring(0, endDir);
        String encryptedFilePath = bufferStr + encryptedFileName;
        Log.d(LOG, "encryptedFilePath " + encryptedFilePath);

        try (FileOutputStream fos = new FileOutputStream(encryptedFilePath)) {
            fos.write(encryptedByteArray, 0, encryptedByteArray.length);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void writeDecryptByteArrayToFile(Uri decryptUriFile, byte[] encryptedByteArray) {

        String forDecryptingPath = decryptUriFile.getPath();
        String forDecryptingFileName = decryptUriFile.getLastPathSegment();
        String decryptedFileName = CONCAT_DECRYPT + forDecryptingFileName;
        int endDir = forDecryptingPath.length() - forDecryptingFileName.length();
        String bufferStr = forDecryptingPath.substring(0, endDir);
        String decryptedFilePath = bufferStr + decryptedFileName;
        Log.d(LOG, "decryptedFilePath " + decryptedFilePath);

        try (FileOutputStream fos = new FileOutputStream(decryptedFilePath)) {
            fos.write(encryptedByteArray, 0, encryptedByteArray.length);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
