#pragma once
#include "Surface.h"
class BezierPatch:public Surface
{
public:
	void Paint(ArgParser *args) {
		glBegin(GL_POINTS);
		glBegin(GL_LINES);
		glPointSize(2);
		glLineWidth(4); glColor3f(1.0f, 0.0f, 0.0f);
		glVertex3f(1.0f, 1.0f, 1.0f);
	}

	virtual void OutputBezier(FILE *file) {

	};
	virtual void OutputBSpline(FILE *file) {

	};

};

