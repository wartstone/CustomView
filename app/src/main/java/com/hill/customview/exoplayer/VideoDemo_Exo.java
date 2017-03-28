package com.hill.customview.exoplayer;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hill.customview.R;
import com.hill.customview.exoplayer.exoplayer.DashRendererBuilder;
import com.hill.customview.exoplayer.exoplayer.DemoPlayer;
import com.hill.customview.exoplayer.exoplayer.ExtractorRendererBuilder;
import com.hill.customview.exoplayer.exoplayer.HlsRendererBuilder;
import com.hill.customview.exoplayer.exoplayer.SmoothStreamingRendererBuilder;
import com.google.android.exoplayer.AspectRatioFrameLayout;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecTrackRenderer;
import com.google.android.exoplayer.MediaCodecUtil;
import com.google.android.exoplayer.drm.UnsupportedDrmException;
import com.google.android.exoplayer.util.Util;


public class VideoDemo_Exo extends Activity implements SurfaceHolder.Callback, DemoPlayer.Listener, View.OnClickListener {

    private FrameLayout videoFrame;//用来控制视频的宽高比
    private SurfaceView surfaceView; //播放区
    private TextView playerStateTextView;//播放的状态
    private RelativeLayout video_skin;
    private TextView prompt;//提示

    //视频控制的各个按钮
    private RelativeLayout video_control;
    private ImageButton py;
    private ImageButton fs;
    private SeekBar seekBar;

    private DemoPlayer player;
    private Uri contentUri; //视频的uri
    private int contentType;//流媒体传输协议类型
    private int Duration;//视频的大小
    private int video_width;
    private int video_heigth;

    private long playerPosition;
    private boolean playerNeedsPrepare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
        //常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        initView();

    }


//    //再次启用Activity时，这个方法会在restart方法前面执行 属于生命周期内的
//    @Override
//    public void onNewIntent(Intent intent) {
//        releasePlayer();
//        playerPosition = 0;
//        setIntent(intent);
//    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            onShown();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23 || player == null) {
            onShown();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void initView() {
        videoFrame = (FrameLayout) findViewById(R.id.video_frame);
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        surfaceView.getHolder().addCallback(this);
        playerStateTextView = (TextView) findViewById(R.id.playerStateTextView);
        video_skin= (RelativeLayout) findViewById(R.id.video_skin);
        prompt= (TextView) findViewById(R.id.prompt);


        //视频控制按钮
        video_control= (RelativeLayout) findViewById(R.id.video_control);
        video_control.setVisibility(View.GONE);
        py= (ImageButton) findViewById(R.id.py);
        py.setOnClickListener(this);
        fs= (ImageButton) findViewById(R.id.fs);
        fs.setOnClickListener(this);
        seekBar= (SeekBar) findViewById(R.id.sk);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {//放开
                     player.seekTo(seekBar.getProgress());
            }
        });

        video_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voidControlsVisibility();
            }
        });



    }

    //释放player
    private void releasePlayer() {
        if (player != null) {
            playerPosition = player.getCurrentPosition();
            player.release();
            player = null;
        }
    }

    // Internal methods 内部方法
    private DemoPlayer.RendererBuilder getRendererBuilder() {
        String userAgent = Util.getUserAgent(this, "MyExoPlayer");
        switch (contentType) {
            case Util.TYPE_SS:
                return new SmoothStreamingRendererBuilder(this, userAgent, contentUri.toString(),
                        new SmoothStreamingTestMediaDrmCallback());
            case Util.TYPE_DASH:
                return new DashRendererBuilder(this, userAgent, contentUri.toString(),
                        new WidevineTestMediaDrmCallback(null, null));
            //new WidevineTestMediaDrmCallback(contentId, provider));
            case Util.TYPE_HLS:
                return new HlsRendererBuilder(this, userAgent, contentUri.toString());
            case Util.TYPE_OTHER:
                return new ExtractorRendererBuilder(this, userAgent, contentUri);
            default:
                throw new IllegalStateException("Unsupported type: " + contentType);
        }
    }

    //获取视频数据
    private void onShown() {

        //contentUri = Uri.parse("http://devimages.apple.com/samplecode/adDemo/ad.m3u8");
        contentUri = Uri.parse("http://upyun.aube-tv.com/upload2/prd/2017/0204/s25b43a6d_1599164dad5__7a90.mp4?_upt=2c6bc2311486228973");
        //contentUri = Uri.parse("http://redirector.c.youtube.com/videoplayback?id=604ed5ce52eda7ee&itag=22&source=youtube&sparams=ip,ipbits,expire,source,id&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=513F28C7FDCBEC60A66C86C9A393556C99DC47FB.04C88036EEE12565A1ED864A875A58F15D8B5300&key=ik0");
        //contentUri = Uri.parse("http://html5demos.com/assets/dizzy.mp4");

        contentType = inferContentType(contentUri);
        Log.e("TAG", "contentUri" + contentUri + "contentType" + contentType);
        if (player == null) {
            //if (!maybeRequestPermission()) { 检查权限，6.0以上可以动态获取权限
            preparePlayer(true);
            //}
        } else {
            player.setBackgrounded(false);
        }
    }

    //控制按钮的显示
    private void voidControlsVisibility() {
        int vs=video_control.getVisibility()==View.GONE?View.VISIBLE:View.GONE;
        video_control.setVisibility(vs);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {//竖屏
           // scaleLayout(video_skin,videoFrame.getWidth(),videoFrame.getHeight());
        }
    }
    /**
     * 根据uri判断出媒体类型,//根据uri获取流媒体传输协议
     * Makes a best guess to infer the type from a media {@link Uri} and an optional overriding file
     * extension.使最佳猜测推断出从一个媒体类型{ @link Uri }和一个可选的最重要的文件扩展。
     *
     * @return The inferred type.推断出来的视频类型
     */
    private static int inferContentType(Uri uri) {
        String u = uri.toString();
        String lastPathSegment = u.substring(u.lastIndexOf("."));
        return Util.inferContentType(lastPathSegment);
    }

    private void preparePlayer(boolean playWhenReady) {
        if (player == null) {
            player = new DemoPlayer(getRendererBuilder());
            player.addListener(this);
            player.seekTo(playerPosition);//播放进度的设置
            playerNeedsPrepare = true; //是否立即播放
        }
        if (playerNeedsPrepare) {
            player.prepare();
            playerNeedsPrepare = false;
        }
        player.setSurface(surfaceView.getHolder().getSurface());
        player.setPlayWhenReady(playWhenReady);

    }

    //surfaceView的监听
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (player != null) {
            player.setSurface(surfaceHolder.getSurface());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (player != null) {
            player.blockingClearSurface();
        }
    }

    /**
    * 视频的播放状态
     STATE_IDLE 播放器空闲，既不在准备也不在播放
     STATE_PREPARING 播放器正在准备
     STATE_BUFFERING 播放器已经准备完毕，但无法立即播放。此状态的原因有很多，但常见的是播放器需要缓冲更多数据才能开始播放
     STATE_PAUSE 播放器准备好并可以立即播放当前位置
     STATE_PLAY 播放器正在播放中
     STATE_ENDED 播放已完毕
    * */
    @Override
    public void onStateChanged(boolean playWhenReady, int playbackState) {
        String text = "playWhenReady=" + playWhenReady + ", playbackState=";
        switch (playbackState) {
            case ExoPlayer.STATE_BUFFERING:
                prompt.setVisibility(View.VISIBLE);
                prompt.setText("加载中。。。");
                text += "buffering";
                break;
            case ExoPlayer.STATE_ENDED:
                text += "ended";
                break;
            case ExoPlayer.STATE_IDLE://空的
                prompt.setVisibility(View.VISIBLE);
                prompt.setText("网络状态差，请检查网络。。。");
                text += "idle";
                break;
            case ExoPlayer.STATE_PREPARING:
                prompt.setVisibility(View.VISIBLE);
                prompt.setText("准备中。。。");
                surfaceView.setVisibility(View.VISIBLE);
                text += "preparing";
                break;
            case ExoPlayer.STATE_READY:
                text += "ready";
                prompt.setVisibility(View.GONE);
                boolean first=true;
                if(first) {
                    //记录视频的宽高
                    video_width=videoFrame.getWidth();
                    video_heigth=videoFrame.getHeight();
                    //skin的宽高
                    if(video_width!=0&&video_heigth!=0){
                        scaleLayout(video_skin,video_width,video_heigth);
                    }
                    //进度条的时间设置
                    Duration = (int) player.getDuration();
                    Log.e("TAG", "Duration--" + Duration);
                    seekBar.setMax(Duration);
                    videoTime seek = new videoTime();
                    seek.start();
                    first=false;
                }
                break;
            default:
                text += "unknown";
                break;
        }
        playerStateTextView.setText(text);
    }

    @Override
    public void onError(Exception e) {
        String errorString = null;
        if (e instanceof UnsupportedDrmException) {
            // 特殊情况DRM的失败
            UnsupportedDrmException unsupportedDrmException = (UnsupportedDrmException) e;
            errorString = getString(Util.SDK_INT < 18 ? R.string.error_drm_not_supported
                    : unsupportedDrmException.reason == UnsupportedDrmException.REASON_UNSUPPORTED_SCHEME
                    ? R.string.error_drm_unsupported_scheme : R.string.error_drm_unknown);
        } else if (e instanceof ExoPlaybackException
                && e.getCause() instanceof MediaCodecTrackRenderer.DecoderInitializationException) {
            // Special case for decoder initialization failures.
            MediaCodecTrackRenderer.DecoderInitializationException decoderInitializationException =
                    (MediaCodecTrackRenderer.DecoderInitializationException) e.getCause();
            if (decoderInitializationException.decoderName == null) {
                if (decoderInitializationException.getCause() instanceof MediaCodecUtil.DecoderQueryException) {
                    errorString = getString(R.string.error_querying_decoders);
                } else if (decoderInitializationException.secureDecoderRequired) {
                    errorString = getString(R.string.error_no_secure_decoder,
                            decoderInitializationException.mimeType);
                } else {
                    errorString = getString(R.string.error_no_decoder,
                            decoderInitializationException.mimeType);
                }
            } else {
                errorString = getString(R.string.error_instantiating_decoder,
                        decoderInitializationException.decoderName);
            }
        }
        if (errorString != null) {
            Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_LONG).show();
        }
        playerNeedsPrepare = true;
    }

    //pixelWidthHeightRatio 显示器的宽高比
    @Override
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
        //videoFrame.setAspectRatio(height == 0 ? 1 : (width * pixelWidthHeightRatio) / height);
    }

    //横竖屏切换
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {//横屏
            //隐藏状态栏
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            //skin的宽高
            scaleLayout(video_skin, 0, 0);

        } else{//竖屏
            //显示状态栏
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            //skin的宽高
            if(video_width!=0&&video_heigth!=0){
                scaleLayout(video_skin,video_width,video_heigth);
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.py://播放键
                if(player.getPlayWhenReady()){
                    py.setBackground(getResources().getDrawable(R.mipmap.pause));
                    player.setPlayWhenReady(false);
                }else {
                    py.setBackground(getResources().getDrawable(R.mipmap.player));
                    player.setPlayWhenReady(true);
                }

                break;
            case R.id.fs://全屏键

                if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {//横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    fs.setBackground(getResources().getDrawable(R.mipmap.full));
                } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){//竖屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    fs.setBackground(getResources().getDrawable(R.mipmap.lessen));
                }
                break;
        }
    }

    //设置videoFrame的大小
    private void scaleLayout(View view,int width,int height) {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        if (width == 0) {
            width=outMetrics.widthPixels;
        }
        if (height == 0) {
            height = outMetrics.heightPixels;
        }
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (params == null) {
            params = new RelativeLayout.LayoutParams(width, height);
        } else {
            params.height = height;
            params.width = width;
        }

        view.setLayoutParams(params);
    }
    //更新进度条
    private final int SEEKBAR=111;
    private Handler seekbarHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SEEKBAR:
                    seekBar.setProgress(msg.arg1);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    class videoTime extends Thread {
        public void run() {
                while (player!=null&&player.getCurrentPosition() <= Duration) {
                    if(player.getPlayWhenReady()){
                    Message message = new Message();
                    message.what = SEEKBAR;
                    message.arg1 = (int) player.getCurrentPosition();
                    seekbarHandler.sendMessage(message);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Log.e("TAG", player.getCurrentPosition() + "---seek---" + Duration);
                }
            }
        }
    }
}