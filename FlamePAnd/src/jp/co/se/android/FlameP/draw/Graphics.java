package jp.co.se.android.FlameP.draw;

import javax.microedition.khronos.opengles.GL10;

import android.content.res.Resources;
import android.graphics.*;
import android.view.SurfaceHolder;

import jp.co.se.android.FlameP.Main.MainData;
import jp.co.se.android.FlameP.lib.Library;

/**
 * MainCanvas
 * 
 */
public class Graphics 
{
	private SurfaceHolder holder;
	private Paint paint;
	private Canvas canvas;

	
	public Graphics(SurfaceHolder getholder)
	{
		this.holder = getholder;
		paint = new Paint();
		paint.setAntiAlias(true);
	}
	
	GL10 glData;
	public void setGl(GL10 gl)
	{
		glData = gl;
	}
	
	public Bitmap createBitMap(Resources r,int id )
	{
		return createBitMap( r, id , Color.argb(255, 0, 255, 0));
	}
	
	int[] pixels;
	
	public Bitmap createBitMap(Resources r,int id ,int eraseColor)
	{
		Bitmap data;
		try
		{
			
			data = BitmapFactory.decodeResource(r, id);
	
			if(data == null)
			{
				Library.TraseMsg("bmpLoadErr");
			}
			if( eraseColor == 0)
	        {
	        	return data ;
	        }
			int width = data.getWidth();	
			int height = data.getHeight();
	
			pixels = new int[width * height]; 
	
			
			int c = eraseColor;
	        Bitmap bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888 );
	        
	        
	        
	        data.getPixels(pixels, 0, width, 0, 0, width, height); 
	        
	        for (int y = 0; y < height; y++) 
	        { 	
	          for (int x = 0; x < width; x++) 
	          { 
	            if( pixels[x + y * width]== c)
	            {
	            	pixels[x + y * width] = 0; 
	            } 
	          } 
	        } 
	        bitmap.eraseColor(eraseColor); 
	        bitmap.setPixels(pixels, 0, width, 0, 0, width, height); 
	        
	        pixels = null;
	        
	        return bitmap;
		}catch (Exception e) {
			// TODO: handle exception
			int a = 0;
		}
		
		return null;
	}
	
	public static final int SET_COLOR_RED   = 1;
	public static final int SET_COLOR_BLUE  = 2;
	public static final int SET_COLOR_GREEN = 3;
	
	public Bitmap setBitMapColor(Bitmap bitmap,int colorType)
	{
		int width = bitmap.getWidth();	
		int height = bitmap.getHeight();
		int[] pixels = new int[width * height]; 
		
		bitmap.getPixels(pixels, 0, width, 0, 0, width, height); 
		
		for (int y = 0; y < height; y++) 
        { 	
          for (int x = 0; x < width; x++) 
          { 
        	  int now = x * width+ y ;
        	  
        	  int blue = Color.blue(pixels[now]);
        	  int green = Color.green(pixels[now]);
        	  int red = Color.red(pixels[now]);
        	  
        	  if(colorType == SET_COLOR_RED)
        	  {
        		  green /= 3;
        		  blue  /= 3;
        	  }
        	  pixels[now] = Color.argb(255, red, green, blue);        	  
          }
        }
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height); 
		return bitmap;
	}

	
	public void lock()
	{
		this.canvas = holder.lockCanvas();
	}
	public void unlock()
	{
		holder.unlockCanvasAndPost(this.canvas);
	}
	
	public int stringWidth(String mes)
	{
		int size = (int)((float)paint.measureText(mes) * (float)MainData.Width_Size);

		return size;
	}

	public void fillRect(int x , int y ,
			int w ,int h)
	{
		fillRect( x , y ,
				 w , h , true );
	}
	public void fillRect(int x , int y ,
			int w ,int h , boolean size )
	{
		paint.setStyle(Paint.Style.FILL);
		
		if(size == false)
		{
			x = (int)((float)x);
			y = (int)((float)y);
			w = (int)((float)w);
			h = (int)((float)h);
		}else
		{
			x +=MainData.SCREEN_MOVE_X;
			y +=MainData.SCREEN_MOVE_Y;

//			x = (int)((float)x*(float)MainData.Width_Size)+MainData.SCREEN_MOVE_X;
//			y = (int)((float)y*(float)MainData.Height_Size)+MainData.SCREEN_MOVE_Y;
//			w = (int)((float)w*(float)MainData.Width_Size);
//			h = (int)((float)h*(float)MainData.Height_Size);			
		}
		
		
		//canvas.scale(MainData.Width_Size , MainData.Height_Size );
		canvas.drawRect(new Rect(x,y,x+w,y+h), paint);
	}
	
	public void drawCircle(int x , int y ,
			int big)
	{
        // ‰~‚ð•`‰æ‚·‚é
        paint.setStyle(Paint.Style.FILL);
        x +=MainData.SCREEN_MOVE_X;
		y +=MainData.SCREEN_MOVE_Y;
		//canvas.scale(MainData.Width_Size , MainData.Height_Size );
        canvas.drawCircle(x, y, big, paint);
	}
	
	
	public void drawBitMap(Bitmap bitmap ,
			int x ,int y)
	{
		int imageW = bitmap.getWidth();
		int imageH = bitmap.getHeight();
		
		drawBitMap( bitmap ,
				x , y ,
				0 , 0 ,
				imageW , imageH );
	}

	
	public void drawBitMap(Bitmap bitmap ,
			int x , int y,
			int drawx , int drawy ,
			int drawW , int drawH)
	{

		int imageW = bitmap.getWidth();
		int imageH = bitmap.getHeight();
		
		drawBitMapScale( bitmap ,
				drawx , drawy ,
				drawW , drawH ,
				x , y, drawW , drawH );
	}

	public void drawBitMapScale(Bitmap bitmap ,
			int x ,int y,int w , int h , boolean SizeMode)
	{
		int imageW = bitmap.getWidth();
		int imageH = bitmap.getHeight();
		drawBitMapScale( bitmap ,
				0 , 0 ,
				imageW , imageH ,
				x , y, w ,  h ,SizeMode);		
	}
	
	public void drawBitMapScale(Bitmap bitmap ,
			int x ,int y,int w , int h)
	{

		
		drawBitMapScale( bitmap ,
				 x , y, w , h , false );
		
	}

	public void drawBitMapScale(Bitmap bitmap ,
			int drawx , int drawy ,
			int drawW , int drawH ,
			int x ,int y,int w , int h)
	{
		drawBitMapScale( bitmap ,
				 drawx ,  drawy ,
				 drawW ,  drawH ,
				 x , y, w , h , true );
	}
	
	public void drawBitMapScale(Bitmap bitmap ,
			int drawx , int drawy ,
			int drawW , int drawH ,
			int x ,int y,int w , int h , boolean SizeMode)
	{
		drawBitMapScale( bitmap ,
			 drawx , drawy ,
			 drawW , drawH ,
			 x , y, w , h , SizeMode , 0 );
	}
	
	public void drawBitMapScale(Bitmap bitmap ,
			int drawx , int drawy ,
			int drawW , int drawH ,
			int x ,int y,int w , int h , boolean SizeMode , int angle)
	{
		
		Rect src = new Rect(
				drawx , drawy ,
				drawW+drawx , drawH +drawy);
		
		//w *= 1.42f;
		//h *= 1.33f;
		
		if(SizeMode)
		{
//			x = (int)((float)x*(float)(MainData.Width_Size))+MainData.SCREEN_MOVE_X;
//			y = (int)((float)y*(float)(MainData.Height_Size))+MainData.SCREEN_MOVE_Y;			
//			w = (int)((float)w*(float)(MainData.Width_Size));
//			h = (int)((float)h*(float)(MainData.Height_Size));
			x +=MainData.SCREEN_MOVE_X;
			y +=MainData.SCREEN_MOVE_Y;
		}else
		{
		}
		x += shake_X;
		y += shake_Y;		
		
		Rect dst = new Rect(x,y,w+x,h+y);
		
		if(angle != 0 )
		{
			Matrix matrix = new Matrix();  
		    matrix.postRotate((float)angle);  // ‰ñ“]‚³‚¹‚éŠp“x‚ðŽw’è  
		    bitmap = Bitmap.createBitmap(bitmap, 0, 0,
		    	bitmap.getWidth(), bitmap.getHeight(), matrix, true);  		    
		}
		//setColor(getColorOfRGB(  255,  255, 255 , 255 ));
		
		//canvas.scale(MainData.Width_Size , MainData.Height_Size );
		
		canvas.drawBitmap(bitmap, src, dst , paint );
	}
	
	public void setCanvasScale()
	{
		canvas.scale(MainData.Width_Size_True , MainData.Height_Size_True );
	}
	
	
	public void drawString(String string , int x , int y)
	{
		x = (int)((float)x*(float)MainData.Width_Size) + MainData.SCREEN_MOVE_X;
		y = (int)((float)y*(float)MainData.Height_Size)+ MainData.SCREEN_MOVE_Y;
		//canvas.scale(MainData.Width_Size , MainData.Height_Size );

		canvas.drawText(string, x, y, paint);
	}

	public void setAlpha(int alpha )
	{	
		if(alpha <= 0)
		{
			alpha = 0;
		}
		if(alpha >= 255)
		{
			alpha = 255;
		}
		setColor(getColorOfRGB(  alpha ,  255, 255 , 255 ));
	}
	

	
	public void setColor(int color)
	{
		paint.setColor(color);
	}
	
	public static int shake_X = 0;
	public static int shake_Y = 0;
	public void setShake(int shakeX , int shakeY)
	{
		shake_X = shakeX;
		shake_Y = shakeY;
	}
	
	
	
	public static final int SIZE_TINY = 12;
	public static final int SIZE_SMALL = 20;

	public void setFontSize(int size)
	{
		size = (int)((float)size * (float)MainData.Width_Size);
		paint.setTextSize(size);
	}
	
	public int getFontSize()
	{
		return (int)paint.getTextSize();
	}
	
	
	
	public static final int FLIP_HORIZONTAL = 1;
	
	public Bitmap setFlipMode(Bitmap bmp,int mode)
	{
		Matrix matrix = new Matrix();

		int width = bmp.getWidth();
		int height = bmp.getHeight(); 

		
		matrix.preScale(1, 1);		
		if(mode == FLIP_HORIZONTAL)
		{
			matrix.preScale(-1.0f, 1.0f);
		}
		
	
		Bitmap Afbmp = Bitmap.createBitmap(bmp, 0, 0, width, height,matrix , false);
		
		return Afbmp;
	}

	
	
	
	
	public static int getColorOfRGB( int r, int g, int b , int alpha )
	{
		return Library.COLOR_ARGB(alpha , r ,  g ,  b);
	}
	
}