package com.zksn.jilinjiaotong.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 图片处理的工具类
 * @author admin
 *
 */
public class ImageFactory {
	 public static void compressPicture(String srcPath, String desPath) {
	        FileOutputStream fos = null;
	        BitmapFactory.Options op = new BitmapFactory.Options();
	        op.inJustDecodeBounds = true;
	        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, op);
	        op.inJustDecodeBounds = false;
	        float w = op.outWidth;
	        float h = op.outHeight;
	        float hh = 960f;//
	        float ww = 720f;//
	        float be = 1.0f;
	        if (w > h && w > ww) {
	            be = w / ww;
	        } else if (w < h && h > hh) {
	            be = (float) (h / hh);
	        }
	        if (be <= 0) {
	            be = 1.0f;
	        }
	        op.inSampleSize = (int) be;
	        bitmap = BitmapFactory.decodeFile(srcPath, op);
	        int desWidth = (int) (w / be);
	        int desHeight = (int) (h / be);
	        bitmap = Bitmap.createScaledBitmap(bitmap, desWidth, desHeight, true);
	        try {
	            fos = new FileOutputStream(desPath);
	            if (bitmap != null) {
	                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

}
