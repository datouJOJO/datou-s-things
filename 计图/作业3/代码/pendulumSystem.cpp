
#include "pendulumSystem.h"

PendulumSystem::PendulumSystem(int numParticles,vector<particle>paticles):ParticleSystem(numParticles)
{
	m_numParticles = numParticles;
	this->m_paticles = paticles;
	this->isSingle = false;	//Ĭ���ǵ���
	// fill in code for initializing the state based on the number of particles
	// ��д������������ʼ��״̬�Ĵ���
	//�洢һ����СΪ2n�Ĵ�Vector3f���飬����λ�ô洢��ż���±��У����ٶȴ洢�������±���
	for (int i = 0; i < m_numParticles; i++) {
		
		// for this system, we care about the position and the velocity
		//�������ϵͳ�����ǹ��ĵ���λ�ú��ٶ�
		this->m_vVecState.push_back(Vector3f());
		this->m_vVecState.push_back(Vector3f(0, 0, 0));
	}
}


// TODO: implement evalF
// for a given state, evaluate f(X,t)
vector<Vector3f> PendulumSystem::evalF(vector<Vector3f> state)
{
	vector<Vector3f> f;

	// YOUR CODE HERE
	for(int i =0 ;i<m_paticles.size();i++){
		//�����ٶ� �����±�
		f.push_back(state[2*i+1]);
		//�ж������Ƿ�ֹ
		Vector3f totalForce;//����
		if(!this->m_paticles[i].isStatic){
			//�������ֹ
			//�������
			//����
			float g = this->m_paticles[i].m;
			Vector3f gravity(0,-g,0);	//ת��Ϊ������ʽ
			//����
			Vector3f elasticForce;
			if(!this->isSingle){
				for(int j = 0 ; j < this->m_paticles[i].link_index.size();j++){
					//λ���±�
					int index = this->m_paticles[i].link_index[j];
					Vector3f d = this->m_vVecState[2*i] - this->m_vVecState[2*index];

					elasticForce+= -this->m_paticles[i].elastic_coefficient[j] * (d.abs() - m_paticles[i].lenth[j]) * (d / d.abs());
				}
			}else{
				//�ǵ���
				Vector3f d = this->m_vVecState[2*i];

				elasticForce+= -this->m_paticles[i].elastic_coefficient[0] * (d.abs() - m_paticles[i].lenth[0]) * (d / d.abs());
			}

			//Ħ����
			Vector3f friction = -this->m_paticles[i].capacitance_coefficient*this->m_vVecState[2*i+1];
			//�������
			totalForce = gravity+elasticForce+friction;
		}
		//���ٶ�
		//��������
		f.push_back(totalForce/this->m_paticles[i].m);
	}
	return f;
}

// render the system (ie draw the particles)
void PendulumSystem::draw()
{
	for (int i = 0; i < m_numParticles; i++) {
		//ż���±걣��λ��
		Vector3f pos = this->m_vVecState[2 * i];//  position of particle i. YOUR CODE HERE
		glPushMatrix();
		glTranslatef(pos[0], pos[1], pos[2] );
		glutSolidSphere(0.075f,10.0f,10.0f);
		glPopMatrix();
	}
}
