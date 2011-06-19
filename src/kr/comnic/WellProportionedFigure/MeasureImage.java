package kr.comnic.WellProportionedFigure;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MeasureImage extends Activity {
	private FrameLayout layout;
	private LayoutParams params;
	
	private ImageView m_picture;

    private DragableView m_line;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.measure_image);

	    layout = (FrameLayout) findViewById(R.id.main_frameLayout);

	    m_picture = new ImageView(this);
	    m_picture.setImageResource(R.drawable.i001);
		layout.addView(m_picture);

	    m_line = new DragableView(this);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.height = 50;
	    m_line.setLayoutParams(params);
	    m_line.setBackgroundColor(Color.BLACK);
		layout.addView(m_line);

	}
}
