using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/UI Components/Complimentary/PaddedElement2D")]
/** 
 	Attach this to any Element2D or ComplexComponent2D and whenever the OnResolutionChange message is received it will automatically reposition it based on the provided padding.
	
	Note: This is based on 2 factors, first being the pivot point of your object, and second the provided justification. To explain, it wouldn't make sense to make a Button2D with a Top-Left pivot justified Right as it will always be off screen!
*/
public class PaddedElement2D : MonoBehaviour {
	
	/** Represents 9 points of the screen that an object will be anchored to. */
	public enum Justification
	{
		TopLeft,TopCenter,TopRight,
		MiddleLeft,MiddleCenter,MiddleRight,
		BottomLeft,BottomCenter,BottomRight
	}
	
	/** Which of the 9 screen point are you basing your padding off of? */
	public Justification justification = PaddedElement2D.Justification.TopLeft;
	/** 
		Padding (in screen pixels) on x and y axis that will be taken account when positioning your object fluidly.
		Note: This is relative to your justification, as you are specifying a distance from your justifcation point.
	*/ 
	public Vector2 padding;
	
	Transform _transform;
	
	void Awake()
	{
		_transform = transform;
	}
	
	/** Message received when QuadUI detects that the resolution has been changed. */
	protected virtual void OnResolutionChange()
	{
		Vector3 pos = _transform.localPosition;
		Vector3 v = Vector3.zero;
		
		switch(justification)
		{
		case Justification.TopLeft:
			v = new Vector3(padding.x,padding.y,pos.z);
			break;
		case Justification.TopCenter:
			v = new Vector3(QuadUI.screenCenter.x+padding.x,padding.y,pos.z);	
			break;
		case Justification.TopRight:
			v = new Vector3(Screen.width-padding.x,padding.y,pos.z);
			break;			
		case Justification.MiddleLeft:
			v = new Vector3(padding.x,QuadUI.screenCenter.y,pos.z);
			break;			
		case Justification.MiddleCenter:
			v = new Vector3(QuadUI.screenCenter.x+padding.x,QuadUI.screenCenter.y+padding.y,pos.z);
			break;			
		case Justification.MiddleRight:
			v = new Vector3(Screen.width-padding.x,QuadUI.screenCenter.y,pos.z);
			break;			
		case Justification.BottomLeft:
			v = new Vector3(padding.x,Screen.height-padding.y,pos.z);
			break;
		case Justification.BottomCenter:
			v = new Vector3(QuadUI.screenCenter.x+padding.x,Screen.height-padding.y,pos.z);
			break;
		case Justification.BottomRight:
			v = new Vector3(Screen.width-padding.x,Screen.height-padding.y,pos.z);
			break;	
		}
		
		_transform.localPosition = v;
	}
}
