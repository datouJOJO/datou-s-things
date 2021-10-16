#include "Sphere.h"

bool Sphere::intersect(const Ray & r, Hit & h, float tmin)
{
	//���߷��� p(t)=r0+rd*t
	//��ȡ��������Ҫ�ı���
	Vec3f origin = r.getOrigin() - this->m_center;

	//cout << origin.x() << " " << origin.y() << " " << origin.z() << endl;
	//����ԭ�����ۻὫ������ ���Խ����ת����ԭ�㽻�����������
	Vec3f direction = r.getDirection();
	//һԪ���������󽻷���Ϊ
	//t^2+2*Rd*R0*t+R0*R0-r^2 = 0
	double a = direction.Dot3(direction);

	double b = 2 * direction.Dot3(origin);

	//cout << direction.x() << " " << direction.y() << " " << direction.z() << endl;
	//cout << origin.x() << " " << origin.y() << " " << origin.z() << endl;

	double c = origin.Dot3(origin)-this->m_radius*this->m_radius;
	//cout << a << " " << b << " " << c << endl;
	//�б�ʽ
	double  discriminant = b * b - 4 * a*c;

	if (discriminant > 0) {
		discriminant = sqrt(discriminant);
		double  t = (-b - discriminant) / 2 * a;

		if (t < tmin)
			t = (-b + discriminant) / 2 * a;

		if (t >= tmin && t < h.getT()) {

			//h.set(t, material, r);
			//���²��� �������˷�����

			Vec3f normal = r.getOrigin() + t * r.getDirection() - m_center;
			normal.Normalize();
			h.set(t, material, normal, r);
			//˵���ཻ��
			return true;
		}
	}
	return false;
}
