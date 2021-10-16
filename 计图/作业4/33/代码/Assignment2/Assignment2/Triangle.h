#pragma once
#include "Object3D.h"

class Triangle:public Object3D
{
public:
	Triangle(Vec3f &a, Vec3f &b, Vec3f &c, Material *m);
	bool intersect(const Ray &r, Hit &h, float tmin);

private:
	Vec3f p1;
	Vec3f p2;
	Vec3f p3;
	Vec3f m_n;
};

