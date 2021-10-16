#pragma once
#include "Camera.h"


class OrthographicCamera:public Camera
{
public:
	OrthographicCamera(Vec3f &center, Vec3f &up, Vec3f & direction,int size) {
		this->m_center = center;
		this->m_up = direction;
		this->m_direction = up;
		this->m_size = size;

		//水平方向
		float temp = this->m_up.Dot3(this->m_direction);

		if (temp != 0) {

			//如果水平方向和面对朝向方向不为0 则寻找一个与面向方向垂直的向量
			Vec3f tempV(0, 0, 1);
			Vec3f::Cross3(this->m_up, tempV, this->m_direction);
		}

		if (this->m_direction.Length() != 1) {

			this->m_direction.Normalize();
		}

		if (this->m_up.Length() != 1) {
		
			this->m_up.Normalize();
		}

		Vec3f::Cross3(this->m_horizontal, this->m_direction, this->m_up);
	}

	virtual Ray generateRay(Vec2f point);

	virtual float getTMin()const;

private:
	Vec3f m_center;
	Vec3f m_up;
	Vec3f m_horizontal;
	Vec3f m_direction;
	int m_size;
};

