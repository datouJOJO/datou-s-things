#pragma once

#include"Camera.h"
#include"vectors.h"

class PerspectiveCamera:public Camera
{
public:

	PerspectiveCamera(Vec3f &center, Vec3f &direction, Vec3f &up, float angle);

	virtual Ray generateRay(Vec2f point);

	virtual float getTMin() const;

private:

	Vec3f m_Center;
	Vec3f m_Direction;
	Vec3f m_Up;
	Vec3f m_Horizontal;
	float m_Angle;	//偏角
	float m_d;	//焦距
	float m_Ratio;
};

