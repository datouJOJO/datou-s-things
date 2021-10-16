#include "SkeletalModel.h"

#include <FL/Fl.H>

using namespace std;

void SkeletalModel::load(const char *skeletonFile, const char *meshFile, const char *attachmentsFile)
{
	loadSkeleton(skeletonFile);

	m_mesh.load(meshFile);
	m_mesh.loadAttachments(attachmentsFile, m_joints.size());

	computeBindWorldToJointTransforms();
	updateCurrentJointToWorldTransforms();
}

void SkeletalModel::draw(Matrix4f cameraMatrix, bool skeletonVisible)
{
	// draw() gets called whenever a redraw is required
	// (after an update() occurs, when the camera moves, the window is resized, etc)

	m_matrixStack.clear();
	m_matrixStack.push(cameraMatrix);
	skeletonVisible = false;
	if( skeletonVisible )
	{
		drawJoints();

		drawSkeleton();
	}
	else
	{
		// Clear out any weird matrix we may have been using for drawing the bones and revert to the camera matrix.
		glLoadMatrixf(m_matrixStack.top());

		// Tell the mesh to draw itself.
		m_mesh.draw();
	}
}

void SkeletalModel::loadSkeleton( const char* filename )
{
	// Load the skeleton from file here.
	//cout << filename;
	FILE*file = fopen(filename, "rb");
	float x = 0, y = 0, z = 0;
	int index = 0;
	while (!feof(file)) {
		if (m_joints.size() == 18)
			break;

		fscanf(file, "%f %f %f %i", &x, &y, &z, &index);
		Joint *joint = new Joint();

		joint->transform = Matrix4f::translation(x, y, z);
		if (index == -1)//根节点
			this->m_rootJoint = joint;
		else {
			this->m_joints[index]->children.push_back(joint);
		}
		this->m_joints.push_back(joint);
	}
	fclose(file);

}

void SkeletalModel::drawJoints( )
{
	// Draw a sphere at each joint. You will need to add a recursive helper function to traverse the joint hierarchy.
	//
	// We recommend using glutSolidSphere( 0.025f, 12, 12 )
	// to draw a sphere of reasonable size.
	//
	// You are *not* permitted to use the OpenGL matrix stack commands
	// (glPushMatrix, glPopMatrix, glMultMatrix).
	// You should use your MatrixStack class
	// and use glLoadMatrix() before your drawing call.
	drawJoints(m_rootJoint);
}

void SkeletalModel::drawJoints(Joint* joint) {
	m_matrixStack.push(joint->transform);//加载该节点的变化矩阵
	for (int i = 0; i < joint->children.size(); i++) {
		Joint *child = joint->children[i];
		drawJoints(child);
	}

	glLoadMatrixf(m_matrixStack.top());

	glutSolidSphere(0.025f, 12, 12);
	m_matrixStack.pop();
}

void SkeletalModel::drawSkeleton( )
{
	// Draw boxes between the joints. You will need to add a recursive helper function to traverse the joint hierarchy.
	drawSkeleton(this->m_rootJoint);
}

void SkeletalModel::drawSkeleton(Joint* joint) {
	this->m_matrixStack.push(joint->transform);//先将骨骼矩阵压入栈中

	for(int i = 0; i < joint->children.size(); i++){
		Joint *child = joint->children[i];
		drawSkeleton(child);
	}

	this->m_matrixStack.pop();//将相应的骨骼弹出开始画骨架

	if (joint == m_rootJoint)
		return;

	Matrix4f m1 = Matrix4f::translation(0, 0, 0.5);//平移变换
	
	Vector3f z = Vector3f(joint->transform.getCol(3)[0], joint->transform.getCol(3)[1], joint->transform.getCol(3)[2]);
	float L = z.abs();
	Matrix4f m2 = Matrix4f::scaling(0.025, 0.025, L);//缩放

	Vector3f rnd = Vector3f(0, 0, 1);
	z.normalize();//z轴旋转
	Vector3f y = Vector3f::cross(z, rnd).normalized();
	Vector3f x = Vector3f::cross(y, z).normalized();
	Matrix4f m3 = Matrix4f(
		x[0], y[0], z[0], 0.0f,
		x[1], y[1], z[1], 0.0f,
		x[2], y[2], z[2], 0.0f,
		0.0f, 0.0f, 0.0f, 1.0f);	//进行旋转

	this->m_matrixStack.push(m3);
	this->m_matrixStack.push(m2);
	this->m_matrixStack.push(m1);

	glLoadMatrixf(m_matrixStack.top());
	glutSolidCube(1.0f);			//进行画画

	this->m_matrixStack.pop();
	this->m_matrixStack.pop();
	this->m_matrixStack.pop();
}

void SkeletalModel::setJointTransform(int jointIndex, float rX, float rY, float rZ)
{
	// Set the rotation part of the joint's transformation matrix based on the passed in Euler angles.
	Matrix4f x = Matrix4f::rotateX(rX);
	Matrix4f y = Matrix4f::rotateY(rY);
	Matrix4f z = Matrix4f::rotateZ(rZ);
	Matrix4f m = x * y * z;

	m_joints[jointIndex]->transform.setSubmatrix3x3(0, 0, m.getSubmatrix3x3(0, 0));
}


void SkeletalModel::computeBindWorldToJointTransforms()
{
	// 2.3.1. Implement this method to compute a per-joint transform from
	// world-space to joint space in the BIND POSE.
	//
	// Note that this needs to be computed only once since there is only
	// a single bind pose.
	//
	// This method should update each joint's bindWorldToJointTransform.
	// You will need to add a recursive helper function to traverse the joint hierarchy.
	this->m_matrixStack.clear();	//先把栈清空
	computeBindWorldToJointTransforms(this->m_rootJoint);
}

void SkeletalModel::computeBindWorldToJointTransforms(Joint* joint) {
	this->m_matrixStack.push(joint->transform);
	for (int i = 0; i < joint->children.size(); i++) {
		Joint*child = joint->children[i];
		computeBindWorldToJointTransforms(child);
	}
	joint->bindWorldToJointTransform = this->m_matrixStack.top().inverse();
	this->m_matrixStack.pop();
}

void SkeletalModel::updateCurrentJointToWorldTransforms()
{
	// 2.3.2. Implement this method to compute a per-joint transform from
	// joint space to world space in the CURRENT POSE.
	//
	// The current pose is defined by the rotations you've applied to the
	// joints and hence needs to be *updated* every time the joint angles change.
	//
	// This method should update each joint's bindWorldToJointTransform.
	// You will need to add a recursive helper function to traverse the joint hierarchy.
	this->m_matrixStack.clear();
	updateCurrentJointToWorldTransforms(this->m_rootJoint);
}

void SkeletalModel::updateCurrentJointToWorldTransforms(Joint* joint) {
	this->m_matrixStack.push(joint->transform);
	for (int i = 0; i < joint->children.size(); i++) {
		Joint*child = joint->children[i];
		updateCurrentJointToWorldTransforms(child);
	}
	joint->currentJointToWorldTransform = this->m_matrixStack.top();
	this->m_matrixStack.pop();

}

void SkeletalModel::updateMesh()
{
	// 2.3.2. This is the core of SSD.
	// Implement this method to update the vertices of the mesh
	// given the current state of the skeleton.
	// You will need both the bind pose world --> joint transforms.
	// and the current joint --> world transforms.4448848484
	for (int i = 1; i < this->m_mesh.bindVertices.size(); i++) {
		vector<float>weight = m_mesh.attachments[i];
		Vector4f pointI(m_mesh.bindVertices[i], 1);
		Vector4f allW;

		for (int j = 0; j < weight.size(); j++) {
			if (weight[j] == 0)
				continue;

			Vector4f temp = m_joints[j]->bindWorldToJointTransform*pointI;//转换成局部坐标进行变换之后
			temp = m_joints[j]->currentJointToWorldTransform *temp;			//转换成世界坐标之后进行加权计算权重
			allW = allW + weight[j] * temp;
		}
		this->m_mesh.currentVertices[i] = allW.xyz();
	}
}

