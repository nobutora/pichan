using UnityEngine;
using System.Collections;

public class ParticleScroller : ScrollBehaviour {

	// Use this for initialization
	void Update () 
	{
        Particle[] particles = particleEmitter.particles;
        int i = 0;
        while (i < particles.Length) {
            particles[i].position += new Vector3(getOneScrollValue(), 0, 0);
            i++;
        }
        particleEmitter.particles = particles;
	}
}
