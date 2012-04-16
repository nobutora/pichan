using UnityEngine;
using System.Collections;

public class MenuButtomMove : MonoBehaviour {
	

	public Texture MenuTexture ;


	void OnGUI () 
	{

		GameManager manager = GameManager.Instance;
		
		float w  =  MenuTexture.width  * manager.getScreenW() ;
		float h  =  MenuTexture.height * manager.getScreenH() ;

		float x = Screen.width -  w * 1.5f;
		
		float y = Screen.height - h * 1.5f;

		
		//Back
		GUI.DrawTexture( new Rect(
			x ,
			y ,
			w ,
			h ),
		 MenuTexture );

		


	}
}
