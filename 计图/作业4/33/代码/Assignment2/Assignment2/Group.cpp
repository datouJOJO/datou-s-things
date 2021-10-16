#include "Group.h"

bool Group::intersect(const Ray & r, Hit & h, float tmin)
{

	bool res = false;

	for (int i = 0; i < this->numOfObjects; i++) {
		if (this->m_objects[i]->intersect(r, h, tmin)) {
			res = true;
		}
	}

	return res;
}

void Group::addObject(int index, Object3D * obj)
{

	this->m_objects[index] = obj;
}
