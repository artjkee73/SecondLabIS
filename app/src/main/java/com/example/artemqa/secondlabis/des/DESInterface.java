package com.example.artemqa.secondlabis.des;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Class to provide a simple user interface to the DES algorithm.
 */
public class DESInterface {
    private static final String DES_KEY = "12345678";
    private static final String DES_IV = "87654321";
    private static final boolean ENCRYPT_MODE = true;
    private static final boolean DECRYPT_MODE = false;

    /**
     * Runs DES in OFB mode on input @param blocks using @param key with appropriate output
     * displayed.
     */

    public byte[] encrypt(byte[] arrayToEncrypt) {

        long[] blocks = splitInputIntoBlocks(arrayToEncrypt);
        long[] encryptArrayBlocks = runOFB(blocks, getKey(DES_KEY), getKey(DES_IV), ENCRYPT_MODE);
        return combiningBlocsToByteArray(encryptArrayBlocks);
    }

    public byte[] decrypt(byte[] arrayToDecrypt) {

        long[] blocks = splitInputIntoBlocks(arrayToDecrypt);
        long[] encryptArrayBlocks = runOFB(blocks, getKey(DES_KEY), getKey(DES_IV), DECRYPT_MODE);
        return combiningBlocsToByteArray(encryptArrayBlocks);
    }

    private long[] runOFB(long[] blocks, long key, long IV, boolean encryptMode) {
        DES des = new DES();
        long[] cipherTexts, plainTexts;

        if (encryptMode) {
            cipherTexts = des.OFBEncrypt(blocks, key, IV);
            return cipherTexts;
        } else {
            plainTexts = des.OFBDecrypt(blocks, key, IV);
            return plainTexts;
        }
    }

    private static long getKey(String keyStr) {
        byte[] keyBytes;
        long key64 = 0;

        keyBytes = keyStr.getBytes();

        for (byte keyByte : keyBytes) {
            key64 <<= 8;
            key64 |= keyByte;
        }

        return key64;
    }

    private long[] splitInputIntoBlocks(byte[] input) {
        long blocks[] = new long[input.length / 8 + 1];

        for (int i = 0, j = -1; i < input.length; i++) {
            if (i % 8 == 0)
                j++;
            blocks[j] <<= 8;
            blocks[j] |= input[i];
        }

        return blocks;
    }

    private byte[] combiningBlocsToByteArray(long[] inputBlocks) {

        byte[] byteArray = new byte[inputBlocks.length * 8];

        int j = 0;
        for (long block : inputBlocks) {
            byte[] blockArray = ByteBuffer.allocate(8).putLong(block).array();
            for (int i = 0; i <blockArray.length;i++,j++) {
                byteArray[j] = blockArray[i];
            }

        }
        return byteArray;
    }

}