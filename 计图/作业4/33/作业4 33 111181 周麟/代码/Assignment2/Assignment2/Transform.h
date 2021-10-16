#pragma once
#include "matrix.h"
#include "Object3D.h"

class Transform:public Object3D
{
public:

	Transform(Matrix &m, Object3D *o);
	virtual bool intersect(const Ray& r, Hit& h, float tmin);

private:

	Matrix mat;
	Matrix mReverseMat;	//È¡·´
	Object3D* obj;
};

