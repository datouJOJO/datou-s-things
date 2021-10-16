//
// originally implemented by Justin Legakis
//
#include "matrix.h"
#include "vectors.h"
#include "image.h"
#include "ifs.h"
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <string.h>


int main(int argc, const char** argv)
{
	const char *input_file = NULL;
	int point = 0;
	int size = 0;
	int iters = 0;
	int width = 100;
	int height = 100;
	const char *output_file = NULL;
	float depth_min = 0;
	float depth_max = 1;
	const char *depth_file = NULL;

	// sample command line:
	// raytracer -input scene1_1.txt -size 200 200 -output output1_1.tga -depth 9 10 depth1_1.tga
	//文件、点数、迭代次数、输出图像大小和输出文件
	for (int i = 1; i < argc; i++) {
		if (!strcmp(argv[i], "-input")) {
			//文件
			i++; assert(i < argc);
			input_file = argv[i];
		}
		else if (!strcmp(argv[i], "-points")) {
			//点数
			i++; assert(i < argc);
			point = atoi(argv[i]);
		}
		else if (!strcmp(argv[i], "-iters")) {
			//迭代次数
			i++; assert(i < argc);
			iters = atoi(argv[i]);
		}
		else if (!strcmp(argv[i], "-size")) {
			//输出图像大小
			i++; assert(i < argc);
			size = atoi(argv[i]);
		}
		else if (!strcmp(argv[i], "-output")) {
			//输出文件
			i++; assert(i < argc);
			output_file = argv[i];
		}
		else {
			printf("whoops error with command line argument %d: '%s'\n", i, argv[i]);
			assert(0);
		}
	}

	FILE* f = fopen(input_file, "r");
	if (f == 0)
	{
		printf("can't open input_file");
			return false;
	}
	int n;
	// Read each line.
	fscanf(f, "%d", &n);
	//std::cout << n;

	//----------------读入文件--------------------
	FILE*input = fopen(input_file, "r");
	assert(input != NULL);

	ifs Ifs;
	int numOftrans;
	//变换矩阵数
	fscanf(input, "%d", &numOftrans);
	Ifs.setNumOfTrans(numOftrans);

	//保存随机数和变换矩阵的值
	for (int i = 0; i < numOftrans; i++) {
		//随机数
		float probability;
		fscanf(input, "%f", &probability);
		Ifs.addProbibility(probability);
		//变换矩阵
		Matrix m;
		m.Read3x3(input);
		Ifs.addMetrix(m);
	}

	//-----------------初始化矩阵 image类----------
	//图片尺寸 size * size
	Image img = Image(size,size);
	Ifs.render(img,point,iters);
	img.SaveTGA(output_file);
	fclose(input);
	return 0;
}