#include "OrthographicCamera.h"

#define T_MIN -100000.0
Ray OrthographicCamera::generateRay(Vec2f point)
{
	//使用公式 
	//origin = center+(x-0.5)*size*horizontal+(y-0.5)*size*up
	//把求得的origin和成员变量m_direction传给类ray

	Vec3f origin = m_center + (point.x() - 0.5) * m_size * m_horizontal + (point.y() - 0.5) * m_size * m_up;

	//cout << m_center << endl;
	//cout << m_horizontal << endl;
	//cout << m_up << endl;
	//cout << this->m_direction << endl;
	//cout << "--------------" << endl;
	Ray ray(origin,this->m_direction);

	return ray;
}

float OrthographicCamera::getTMin()const
{
	return T_MIN;
}
