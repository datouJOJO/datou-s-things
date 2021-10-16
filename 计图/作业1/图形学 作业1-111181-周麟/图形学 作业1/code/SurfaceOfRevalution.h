#pragma once
#include "Surface.h"
#include "Curve.h"

class SurfaceOfRevalution :public Surface
{
public:
	Curve *c;

	SurfaceOfRevalution(Curve*in_c) {
		c = in_c;
	}

	virtual void Paint(ArgParser *args) {
		c->Paint(args);
	}

	virtual void OutputBezier(FILE *file) {
		c->OutputBezier(file);
	}
	virtual void OutputBSpline(FILE *file) {
		c->OutputBSpline(file);
	}
	virtual TriangleMesh* OutputTriangles(ArgParser* args) {
		return c->OutputTriangles(args);
	}
	virtual int getNumVertices() {
		return c->getNumVertices();
	}
	virtual Vec3f getVertex(int i) {
		return c->getVertex(i);
	}

	virtual void moveControlPoint(int selectedPoint, float x, float y){
		c->moveControlPoint(selectedPoint, x, y);
	}

	virtual void addControlPoint(int selectedPoint, float x, float y) {
		c->addControlPoint(selectedPoint, x, y);
	}

	virtual void deleteControlPoint(int selectedPoint) {
		c->deleteControlPoint(selectedPoint);
	}

};

