#pragma once
#include "Object3D.h"


class Group:public Object3D
{

public:

	Group(int num) {

		this->numOfObjects = num;
		//���ٿռ�
		this->m_objects = new Object3D*[this->numOfObjects];
	};

	~Group() {
		
		//�ͷſռ�
		delete[]m_objects;
	};

	virtual bool intersect(const Ray &r, Hit &h, float tmin);
	void addObject(int index, Object3D *obj);

private:
	int numOfObjects;
	Object3D**m_objects;
};

