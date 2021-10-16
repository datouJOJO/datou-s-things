#pragma once
#ifndef OBJECT3D_H
#define OBJECT3D_H

#include "ray.h"
#include "hit.h"
#include "material.h"

class Object3D {

public:

	//一个缺省的构造函数和析构函数（constructor and destructor）

	Object3D() 
	{
	}

	virtual ~Object3D()
	{
	}
	//纯虚求交（intersection）方法
	virtual bool intersect(const Ray &r, Hit &h, float tmin) = 0;

protected:

	//指向材质Material实例的指针
	Material*material;

};

#endif