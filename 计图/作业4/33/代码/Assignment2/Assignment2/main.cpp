// assighment2.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//
#pragma warning(disable:4996)
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
#include "light.h"

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
	char *normal_file = NULL;
	bool isShadeBack = false;

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
		else if (!strcmp(argv[i], "-normals")) {
			i++; assert(i < argc);
			normal_file = argv[i];
		}
		else if (!strcmp(argv[i], "-shade_back")) {
			isShadeBack = true;
		}
		else {
			printf("whoops error with command line argument %d: '%s'\n", i, argv[i]);
			assert(0);
		}
		//std::cout << argv[i] << std::endl;
	}
	// ========================================================
	// ========================================================

	SceneParser* sceneParser = new SceneParser(input_file);
	int num_materials = sceneParser->getNumMaterials();
	int num_lights = sceneParser->getNumLights();
	Camera* camera = sceneParser->getCamera();
	Group* group = sceneParser->getGroup();
	Vec3f background_color = sceneParser->getBackgroundColor();
	Vec3f ambient_light = sceneParser->getAmbientLight();
	Image* image = new Image(width, height);
	image->SetAllPixels(background_color);
	Image* depth_image = new Image(width, height);
	depth_image->SetAllPixels(Vec3f(0.0, 0.0, 0.0));
	Image* normal_image = new Image(width, height);
	normal_image->SetAllPixels(Vec3f(0.0, 0.0, 0.0));

	float precalc = depth_max - depth_min;
	if (precalc == 0) {
		precalc = 1.0f;
	}

	for (int j = 0; j < height; j++){
		for (int i = 0; i < width; i++){
			float x = (i + 0.5) / width;
			float y = (j + 0.5) / height;
			Material* material = sceneParser->getMaterial(0);
			Ray ray = camera->generateRay(Vec2f(x, y));
			Hit* hit = new Hit(INT_MAX, material, Vec3f(0.0f, 0.0f, 0.0f));
			float tmin = 0;
			if (group->intersect(ray, *hit, tmin)) {
				Vec3f color = hit->getMaterial()->getDiffuseColor();
				Vec3f pixel = Vec3f(color.x() * ambient_light.x(), color.y() * ambient_light.y(), color.z() * ambient_light.z());
				Vec3f normal = hit->getNormal();
				if (isShadeBack) {
					if (normal.Dot3(ray.getDirection()) > 0) {
						normal = normal * -1.0f;
					}
				}
				if (normal.Dot3(ray.getDirection()) <= 0) {
					Vec3f intersect_point = ray.getOrigin() + hit->getT() * ray.getDirection();
					for (int p = 0; p < num_lights; p++) {
						Light* light = sceneParser->getLight(p);
						Vec3f light_direction;
						Vec3f light_color;
						light->getIllumination(intersect_point, light_direction, light_color);
						float d = light_direction.Dot3(normal);
						if (d < 0) {
							d = 0;
						}
						pixel += d * Vec3f(light_color.x() * color.x(), light_color.y() * color.y(), light_color.z() * color.z());
					}
					image->SetPixel(i, j, pixel);
					float t = hit->getT();
					if (t < depth_min) {
						t = depth_min;
					}
					if (t > depth_max) {
						t = depth_max;
					}
					float volumn = (depth_max - t) / precalc;
					depth_image->SetPixel(i, j, Vec3f(volumn, volumn, volumn));
					Vec3f normal_fab = Vec3f(fabs(normal.x()), fabs(normal.y()), fabs(normal.z()));
					normal_image->SetPixel(i, j, normal_fab);
				}
				else {
					image->SetPixel(i, j, Vec3f(0.0f, 0.0f, 0.0f));
				}
			}
		}
	}

	if (output_file != NULL) {
		image->SaveTGA(output_file);
	}
	if (depth_file != NULL) {
		depth_image->SaveTGA(depth_file);
	}
	if (normal_file != NULL) {
		normal_image->SaveTGA(normal_file);
	}
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
