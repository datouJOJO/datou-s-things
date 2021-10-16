#pragma once
#include "arg_parser.h"
#include "vectors.h"
#include "matrix.h"
#include "triangle_mesh.h"
#include <gl\glut.h>
#include <vector>

class Spline
{
public:
	Vec3f*vertices;
	int index;

	Spline() {};
	Spline(int vertices_num) {
		this->index = vertices_num;
		vertices = new Vec3f[1000];
	};
	~Spline() {
		delete[]vertices;
	}

	// ���ڻ�ͼ��FOR VISUALIZATION
	virtual void Paint(ArgParser *args) {};

	// ����ʵ����������ת����FOR CONVERTING BETWEEN SPLINE TYPES
	virtual void OutputBezier(FILE *file)=0;
	virtual void OutputBSpline(FILE *file) = 0;

	// ���ڵõ����Ƶ��FOR CONTROL POINT PICKING
	virtual int getNumVertices()
	{
		return index;
	};
	virtual Vec3f getVertex(int i)
	{
		return vertices[i];
	};

	virtual void set(int i, Vec3f v) {
		//float x, y, z = 0.0;
		//v.Get(x, y, z);
		//std::cout << i << std::endl;
		//std::cout << x << " " << y << " " << z << " " << std::endl;
		vertices[i] = v;
	};
	// ���ڱ༭������FOR EDITING OPERATIONS
	virtual void moveControlPoint(int selectedPoint, float x, float y) {
		Vec3f temp = Vec3f(x, y, 0);
		vertices[selectedPoint] = temp;
	}
	virtual void addControlPoint(int selectedPoint, float x, float y) {
		Vec3f temp = Vec3f(x, y, 0);
		vertices[selectedPoint] = temp;
		index += 1;
	}
	virtual void deleteControlPoint(int selectedPoint) {
		for (int i = selectedPoint; i < index - 1; i++) {
			vertices[i] = vertices[i + 1];
		}
		index -= 1;
	}

	// ���ڲ��������ε�FOR GENERATING TRIANGLES
	virtual TriangleMesh* OutputTriangles(ArgParser *args) { std::cout << args->curve_tessellation;  return 0;};

};

