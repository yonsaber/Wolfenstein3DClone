package com.base.engine;

public class SpotLight {
	private PointLight pointLight;
	private Vector3f direction;
	private float cutOff;

	public SpotLight(PointLight pointLight, Vector3f direction, float cutOff) {
		this.pointLight = pointLight;
		this.direction = direction.normalized();
		this.cutOff = cutOff;
	}
	
	public PointLight getPointLight() {
		return pointLight;
	}
	public void setPointLight(PointLight pointLight) {
		this.pointLight = pointLight;
	}
	public Vector3f getDirection() {
		return direction;
	}
	public void setDirection(Vector3f direction) {
		this.direction = direction.normalized();
	}
	public float getCutOff() {
		return cutOff;
	}
	public void setCutOff(float cutOff) {
		this.cutOff = cutOff;
	}
}
