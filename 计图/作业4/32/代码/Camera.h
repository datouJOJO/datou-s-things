#pragma once
#include "vectors.h"
#include "ray.h"

class Camera {

public:

	Camera() 
	{};
	virtual Ray generateRay(Vec2f point) = 0;
	virtual float getTMin() const = 0;

};