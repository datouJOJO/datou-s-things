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
	void render(Image&img,int numOfPoint,int numOfIters);	//��Ⱦ
	int getTransformationIndex(float probability);
private:
	void clear() {
		TransMetrixs.clear();
		probabilities.clear();
	};
	int numOfTrans;		//�任�������
	vector<Matrix>TransMetrixs;		//�任��������
	vector<float>probabilities;		//��������
	int numOfMetrix;		//�任�������
	int numOfprobibility;	//������
};

