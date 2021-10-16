#include "Sphere.h"

bool Sphere::intersect(const Ray & r, Hit & h, float tmin)
{
	//光线方程 p(t)=r0+rd*t
	//获取方程中需要的变量
	Vec3f origin = r.getOrigin() - this->m_center;

	//cout << origin.x() << " " << origin.y() << " " << origin.z() << endl;
	//基于原点讨论会将问题简便 所以将起点转成与原点交点问题的讨论
	Vec3f direction = r.getDirection();
	//一元二次球面求交方程为
	//t^2+2*Rd*R0*t+R0*R0-r^2 = 0
	double a = direction.Dot3(direction);

	double b = 2 * direction.Dot3(origin);

	//cout << direction.x() << " " << direction.y() << " " << direction.z() << endl;
	//cout << origin.x() << " " << origin.y() << " " << origin.z() << endl;

	double c = origin.Dot3(origin)-this->m_radius*this->m_radius;
	//cout << a << " " << b << " " << c << endl;
	//判别式
	double  discriminant = b * b - 4 * a*c;

	if (discriminant > 0) {
		discriminant = sqrt(discriminant);
		double  t = (-b - discriminant) / 2 * a;

		if (t < tmin)
			t = (-b + discriminant) / 2 * a;

		if (t >= tmin && t < h.getT()) {

			//h.set(t, material, r);
			//更新部分 还引入了法向量

			Vec3f normal = r.getOrigin() + t * r.getDirection() - m_center;
			normal.Normalize();
			h.set(t, material, normal, r);
			//说明相交了
			return true;
		}
	}
	return false;
}
