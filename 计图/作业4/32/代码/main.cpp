// assighment1.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>
#include <assert.h>
#include "scene_parser.h"
#include "image.h"
#include "Group.h"
#include "Camera.h"
#include "vectors.h"
#include "ray.h"
#include "hit.h"
#include "material.h"

int main(int argc,char**argv)
{
	// ========================================================
// ========================================================
// Some sample code you might like to use for parsing 
// command line arguments 

	char *input_file = NULL;
	int width = 100;
	int height = 100;
	char *output_file = NULL;
	float depth_min = 0;
	float depth_max = 1;
	char *depth_file = NULL;

	// sample command line:
	// raytracer -input scene1_1.txt -size 200 200 -output output1_1.tga -depth 9 10 depth1_1.tga

	for (int i = 1; i < argc; i++) {
		if (!strcmp(argv[i], "-input")) {
			i++; assert(i < argc);
			input_file = argv[i];
		}
		else if (!strcmp(argv[i], "-size")) {
			i++; assert(i < argc);
			width = atoi(argv[i]);
			i++; assert(i < argc);
			height = atoi(argv[i]);
		}
		else if (!strcmp(argv[i], "-output")) {
			i++; assert(i < argc);
			output_file = argv[i];
		}
		else if (!strcmp(argv[i], "-depth")) {
			i++; assert(i < argc);
			depth_min = atof(argv[i]);
			i++; assert(i < argc);
			depth_max = atof(argv[i]);
			i++; assert(i < argc);
			depth_file = argv[i];
		}
		else {
			printf("whoops error with command line argument %d: '%s'\n", i, argv[i]);
			assert(0);
		}
		//std::cout << argv[i] << std::endl;
	}
	// ========================================================
	// ========================================================

	SceneParser sp(input_file);
	Image*image = new Image(width, height);
	Image*depthImage = new Image(width, height);

	Group *group = sp.getGroup();
	Vec3f background = sp.getBackgroundColor();
	Camera * camera = sp.getCamera();

	Vec3f black(0, 0, 0);

	image->SetAllPixels(background);
	depthImage->SetAllPixels(black);

	float x = 0.0, y = 0.0;
	//因为x,y的范围为0~1
	for (int i = 0; i < width; i++, x += 1.0 / width) {
		y = 0.0;
		for (int j = 0; j < height; j++, y += 1.0 / height) {
		
			Ray ray = camera->generateRay(Vec2f(x, y));
			Hit hit(FLT_MAX, NULL);
			//cout << x << " " << y << endl;
			if (group->intersect(ray, hit, camera->getTMin())) {
				Material*m = hit.getMaterial();
				image->SetPixel(i, j, m->getDiffuseColor());
				float t = hit.getT();

				if (t > depth_max || t < depth_min)
					continue;

				float grayComponent = (t - depth_min) / (depth_max - depth_min);
				grayComponent = 1 - grayComponent;

				Vec3f color(grayComponent, grayComponent, grayComponent);
				depthImage->SetPixel(i, j, color);
			}
		}
	}

	image->SaveTGA(output_file);
	depthImage->SaveTGA(depth_file);
	return 0;
}

// 运行程序: Ctrl + F5 或调试 >“开始执行(不调试)”菜单
// 调试程序: F5 或调试 >“开始调试”菜单

// 入门使用技巧: 
//   1. 使用解决方案资源管理器窗口添加/管理文件
//   2. 使用团队资源管理器窗口连接到源代码管理
//   3. 使用输出窗口查看生成输出和其他消息
//   4. 使用错误列表窗口查看错误
//   5. 转到“项目”>“添加新项”以创建新的代码文件，或转到“项目”>“添加现有项”以将现有代码文件添加到项目
//   6. 将来，若要再次打开此项目，请转到“文件”>“打开”>“项目”并选择 .sln 文件
