using UnityEngine;
using System.Collections;

/** 
  	Will the object be scaled on either the X or Y axis?
	Note: This currently only supports Horizontal for this build.
*/
public enum ScaleAxis{Horizontal}//,Vertical}

[AddComponentMenu("Quad UI/Complex Components/ResizableBar2D")]
/**
 * This class is great for progress and health bars.
 * You can change the size of the middle segment (bar) by the function Scale(float percent)
 * 
 * */
public class ResizableBar2D : ComplexComponent2D
{
	public ScaleAxis axis = ScaleAxis.Horizontal;
	
	public float minLength = 1;
	public float maxLength = 100;
	float _percentage = 0;
	
	public Quad2D cap1;
	public Quad2D cap2;
	public Quad2D bar;
	
	
	/**
	 * modifies the scale of the middle segment.
	 * percent is represented as a float 0 to 1
	 */
	public void Scale(float percent)
	{
		_percentage = percent;
		int _percentAsScale = (int) Mathf.Round( (maxLength-minLength) * percent);
		
		if(axis == ScaleAxis.Horizontal)
		{
			bar.Scale(new Vector3(_percentAsScale,1,1));
			Vector3 barPosition = bar.GetPosition();
			cap2.MovePosition(new Vector3(barPosition.x + (bar.dimensions.x*bar.GetScale().x),barPosition.y,barPosition.z),true);
		}/*
		else if(axis == ScaleAxis.Vertical)
		{
			bar.Scale(new Vector3(1,_percentAsScale,1));
			Vector3 barPosition = bar.GetPosition();
			cap2.MovePosition(new Vector3(barPosition.x,barPosition.y + (bar.dimensions.y*bar.GetScale().y),barPosition.z),true);
		}*/
	}
	
	public float percentage
	{
		get{return _percentage;}
	}
	
}
