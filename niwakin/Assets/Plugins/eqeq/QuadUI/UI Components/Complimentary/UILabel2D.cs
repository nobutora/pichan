using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/UI Components/Complimentary/UILabel2D")]
/**
 * A UILabel will follow a 3D position in 2D space. Simply attach to the Quad2D's Game Object and reference a target Transform, and you're good!
 * Currently only uses the Main Camera's screen coordinates.
 * */
public class UILabel2D : MonoBehaviour
{	
	public Transform target;
	Transform _transform;
	Camera _cam;
	
	public Vector2 pixelOffset = Vector2.zero;
	public Vector3 offset3D = Vector3.zero;
	Vector3 screenpos;
	
	float _screenHeight = -1;
	
	// Use this for initialization
	void Awake() 
	{
		_transform = transform;
		_cam = Camera.main;
	}
	
	// Update is called once per frame
	void LateUpdate() 
	{
		if(_screenHeight < 0) _screenHeight = QuadUI.Instance.screenDimensions.y;
		if(!target) return;
		screenpos = _cam.WorldToScreenPoint(target.position + offset3D);
		//screenpos = new Vector3(screenpos.x,-screenpos.y,screenpos.z);
		_transform.localPosition = new Vector3(screenpos.x + pixelOffset.x, _screenHeight - (screenpos.y + pixelOffset.y), 0);
		//print(screenpos + "|" + _transform.localPosition);
	}
	
	protected void OnResolutionChange()
	{
		#if UNITY_EDITOR
		_screenHeight = QuadUI.Instance.screenDimensions.y;
		#else
		_screenHeight = Screen.currentResolution.height;
		#endif
	}
}
