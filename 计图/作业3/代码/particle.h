#pragma once
#include<vector>
using namespace std;

class particle{
public:

	particle(){
		//初始化
		capacitance_coefficient = 0.0;

		m = 0.0;

		//初始化粒子不静止
		isStatic = false;
	}

	vector<int>link_index;	//连接粒子位置下标

	vector<float>elastic_coefficient;	//连接绳子的弹性系数

	float capacitance_coefficient;	//摩擦系数

	vector<float>lenth;	//连接绳子静态时的长度

	float m;	//粒子质量

	bool isStatic;	//是否静止
	
};