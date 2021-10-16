#pragma once

#include "Object3D.h"
#include "vectors.h"
#include "material.h"

class Plane:public Object3D
{
public:
	Plane(Vec3f &normal, float d, Material *m);
	bool intersect(const Ray &r, Hit &h, float tmin);

private:

	Vec3f m_Normal;
	float m_d;
};