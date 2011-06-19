package kr.comnic.WellProportionedFigure;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class WellProportionedFigureActivity extends Activity {
    private Preview mPreview;
    private ImageView m_model;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        

        //Intent intent = new Intent(this, MeasureImage.class);
        //startActivity(intent);
        
        // Create our Preview view and set it as the content of our activity.
        RelativeLayout layout = new RelativeLayout(this);
        mPreview = new Preview(this);
        layout.addView(mPreview);
        m_model = new ImageView(this);
        m_model.setImageResource(R.drawable.model_w1_1);
        layout.addView(m_model);

        
        setContentView(layout);
        
        
        
    }
}

class Preview extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder mHolder;
    Camera mCamera;
    
    Preview(Context context) {
        super(context);
        
        // SurfaceHolder.Callback�� ���������ν� Surface�� ����/�Ҹ�Ǿ�����
        // �� �� �ֽ��ϴ�.
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // Surface�� �����Ǿ��ٸ�, ī�޶��� �ν��Ͻ��� �޾ƿ� �� ī�޶���
        // Preview �� ǥ���� ��ġ�� �����մϴ�.
        mCamera = Camera.open();
        try {
           mCamera.setPreviewDisplay(holder);
        } catch (IOException exception) {
            mCamera.release();
            mCamera = null;
            // TODO: add more exception handling logic here
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // �ٸ� ȭ������ ���ư���, Surface�� �Ҹ�˴ϴ�. ���� ī�޶��� Preview�� 
        // �����ؾ� �մϴ�. ī�޶�� ������ �� �ִ� �ڿ��� �ƴϱ⿡, ������� ����
        // ��� -��Ƽ��Ƽ�� �Ͻ����� ���°� �� ��� �� - �ڿ��� ��ȯ�ؾ��մϴ�.
        mCamera.stopPreview();
        mCamera = null;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // ǥ���� ������ ũ�⸦ �˾����Ƿ� �ش� ũ��� Preview�� �����մϴ�.
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(w, h);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    }

}