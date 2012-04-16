using UnityEngine;
using System.Collections;

/** Attach this to any Element2D or ComplexComponent2D and whenever the OnResolutionChange message is received it will automatically reposition it based on the normalized (0 to 1) screen position you provide.*/
[AddComponentMenu("Quad UI/UI Components/Complimentary/FluidElement2D")]
public class FluidElement2D : MonoBehaviour
{
	/** Position in a 0 to 1 space where 0,0 is top-left and 1,1 is bottom-right*/
	public Vector2 normalizedScreenPosition = new Vector2(0,0);
	Transform _transform;
	
	void Awake()
	{
		_transform = transform;
	}
	
	/** Message received when QuadUI detects that the resolution has been changed. */
	protected virtual void OnResolutionChange()
	{
		Vector2 pixelPosition = new Vector2(normalizedScreenPosition.x * Screen.width, normalizedScreenPosition.y * Screen.height);
		_transform.localPosition = new Vector3(pixelPosition.x, pixelPosition.y, _transform.localPosition.z);
	}
}
