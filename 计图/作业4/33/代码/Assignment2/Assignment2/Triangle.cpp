#include "Triangle.h"

float det3x3(float a1, float a2, float a3,
	float b1, float b2, float b3,
	float c1, float c2, float c3);


Triangle::Triangle(Vec3f & a, Vec3f & b, Vec3f & c, Material * m)
{

	p1 = a;
	p2 = b;
	p3 = c;
	material = m;

	Vec3f::Cross3(m_n, (p2 - p1), (p3 - p1));
	m_n.Normalize();
}

bool Triangle::intersect(const Ray & r, Hit & h, float tmin)
{

	float belta, gamma, t;
	float A;
	
	A = det3x3(
		p1.x() - p2.x(), p1.x() - p3.x(), r.getDirection().x(),
		p1.y() - p2.y(), p1.y() - p3.y(), r.getDirection().y(),
		p1.z() - p2.z(), p1.z() - p3.z(), r.getDirection().z()
	);

	belta = det3x3(
		p1.x() - r.getOrigin().x(), p1.x() - p3.x(), r.getDirection().x(),
		p1.y() - r.getOrigin().y(), p1.y() - p3.y(), r.getDirection().y(),
		p1.z() - r.getOrigin().z(), p1.z() - p3.z(), r.getDirection().z()
	);
	gamma = det3x3(
		p1.x() - p2.x(), p1.x() - r.getOrigin().x(), r.getDirection().x(),
		p1.y() - p2.y(), p1.y() - r.getOrigin().y(), r.getDirection().y(),
		p1.z() - p2.z(), p1.z() - r.getOrigin().z(), r.getDirection().z()
	);
	t = det3x3(
		p1.x() - p2.x(), p1.x() - p3.x(), p1.x() - r.getOrigin().x(),
		p1.y() - p2.y(), p1.y() - p3.y(), p1.y() - r.getOrigin().y(),
		p1.z() - p2.z(), p1.z() - p3.z(), p1.z() - r.getOrigin().z()
	);
	belta /= A;
	gamma /= A;
	t /= A;

	if (belta > 1 || belta < 0) return false;
	if (gamma > 1 || gamma < 0) return false;
	if (1 - belta - gamma > 1 || 1 - belta - gamma < 0) return false;
	// hit
	if (t > tmin && t < h.getT())
	{
		h.set(t, material, m_n, r);
		return true;
	}
	return false;

}
