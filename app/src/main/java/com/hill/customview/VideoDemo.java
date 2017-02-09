package com.hill.customview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by hill on 16/11/24.
 */

public class VideoDemo extends Activity
        implements TextureView.SurfaceTextureListener,
        MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnVideoSizeChangedListener {

    private MediaPlayer mMediaPlayer;
    private TextureView mTextureView;
    private FrameLayout mBlurLayout;
    private ImageView mBlurView;

    public static String MY_VIDEO = "http://upyun.aube-tv.com/upload2/prd/2017/0204/s25b43a6d_1599164dad5__7a90.mp4?_upt=2c6bc2311486228973";
    public static String TAG = "TextureViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_videodemo);

        mTextureView = (TextureView) findViewById(R.id.videodemo_textureview);
        mTextureView.setSurfaceTextureListener(this);
        mBlurLayout = (FrameLayout) findViewById(R.id.videodemo_blurroot);
        mBlurView = (ImageView) findViewById(R.id.videodemo_blurview);
    }

    public void getBitmap(TextureView vv) {
        //String mPath = "/storage/emulated/legacy/1.png";
        String mPath = Environment.getExternalStorageDirectory().toString()
                + "/Pictures/textureview.png";
        //Toast.makeText(getApplicationContext(), "Capturing Screenshot: " + mPath, Toast.LENGTH_SHORT).show();

        Bitmap bm = vv.getBitmap();
        if (bm == null)
            Log.e(TAG, "bitmap is null");

        mTextureView.setVisibility(View.GONE);
        showBlurImg(bm);

        OutputStream fout = null;
        File imageFile = new File(mPath);

//        try {
//            fout = new FileOutputStream(imageFile);
//            bm.compress(Bitmap.CompressFormat.PNG, 90, fout);
//            fout.flush();
//            fout.close();
//            Toast.makeText(getApplicationContext(), "Captured Screenshot: " + mPath, Toast.LENGTH_SHORT).show();
//        } catch (FileNotFoundException e) {
//            Log.e(TAG, "FileNotFoundException");
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), "Capture FileNotFoundException", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            Log.e(TAG, "IOException");
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), "Capture IOException", Toast.LENGTH_SHORT).show();
//        }
    }
    
    private void showBlurImg(Bitmap bmp) {
//        try {
//            mBlurView.setImageResource(bmp);
//            mBlurView = BlurViewDecorator.createBlurView(context, true);
//            mBlurLayout.addView(mBlurView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        mBlurLayout.setVisibility(View.VISIBLE);
        Drawable drawable = new BitmapDrawable(bmp);
        mBlurView.setImageDrawable(drawable);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.media_player_video, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Surface s = new Surface(surface);

        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(MY_VIDEO);
            mMediaPlayer.setSurface(s);
            mMediaPlayer.prepare();

            mMediaPlayer.setOnBufferingUpdateListener(this);
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setOnVideoSizeChangedListener(this);

            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.start();

            TextView text = (TextView) findViewById(R.id.videodemo_text);
            text.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    VideoDemo.this.getBitmap(mTextureView);
                }
            });
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }
}
