
using UnityEngine;
using System.Collections;

public class EffectBase : ScrollBehaviour {
	
    
    private Vector2 _size;
    private Renderer _myRenderer;
    private int _lastIndex = -1;
	
	private int idxMax = 0;
	private int idxCount = 0;
	
	
	//FIRST READY
	public void firstEffect (int _uvTieX , int _uvTieY ,float f_alphe ) {

		_size = new Vector2 (1.0f / _uvTieX , 1.0f / _uvTieY);
		_myRenderer = renderer;
		
		idxMax = _uvTieX * _uvTieY ;

        int uIndex = 0 ;
        int vIndex = 0 ;
     	SetPictChange( uIndex , vIndex );
		
        if(_myRenderer == null)
            enabled = false;

		SetAlphe( f_alphe );
	}
	

	// BASIC MOVE
	public float MoveEffect (int _uvTieX , int _uvTieY , int _fps , float animeTime) {
	
		animeTime += Time.deltaTime;
		
		int index = (int)(animeTime * _fps) % (_uvTieX * _uvTieY);
        if(index != _lastIndex)
        {
            // split into horizontal and vertical index
            int uIndex = index % _uvTieX;
            int vIndex = index / _uvTieX;
      
            // build offset
            // v coordinate is the bottom of the image in opengl so we need to invert.
			SetPictChange( uIndex , vIndex );
            _lastIndex = index;
			idxCount++;
        }
		return animeTime;
	}
	public void ScrollMove()
	{
		// scroll 
		Scroll();
	}
	//ANIMESION TIMER GET
	public int GetIdxCount()
	{
		return idxCount ;
	}

	//UV CANGE
	public void SetPictChange(int uIndex , int vIndex)
	{
		Vector2 offset = new Vector2 (uIndex * _size.x, 1.0f - _size.y - vIndex * _size.y);
            
        _myRenderer.material.SetTextureOffset ("_MainTex", offset);
        _myRenderer.material.SetTextureScale ("_MainTex", _size);
	}
	
	public void SetScale( float w , float h )
	{
		Vector3 scale = new Vector3( 
				w ,
				h ,
				1 );
		transform.localScale = scale;
	}
	 
	//ALPHE CHANGE
	public void SetAlphe( float alphe )
	{

		Material mat ;
        //mat.shader = Shader.Find("Transparent/Diffuse");
		if(_myRenderer == null)
		{
			mat = this.renderer.material;
		}else
		{
			mat = _myRenderer.material;
		}

        Color color = mat.color;
        color.r = 1.0f;
        color.b = 1.0f;
        color.g = 1.0f;
        color.a = alphe ;
        mat.color = color;
	}


	public void SetAngle( float angle )
	{
		transform.eulerAngles = new Vector3(0, 0, angle);
	}

	
	
	
}
