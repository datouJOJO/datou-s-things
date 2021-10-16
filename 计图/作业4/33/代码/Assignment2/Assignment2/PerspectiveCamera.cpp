#include "PerspectiveCamera.h"

PerspectiveCamera::PerspectiveCamera(Vec3f &center, Vec3f &direction, Vec3f & up, float angle)
{	
	//构造函数
	//两个相互垂直的向量就能确定一个相互垂直的正交向量
	m_Center = center;
	m_Up = up;
	m_Direction = direction;
	m_Angle = angle;

	//cout << "center" << m_Center << endl;
	//cout << "up" << m_Up << endl;
	//cout << "direction" << m_Direction << endl;

	float cos = m_Up.Dot3(m_Direction);

	if (cos != 0) {
	
		Vec3f temp;
		Vec3f::Cross3(temp, m_Direction, m_Up);
		Vec3f::Cross3(m_Up, temp, m_Direction);
	}

	//单位化
	if (m_Up.Length() != 1) {
	
		m_Up.Normalize();
	}

	if (m_Direction.Length() != 1) {
	
		m_Direction.Normalize();
	}

	Vec3f::Cross3(m_Horizontal, m_Direction, m_Up);

	m_d = 1.0f / tanf(m_Angle / 2.0);
	m_Ratio = 1.0;
}

Ray PerspectiveCamera::generateRay(Vec2f point)
{
	float x_ndc = point.x();
	float y_ndc = point.y();

	float screenWidth = 0.f;
	float screenHeight = 0.f;

	if (m_Ratio > 1.f)
	{
		screenWidth = 2 * m_Ratio;
		screenHeight = 2.f;
	}
	else
	{
		screenWidth = 2.f;
		screenHeight = 2 * m_Ratio;
	}

	//float height = 2 * tan(mAngle * PI / 360.0);
	//float width = height * mRatio;

	float left = -screenWidth / 2.0;
	float top = -screenHeight / 2.0;

	float u = x_ndc * screenWidth + left;
	float v = y_ndc * screenHeight + top;

	float near = screenHeight / (2.f * tanf(m_Angle / 2.0));

	Vec3f originalDir = near * m_Direction + u * m_Horizontal + v * m_Up;

	if (originalDir.Length() != 0)
	{
		originalDir.Normalize();
	}

	Ray r(m_Center, originalDir);

	return r;
}

float PerspectiveCamera::getTMin() const
{
	//最小值
	return -1.0f*FLT_MAX;
}
