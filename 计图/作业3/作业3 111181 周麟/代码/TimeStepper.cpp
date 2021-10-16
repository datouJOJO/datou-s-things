#include "TimeStepper.hpp"

///TODO: implement Explicit Euler time integrator here
void ForwardEuler::takeStep(ParticleSystem* particleSystem, float stepSize)
{
	//获得状态
	vector<Vector3f>state = particleSystem->getState();
	//求导
	vector<Vector3f>f = particleSystem->evalF(state);
	//更新状态
	//X(t + h)=X + hf(X,t)
	for(int i = 0 ; i < state.size() ; i++){
		state[i] = state[i] + stepSize * f[i];
	}
	//设置当前状态速度
	particleSystem->setState(state);
}

///TODO: implement Trapzoidal rule here
void Trapzoidal::takeStep(ParticleSystem* particleSystem, float stepSize)
{
	//获得状态
	vector<Vector3f>state0 = particleSystem->getState();
	vector<Vector3f>state1 = particleSystem->getState();
	//求导
	vector<Vector3f>f0 = particleSystem->evalF(state1);
	//更新状态
	//X(t + h)=X + hf(X,t)
	for(int i = 0 ; i < state1.size() ; i++){
		state1[i] = state1[i] + stepSize * f0[i];
	}
	vector<Vector3f>f1 = particleSystem->evalF(state1);
	//X(t + h)=X + h(f0+f1)/2
	for(int i = 0 ; i < state0.size() ; i++){
		state0[i] = state0[i] + stepSize * (f0[i]+f1[i])/2;
	}
	//设置当前状态速度
	particleSystem->setState(state0);
}
