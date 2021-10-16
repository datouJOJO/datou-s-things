#pragma once
#include "image.h"
#include "matrix.h"
#include <vector>

class ifs
{
public:
	ifs() {
		numOfTrans = 0;
		numOfMetrix = 0;
		numOfprobibility = 0;
	}
	~ifs() {
		clear();
	}

	void setNumOfTrans(int num);
	void addMetrix(Matrix m);
	void addProbibility(float p);
	void render(Image&img,int numOfPoint,int numOfIters);	//渲染
	int getTransformationIndex(float probability);
private:
	void clear() {
		TransMetrixs.clear();
		probabilities.clear();
	};
	int numOfTrans;		//变换矩阵个数
	vector<Matrix>TransMetrixs;		//变换矩阵数组
	vector<float>probabilities;		//概率数组
	int numOfMetrix;		//变换矩阵个数
	int numOfprobibility;	//概率数
};

