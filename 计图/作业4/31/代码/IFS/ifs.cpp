#include "ifs.h"

void ifs::setNumOfTrans(int num)
{
	this->numOfTrans = num;
	clear();
}

void ifs::addMetrix(Matrix m)
{
	if (this->numOfMetrix == this->numOfTrans)return;
	this->TransMetrixs.push_back(m);
	this->numOfMetrix++;
}

void ifs::addProbibility(float p)
{
	if (this->numOfTrans == this->numOfprobibility)return;
	this->probabilities.push_back(p);
	this->numOfprobibility++;
}

void ifs::render(Image&img, int numOfPoint, int numOfIters)
{
	time_t t;
	srand((unsigned)time_t(&t));
	int width = img.Width();
	int height = img.Height();
	//判断长宽是否大于0
	assert(height > 0 && width > 0);

	//设置背景颜色
	//白色
	Vec3f background(255.0f, 255.0f, 255.0f);
	img.SetAllPixels(background);

	//粒子颜色
	Vec3f black(0.0f, 0.0f, 0.0f);

	//随机点的坐标
	float p_x = 0.0;
	float p_y = 0.0;

	Vec3f interPoint(0.0, 0.0, 0.0);
	for (int j = 0; j < numOfPoint; j++) {
		//获取随机点的坐标
		p_x = (float)(rand()) / (float)(RAND_MAX);
		p_y = (float)(rand()) / (float)(RAND_MAX);
		interPoint.Set(p_x, p_y, 0.0f);

		for (int i = 0; i < numOfIters; i++) {

			int indexProbility = 0;
			float probability = (float)(rand()) / (float)(RAND_MAX);

			double p = this->probabilities[0];
			//while (p <= probability&&indexTrans < this->numOfTrans) {
			//	assert(indexTrans < this->numOfTrans);
			//	p += this->probabilities[++indexTrans];
			//}

			int indexTrans = getTransformationIndex(probability);

			if (indexTrans >= this->numOfTrans) {
				indexTrans = this->numOfTrans - 1;
			}
			Matrix m = this->TransMetrixs[indexTrans];
			//对interPoint做m变换
			m.Transform(interPoint);
		}
		p_x = interPoint[0];
		p_y = interPoint[1];

		if (p_x >= 0 && p_x < 1 && p_y >= 0 && p_y < 1) {
			img.SetPixel(p_x*width, p_y*height, black);
		}
	}
}

int ifs::getTransformationIndex(float probability)
{
	float * pTemp = new float[this->numOfTrans];
	memset(pTemp, 0, this->numOfTrans * sizeof(float));

	int i;
	pTemp[0] = this->probabilities[0];
	for (i = 1; i < this->numOfTrans; i++)
	{
		pTemp[i] = pTemp[i - 1] + this->probabilities[i];
	}

	for (i = 0; i < this->numOfTrans; i++)
	{
		if (probability <= pTemp[i])
			break;
	}

	delete[] pTemp;

	return i;
}
