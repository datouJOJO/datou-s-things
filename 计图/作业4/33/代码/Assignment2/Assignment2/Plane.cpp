#include "Plane.h"

Plane::Plane(Vec3f & normal, float d, Material * m)
{
	m_Normal = normal;
	material = m;
	m_d = d;
}

bool Plane::intersect(const Ray & r, Hit & h, float tmin)
{
	//求向量与平面的交点的公式为
	//t = -(D+n*R0)/n*Rd
	Vec3f origin = r.getOrigin();
	Vec3f direction = r.getDirection();

	float a = origin.Dot3(m_Normal);
	float b = direction.Dot3(m_Normal);

	double t = (m_d - a) / b;
	if (t < tmin||t>h.getT())
		return false;

	h.set(t, material, m_Normal, r);
	return true;

}
