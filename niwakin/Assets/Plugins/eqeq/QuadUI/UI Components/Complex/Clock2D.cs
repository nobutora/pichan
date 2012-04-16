using UnityEngine;
using System.Collections;

[AddComponentMenu("Quad UI/Complex Components/Clock2D")]
/*
 * A clock component typically used for displaying time in Minutes:Seconds format. 
 */
public class Clock2D : ComplexComponent2D
{
	/**
	 * Maximum number of seconds allowed. (5999.0F) The clock would read 99:59
	 * The clock can also count backwards or simply display a time if you set it manually.
	 */
	const float MAX_SECONDS = 5999.0F; //99 mins + 59 secs
	
	public Sprite2D minutesTens;
	public Sprite2D minutesOnes;
	public Element2D colon;
	public Sprite2D secondsTens;
	public Sprite2D secondsOnes;
	
	private int _minutesTens = 0;
	private int _minutesOnes = 0;
	private int _secondsTens = 0;
	private int _secondsOnes = 0;
	
	float _time;
	bool _on = false;
	bool _countDown = false;
	
	void Update()
	{
		if(!_on) return;
		
		if(_countDown) _time = Mathf.Clamp(_time - QuadUI.deltaTime,0,MAX_SECONDS);
		else _time = Mathf.Clamp(_time + QuadUI.deltaTime,0,MAX_SECONDS);
        
		TimeCode(_time);
	}
	
	/**
	 * Allows for manual setting of time on clock.
	 * */
	public void SetTimeManually(float seconds)
	{
		_time = Mathf.Clamp(seconds,0,MAX_SECONDS);
		TimeCode(_time);
	}
	
	/** 
	 * Starts the clock.
	 * 2 overloads, specify the amount of time to start from and whether or not this clock will count down.
	 * */
	public void StartClock()
	{
		_on = true;
	}
	
	public void StartClock(float secondsOnClock)
	{
		_time = Mathf.Clamp(secondsOnClock,0,MAX_SECONDS);
		StartClock();
	}
	
	public void StartClock(float secondsOnClock, bool countDown)
	{
		_countDown = countDown;
		StartClock(secondsOnClock);
	}
	
	/** Stops clock from counting */
	public void StopClock()
	{
		_on = false;
	}
	
	/** Resets clock to 0 seconds. */
	public void ResetClock()
	{
		_time = 0;
	}
	
	private void TimeCode(float t)
    {
        _minutesTens = (int)Mathf.FloorToInt(Mathf.Floor(t / 60F) / 10F);
		_minutesOnes = (int)Mathf.FloorToInt(t / 60F) % 10;
		_secondsTens = (int)Mathf.FloorToInt(Mathf.Floor(t % 60F) / 10F);
		_secondsOnes = (int)Mathf.FloorToInt(t % 60F) % 10;
				
		minutesTens.GoToFrame(_minutesTens);
        minutesOnes.GoToFrame(_minutesOnes);
        secondsTens.GoToFrame(_secondsTens);
        secondsOnes.GoToFrame(_secondsOnes);
    }
	
	/** 
	 * Returns the amount of time, in seconds, currently on the clock.
	 * */
	public float TimeInSeconds()
	{
		return _time;
	}
	
	/*
	public string TimeEncoded()
	{
		string encoded = "";
		return encoded;
	}
	*/
}
