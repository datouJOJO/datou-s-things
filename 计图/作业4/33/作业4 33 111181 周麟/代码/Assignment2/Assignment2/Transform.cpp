#include "Transform.h"

Transform::Transform(Matrix & m, Object3D * o)
{

	mat = m;
	obj = o;
	mat.Inverse(mReverseMat);
}

bool Transform::intersect(const Ray & r, Hit & h, float tmin)
{
	Vec3f original = r.getOrigin();
	Vec3f dir = r.getDirection();

	mReverseMat.Transform(original);
	mReverseMat.TransformDirection(dir);


	Ray transformedRay(original, dir);

	if (obj->intersect(transformedRay, h, tmin))
	{
		Vec3f normal = h.getNormal();
		Matrix t;
		mReverseMat.Transpose(t);
		t.TransformDirection(normal);
		h.set(h.getT(), h.getMaterial(), normal, r);
		return true;
	}

	return false;
}
