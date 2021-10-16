#pragma once
#ifndef OBJECT3D_H
#define OBJECT3D_H

#include "ray.h"
#include "hit.h"
#include "material.h"

class Object3D {

public:

	//һ��ȱʡ�Ĺ��캯��������������constructor and destructor��

	Object3D() 
	{
	}

	virtual ~Object3D()
	{
	}
	//�����󽻣�intersection������
	virtual bool intersect(const Ray &r, Hit &h, float tmin) = 0;

protected:

	//ָ�����Materialʵ����ָ��
	Material*material;

};

#endif