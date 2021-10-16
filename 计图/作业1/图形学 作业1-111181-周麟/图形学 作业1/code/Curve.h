#pragma once
#include "Spline.h"
#include <gl\glut.h>

class Curve :public Spline
{
public:
	Curve() {}
	virtual ~Curve() {
	};
	virtual void Paint(ArgParser *args) {
		//读文件？
		//先画点
		for (int i = 0; i < index; i++) {
			float x = 0, y = 0, z = 0;
			vertices[i].Get(x, y, z);

			glPointSize(5.0);
			glBegin(GL_POINTS);
			glColor3f(1.0, 0.0, 0.0);
			glVertex3f(x, y, z);
			glEnd();
		}

		//再画线
		for (int i = 0; i < index-1; i++) {
			float x1 = 0, y1 = 0, z1 = 0;
			float x2 = 0, y2 = 0, z2 = 0;
			vertices[i].Get(x1, y1, z1);
			vertices[i+1].Get(x2, y2, z2);
			glPointSize(5.0);
			glBegin(GL_LINES);
			glColor3f(0.0, 0.0, 1.0);
			glVertex3f(x1, y1, z1);
			glVertex3f(x2, y2, z2);
			glEnd();
		}
	}
};

