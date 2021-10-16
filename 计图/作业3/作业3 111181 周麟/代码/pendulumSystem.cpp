
#include "pendulumSystem.h"

PendulumSystem::PendulumSystem(int numParticles,vector<particle>paticles):ParticleSystem(numParticles)
{
	m_numParticles = numParticles;
	this->m_paticles = paticles;
	this->isSingle = false;	//默认是单摆
	// fill in code for initializing the state based on the number of particles
	// 填写根据粒子数初始化状态的代码
	//存储一个大小为2n的大Vector3f数组，其中位置存储在偶数下标中，而速度存储在奇数下标中
	for (int i = 0; i < m_numParticles; i++) {
		
		// for this system, we care about the position and the velocity
		//对于这个系统，我们关心的是位置和速度
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
		//保存速度 奇数下标
		f.push_back(state[2*i+1]);
		//判断物体是否静止
		Vector3f totalForce;//合力
		if(!this->m_paticles[i].isStatic){
			//如果不静止
			//计算分力
			//重力
			float g = this->m_paticles[i].m;
			Vector3f gravity(0,-g,0);	//转化为向量形式
			//弹力
			Vector3f elasticForce;
			if(!this->isSingle){
				for(int j = 0 ; j < this->m_paticles[i].link_index.size();j++){
					//位置下标
					int index = this->m_paticles[i].link_index[j];
					Vector3f d = this->m_vVecState[2*i] - this->m_vVecState[2*index];

					elasticForce+= -this->m_paticles[i].elastic_coefficient[j] * (d.abs() - m_paticles[i].lenth[j]) * (d / d.abs());
				}
			}else{
				//是单摆
				Vector3f d = this->m_vVecState[2*i];

				elasticForce+= -this->m_paticles[i].elastic_coefficient[0] * (d.abs() - m_paticles[i].lenth[0]) * (d / d.abs());
			}

			//摩擦力
			Vector3f friction = -this->m_paticles[i].capacitance_coefficient*this->m_vVecState[2*i+1];
			//计算合力
			totalForce = gravity+elasticForce+friction;
		}
		//加速度
		//保存数据
		f.push_back(totalForce/this->m_paticles[i].m);
	}
	return f;
}

// render the system (ie draw the particles)
void PendulumSystem::draw()
{
	for (int i = 0; i < m_numParticles; i++) {
		//偶数下标保存位置
		Vector3f pos = this->m_vVecState[2 * i];//  position of particle i. YOUR CODE HERE
		glPushMatrix();
		glTranslatef(pos[0], pos[1], pos[2] );
		glutSolidSphere(0.075f,10.0f,10.0f);
		glPopMatrix();
	}
}
