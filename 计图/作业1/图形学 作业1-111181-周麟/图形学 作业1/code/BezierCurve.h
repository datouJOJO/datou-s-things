#pragma once
#include "Curve.h"
#define _USE_MATH_DEFINES
#include <math.h>

class BezierCurve:public Curve
{
public:
	BezierCurve(int index) {
		vertices = new Vec3f[1000];
		this->index = index;
	};

	virtual void Paint(ArgParser *args) {
		Curve::Paint(args);
		float curve_tessellation = args->curve_tessellation;
		//把控制点和线画出来
		//四个点为一组 不连续
		glBegin(GL_LINE_STRIP);
		glColor3f(0, 1, 0);
		for (float i = 0; i < index - 3;i+=3) {
			//[0~1]
			for (float j = 0; j <= curve_tessellation; j++) {
				float t = j / curve_tessellation;
				Vec3f v = Bezier_algorithm(t,i);
				glVertex3f(v.x(), v.y(), v.z());
			}
		}
		glEnd();
	}
	Vec3f Bezier_algorithm(float t,int i) {
		float a1 = pow((1 - t), 3);
		float a2 = pow((1 - t), 2) * 3 * t;
		float a3 = (1 - t) * 3 * t * t;
		float a4 = t * t * t;

		float x = vertices[i].x()*a1 + vertices[i+1].x()*a2 + vertices[i+2].x()*a3 + vertices[i+3].x()*a4;
		float y = vertices[i].y()*a1 + vertices[i+1].y()*a2 + vertices[i+2].y()*a3 + vertices[i+3].y()*a4;
		float z = vertices[i].z()*a1 + vertices[i+1].z()*a2 + vertices[i+2].z()*a3 + vertices[i+3].z()*a4;

		return Vec3f(x, y, z);
	};

	virtual void OutputBezier(FILE *file) {
		//写入数据
		fprintf(file, "%s", "bezier num_vertices ");
		//换行符
		fprintf(file, "%d\n", index);
		for (int i = 0; i < index; i++) {
			fprintf(file, "%f %f %f\n", vertices[i].x(), vertices[i].y(), vertices[i].z());
		}
	};
	virtual void OutputBSpline(FILE *file) {
		fprintf(file, "bspline\n");
		fprintf(file, "num_vertices 4\n");
		float bz[16] = { -1,3,-3,1,3,-6,3,0,-3,3,0,0,1,0,0,0 };
		float bs[16] = { -1,3,-3,1,3,-6,0,4,-3,3,3,1,1,0,0,0 };
		for (int i = 0; i < 16; i++) {
			bs[i] /= 6;
		}

		Matrix bezier(bz);
		Matrix bspline(bs);

		float G1[16] = { vertices[0].x(),vertices[1].x(),vertices[2].x(),vertices[3].x(),
						 vertices[0].y(),vertices[1].y(),vertices[2].y(),vertices[3].y(),
						 vertices[0].z(),vertices[1].z(),vertices[2].z(),vertices[3].z(),
						 0,0,0,0
						};
		Matrix Geometry(G1);
		Matrix G2;
		if (bspline.Inverse())
		{
			G2 = Geometry * bezier * bspline;
		}
		for (int i = 0; i < 4; i++) {
			fprintf(file, "%f %f %f\n", G2.Get(i, 0), G2.Get(i, 1), G2.Get(i, 2));
		}
	};

	//试例curve_editor -input spline06_torus.txt -curve_tessellation 4 -revolution_tessellation 10 -output torus_low.obj
	//三角形网格
	virtual TriangleMesh* OutputTriangles(ArgParser *args) {
		int curve_tessellation = args->curve_tessellation;
		int revolution_tessellation = args->revolution_tessellation;
		//曲线的条数
		//曲线越多 段数越多 三角网越密集 生成的模型就越平滑
		int cnum = curve_tessellation * (index - 1) / 3 + 1;
		TriangleNet* tri = new TriangleNet(revolution_tessellation + 1, cnum - 1);

		vector<Vec3f> arr;
		Vec3f temp;
		for (int j = 0; j < index - 3; j += 3)
		{
			//转化成bezier坐标
			for (double i = 0; i <= curve_tessellation; i++)
			{
				double t = i / double(curve_tessellation);
				double a1 = pow(1 - t, 3);
				double a2 = pow(1 - t, 2) * 3 * t;
				double a3 = 3 * t * t * (1 - t);
				double a4 = t * t * t;
				double x = a1 * vertices[j].x() + a2 * vertices[j + 1].x() + a3 * vertices[j + 2].x() + a4 * vertices[j + 3].x();
				double y = a1 * vertices[j].y() + a2 * vertices[j + 1].y() + a3 * vertices[j + 2].y() + a4 * vertices[j + 3].y();
				temp.Set(x, y, 0.0);
				arr.push_back(temp);
			}
			arr.pop_back();
		}
		arr.push_back(temp);
		for (int i = 0; i < cnum; i++)
		{
			Vec3f t(arr[i].x(), arr[i].y(), arr[i].z());
			for (int j = 0; j <= revolution_tessellation + 1; j++)
			{

				//有revolution_tessellation段，计算每段要旋转多少度
				double angle = 360.0 / revolution_tessellation / 180 * M_PI;
				double x = t.x() * cos(angle) + t.z() * sin(angle);
				double y = t.y();
				double z = t.x() * -1.0 * sin(angle) + t.z() * cos(angle);
				//定位赋值
				tri->SetVertex(j, i, t);
				t.Set(x, y, z);
			}
		}
		return tri;
	};

};

