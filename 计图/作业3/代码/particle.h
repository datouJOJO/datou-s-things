#pragma once
#include<vector>
using namespace std;

class particle{
public:

	particle(){
		//��ʼ��
		capacitance_coefficient = 0.0;

		m = 0.0;

		//��ʼ�����Ӳ���ֹ
		isStatic = false;
	}

	vector<int>link_index;	//��������λ���±�

	vector<float>elastic_coefficient;	//�������ӵĵ���ϵ��

	float capacitance_coefficient;	//Ħ��ϵ��

	vector<float>lenth;	//�������Ӿ�̬ʱ�ĳ���

	float m;	//��������

	bool isStatic;	//�Ƿ�ֹ
	
};