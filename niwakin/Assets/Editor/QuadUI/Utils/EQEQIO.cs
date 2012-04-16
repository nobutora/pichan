using UnityEngine;
using UnityEditor;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System;
namespace EqualsEquals
{
	public class EQEQIO
	{
		
		#region General I/O
		
		public static bool CreateTargetFolder(string fldr)
		{
			try
			{
				System.IO.Directory.CreateDirectory(fldr);
			}
			catch
			{
				EditorUtility.DisplayDialog("Error!", "Failed to create target folder!", "");
				return false;
			}
			
			return true;
		}
		
		#endregion
		
	}
}
