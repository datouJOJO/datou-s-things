#include "ClothSystem.h"

//TODO: Initialize here
ClothSystem::ClothSystem(int num ,vector<particle> particles):PendulumSystem(num* num, particles)
{
	this->m_num = num;
	this->m_paticles = particles;
}


// TODO: implement evalF
// for a given state, evaluate f(X,t)
vector<Vector3f> ClothSystem::evalF(vector<Vector3f> state)
{

	vector<Vector3f> f = PendulumSystem::evalF(state);

	for (int i = 0; i < this->m_numParticles; i++) {
		if (!m_paticles[i].isStatic)
			f[2 * i + 1] = f[2 * i + 1] + windForce / m_paticles[i].m;//�ϼ��ٶ� 
	}

	return f;
}

///TODO: render the system (ie draw the particles)
void ClothSystem::draw()
{
	//����Բ��
	glPushMatrix();
	GLfloat sphereColor[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	GLfloat Color[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, sphereColor);
	glutSolidSphere(1.0f, 50.0f, 50.0f);
	glPopMatrix();

	//printf("�Ѿ�������");
	if(this->isString){
		//���ṹ���ɲ���
		glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, Color);
		glBegin(GL_TRIANGLES);
		for (int i = 0; i < m_num - 1; i++) {
			for (int j = 0; j < m_num - 1; j++) {

				Vector3f v1 = this->m_vVecState[2 * (i * m_num + j)];
				Vector3f v2 = this->m_vVecState[2 * (i * m_num + 1 + j)];
				Vector3f v3 = this->m_vVecState[2 * ((i + 1) * m_num + j)];
				Vector3f v4 = this->m_vVecState[2 * ((i + 1) * m_num + 1 + j)];

				//�ж�������;�ֹ�������ٶ�Ϊ0
				//������Զ��ľ���
				if (v1.abs() - 1.0f <= 0.1) {
					m_paticles[(i * m_num + j)].isStatic = true;
					m_vVecState[2 * (i * m_num + j)+1] = 0;
				}

				if (v2.abs() - 1.0f <= 0.1) {
					m_paticles[ (i * m_num + 1 + j)].isStatic = true;
					m_vVecState[2 * (i * m_num + 1 + j)+1] = 0;
				}

				if (v3.abs() - 1.0f <= 0.1) {
					m_paticles[ ((i + 1) * m_num + j)].isStatic = true;
					m_vVecState[2 * ((i + 1) * m_num + j)+1] = 0;
				}

				if (v4.abs() - 1.0f <= 0.1) {
					m_paticles[((i + 1) * m_num + 1 + j)].isStatic = true;
					m_vVecState[2 * ((i + 1) * m_num + 1 + j)+1] = 0;
				}
			
				//���㷨����
				Vector3f n1 = Vector3f::cross(v1 - v2, v1 - v3);
				Vector3f n2 = Vector3f::cross(v4 - v3, v3 - v2);

				//������������
				glNormal3f(n1.x(), n1.y(), n1.z());	//�趨������
				glVertex3f(v1.x(), v1.y(), v1.z());
				glVertex3f(v2.x(), v2.y(), v2.z());
				glVertex3f(v3.x(), v3.y(), v3.z());

				glNormal3f(n2.x(), n2.y(), n2.z());
				glVertex3f(v4.x(), v4.y(), v4.z());
				glVertex3f(v3.x(), v3.y(), v3.z());
				glVertex3f(v2.x(), v2.y(), v2.z());
			}
		}
		glEnd();
	}else{
		//���ṹ����
		glEnable(GL_LINE_SMOOTH);
		glBegin(GL_LINES);
		for (int i = 0; i < this->m_paticles.size(); i++) {
			for (int j = 0; j < 4; j++) {
				if (m_paticles[i].lenth[0] != m_paticles[i].lenth[j])
					continue;
				//���ڵ��λ������
				int index = m_paticles[i].link_index[j];
				Vector3f p1 = m_vVecState[2 * i];

				//���ڵ�
				Vector3f p2 = m_vVecState[2 * index];

				//����ԭ��ľ��������ľ���֮��
				if (p1.abs() - 1.0f <= 0.1) {
					m_paticles[i].isStatic = true;//���Ӿ�ֹ
					m_vVecState[2 * i + 1] = 0;//�ٶ�Ϊ0
				}
				if (p2.abs() - 1.0f <= 0.1) {
					m_paticles[index].isStatic = true;
					m_vVecState[2 * index + 1] = 0;
				}
				//����
				glVertex3f(p1.x(), p1.y(), p1.z());
				glVertex3f(p2.x(), p2.y(), p2.z());
			}
		}
		glEnd();
	}
}

