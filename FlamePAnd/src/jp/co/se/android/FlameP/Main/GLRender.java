package jp.co.se.android.FlameP.Main;

import javax.microedition.khronos.egl.EGLConfig;  

import javax.microedition.khronos.opengles.GL10;  

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;  
import jp.co.se.android.FlameP.Main.MainData;

public class GLRender implements Renderer {  


	MainData pMain;
	
	public GLRender(MainData main )
	{
		pMain = main;
	}
	
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {}  

   

     

     public void onSurfaceChanged(GL10 gl, int width, int height) {  

         ///gl.glViewport(0, 0, width, height);  

     }  

  

     

   public void onDrawFrame(GL10 gl) {  

         updateColor();  

       gl.glClearColor(r, g, b, 1.0f);  

      // glClear() の引数には、現在の消去値でクリアする対象バッファを指定  

      gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);  
      
  	}  


   	private void updateColor() {  

        r = (float)Math.sin(radian);  

       g = (float)Math.sin(radian / 2.0d);  

      b = (float)Math.sin(radian / 3.0d);  

    radian += uRadian;  

   }  
   

   	private float r;  

   	private float g;  

	private float b;  
   

    private double radian = 0.0d;  

    private double uRadian = Math.PI / 180.0d;  

} 