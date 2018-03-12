package com.github.mhrjnamar.circularimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by User on 12/26/2017.
 */

public class CircleImageView extends AppCompatImageView {

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        Drawable drawable = getDrawable();
//
//        if (drawable == null) {
//            return;
//        }
//
//        if (getWidth() == 0 || getHeight() == 0) {
//            return;
//        }
//
//        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
//        if (b== null){
//            return;
//        }
//        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
//
//        int width = getWidth();
//
//        Bitmap roundBitmap = getCroppedBitmap(bitmap, width);
//        canvas.drawBitmap(roundBitmap, 0, 0, null);
    }

    private Bitmap getCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap sbmp;
        if (bitmap.getHeight() != radius || bitmap.getWidth() != radius) {
            float smallest = Math.min(bitmap.getHeight(), bitmap.getWidth());
            float factor = smallest / radius;
            sbmp = Bitmap.createScaledBitmap(bitmap,
                    ((int) (bitmap.getWidth() / factor)),
                    ((int) (bitmap.getHeight() / factor)), false);
        } else {
            sbmp = bitmap;
        }

        Bitmap outputBitmap = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outputBitmap);

        final String color = "#c3c9c9";
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, radius, radius);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        //paint.setMaskFilter(new EmbossMaskFilter())
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor(color));
        canvas.drawCircle(radius / 2 + 0.7f, radius / 2 + 0.7f, radius / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return outputBitmap;
    }

}
