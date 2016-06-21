/*
 *
 *   Copyright 2016 YunDi
 *
 *   The code is part of Yunnan, Shenzhen Branch of the internal architecture of YunDi source group
 *
 *   DO NOT DIVULGE
 *
 */

package com.bq2015.bqlib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/************************************************************
 * ,* Author:  bq2015
 * ,* Description:  Bitmap相关的工具类
 * ,* Date: 2016/3/7
 ************************************************************/
public class BitmapTools {

    /**
     * 根据输入流获取位图对象
     *
     * @param is
     * @return
     */
    public static Bitmap getBitmap(InputStream is) {
        return BitmapFactory.decodeStream(is);
    }

    /**
     * 根据输入流和 缩小比例 获取位图对象
     *
     * @param is
     * @param scale
     * @return
     */
    public static Bitmap getBitmap(InputStream is, int scale) {
        Bitmap bitmap = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = scale;
        bitmap = BitmapFactory.decodeStream(is, null, opts);
        return bitmap;
    }

    /**
     * 根据指定的宽高 保持纵横比 缩小读取指定图片
     *
     * @param bytes
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getBitmap(byte[] bytes, int width, int height) {
        Bitmap bitmap = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
        opts.inJustDecodeBounds = false;
        int scaleX = opts.outWidth / width;
        int scaleY = opts.outHeight / height;
        int scale = scaleX > scaleY ? scaleX : scaleY;
        opts.inSampleSize = scale;
        Log.i("info", "scale : " + scale);
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
        return bitmap;
    }

    /**
     * 根据指定的宽高 等比例 缩小读取指定路径的图片
     *
     * @param fileName
     *            文件名
     * @param width
     *            宽
     * @param height
     *            高
     * @return
     */
    public static Bitmap getBitmap(String fileName, int width, int height) {
        // 绝对路径
        String abPath =fileName;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 通过这个bitmap获取图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeFile(abPath, options);
        // if (bitmap == null)
        // {
        // System.out.println("bitmap为空");
        // }
        float realWidth = options.outWidth;
        float realHeight = options.outHeight;
        if (realHeight == 0 || realWidth == 0) {
            return null;
        }
        // System.out.println("真实图片高度：" + realHeight + "宽度:" + realWidth);
        // 计算缩放比
        int scaleX = options.outWidth / width;
        int scaleY = options.outHeight / height;
        int scale = scaleX > scaleY ? scaleX : scaleY;
        options.inSampleSize = scale;
        // }
        // else if(flag==1){
        // options.outWidth=width;
        // options.outHeight=height;
        // }
        options.inJustDecodeBounds = false;
        // 注意这次要把options.inJustDecodeBounds 设为 false,这次图片是要读取出来的。
        bitmap = BitmapFactory.decodeFile(abPath, options);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        System.out.println("缩略图高度：" + h + "宽度:" + w);
        return bitmap;
    }
    /**
     * 根据指定的宽高 缩小读取指定路径的图片
     *
     * @param fileName
     *            文件名
     * @param width
     *            宽
     * @param height
     *            高
     * @return
     */
    public static Bitmap getBitmapDeng(String fileName, int width, int height) {
        // 绝对路径
        String abPath =  fileName;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 通过这个bitmap获取图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeFile(abPath, options);
        // if (bitmap == null)
        // {
        // System.out.println("bitmap为空");
        // }
        float realWidth = options.outWidth;
        float realHeight = options.outHeight;
        if (realHeight == 0 || realWidth == 0) {
            return null;
        }
        // System.out.println("真实图片高度：" + realHeight + "宽度:" + realWidth);
        // 计算缩放比
        float scaleX = width/realWidth ;
        float scaleY =  height/realHeight ;
        int scale = (int) (scaleX > scaleY ? scaleX : scaleY);
        options.inSampleSize = scale;
        Matrix matrix = new Matrix();
        // float scaleWidth = ((float) w / width);
        // float scaleHeight = ((float) h / height);
        matrix.postScale(scaleX, scaleY);
        // }
        // else if(flag==1){
        // options.outWidth=width;
        // options.outHeight=height;
        // }
        options.inJustDecodeBounds = false;
        // 注意这次要把options.inJustDecodeBounds 设为 false,这次图片是要读取出来的。
        bitmap = BitmapFactory.decodeFile(abPath, options);
        bitmap=Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
//      int w = bitmap.getWidth();
//      int h = bitmap.getHeight();
//      System.out.println("缩略图高度：" + h + "宽度:" + w);
        return bitmap;
    }


    /**
     * 根据指定的高度比例，拉伸读取指定图片
     *
     * @param bytes
     * @param height
     * @return
     */
    public static Bitmap getBitmap(byte[] bytes, int height) {
        Bitmap bitmap = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
        opts.inJustDecodeBounds = false;
        // int scaleX = opts.outWidth / width;
        // int scaleY = opts.outHeight / height;
        // int scale = scaleX > scaleY ? scaleX : scaleY;
        opts.outHeight = opts.outHeight * height;

        // Log.i("info", "scale : " + scale);
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
        return bitmap;
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 根据路径 从文件中读取位图对象
     *
     * @param path
     * @return
     */
    public static Bitmap getbiBitmap(String path) {
        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeFile(path);
        return bitmap;
    }

    /**
     * 保存位图对象到指定位置
     *
     * @param path
     * @param bitmap
     * @throws IOException
     */
    public static void saveBitmap(String path, Bitmap bitmap)
            throws IOException {
        if (path != null && bitmap != null) {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            OutputStream stream = new FileOutputStream(file);
            String name = file.getName();
            String end = name.substring(name.lastIndexOf(".") + 1);
            if ("png".equals(end)) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            } else {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            }
        }
    }

    /**
     * 将图片内容解析成字节数组
     * @param inStream
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }

    /**
     * 将字节数组转换为ImageView可调用的Bitmap对象
     * @param bytes
     * @param opts
     * @return Bitmap
     */
    public static Bitmap getPicFromBytes(byte[] bytes,
                                         BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                        opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

    /**
     * 图片缩放
     * @param bitmap
     *            对象
     * @param w
     *            要缩放的宽度
     * @param h
     *            要缩放的高度
     * @return newBmp 新 Bitmap对象
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newBmp;
    }

    /**
     * 等比图片缩放
     * @param bitmap
     *            对象
     * @param w
     *            要缩放的宽度
     * @param h
     *            要缩放的高度
     * @return newBmp 新 Bitmap对象
     */
    public static Bitmap zoomBitmapDeng(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        float scale = scaleWidth > scaleHeight ? scaleWidth : scaleHeight;
        matrix.postScale(scale, scale);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        bitmap.recycle();
        return newBmp;
    }

    /**
     * 等比图片缩放
     * @param bitmap
     *            对象
     * @param scale
     *            等比缩放的比例
     * @return newBmp 新 Bitmap对象
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, float scale) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        // float scaleWidth = ((float) w / width);
        // float scaleHeight = ((float) h / height);
        matrix.postScale(scale, scale);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newBmp;
    }

    /**
     * 把字节数组保存为一个文件
     * @param bytes
     *            对象
     * @param outputFile
     *            输出的文件路径
     * @return
     */
    public static File getFileFromBytes(byte[] bytes, String outputFile) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }
    /**
     * 获取圆角位图的方法
     *
     * @param bitmap
     *            需要转化成圆角的位图
     * @param pixels
     *            圆角的度数，数值越大，圆角越大
     * @return 处理后的圆角位图
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
//      final int color = 0x00FFFFFF;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
//      paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 获取圆形位图的方法
     *
     * @param bitmap
     *            需要转化成圆的位图
     * @return 处理后的圆位图
     */
    public static Bitmap toRound(Bitmap bitmap) {
        final int bitmap_w = bitmap.getWidth();
        final int bitmap_h = bitmap.getHeight();
        int radius = bitmap_w;
//        Rect rect  = new Rect(0, 0, bitmap_w, bitmap_h);
        float left = 0, top = 0;
        if(radius > bitmap_h) {
            radius = bitmap_h;
            left = (radius-bitmap_w)/2;
        } else {
            top = (radius-bitmap_h)/2;
        }
        Bitmap output = Bitmap.createBitmap(radius,
                radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(1, 0, 0, 0);
        radius /= 2;
        canvas.drawCircle(radius, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, left, top, paint);
        return output;
    }
    /**
     * bitmap毛玻璃模糊效果
     *
     * @param sentBitmap, radius
     *
     * @return 处理后的圆位图
     */
    public static Bitmap fastblur(Bitmap sentBitmap, int radius) {

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int temp = 256 * divsum;
        int dv[] = new int[temp];
        for (i = 0; i < temp; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
                        | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);
        return (bitmap);
    }
}
