#include "Mesh.h"

using namespace std;

void Mesh::load( const char* filename )
{
	// 2.1.1. load() should populate bindVertices, currentVertices, and faces

	// Add your code here.
	//与作业 0一样读入相应的参数
	cout << filename;
	FILE*file = fopen(filename, "rb");
	char c;
	float x = 0, y = 0, z = 0;	//点坐标
	int v1 = 0, v2 = 0, v3 = 0;		//面的点对应的点的下标
	while (!feof(file)) {
		fscanf(file, "%c ", &c);//数据的类型

		if (c == 'v') {			//点坐标
			fscanf(file, "%f %f %f", &x, &y, &z);
			this->bindVertices.push_back(Vector3f(x, y, z));
		}
		else if (c == 'f') {	//点下标
			fscanf(file, "%d %d %d", &v1, &v2, &v3);
			this->faces.push_back(Tuple3u(v1, v2, v3));
		}
	}
	fclose(file);
	cout << "结点数量为：" << bindVertices.size() << endl;
	cout << "面数量为:" << faces.size() << endl;
	// make a copy of the bind vertices as the current vertices
	currentVertices = bindVertices;
}

void Mesh::draw()
{
	// Since these meshes don't have normals
	// be sure to generate a normal per triangle.
	// Notice that since we have per-triangle normals
	// rather than the analytical normals from
	// assignment 1, the appearance is "faceted".
	//与作业0一样用读入的点数据和面数据画图案
	glBegin(GL_TRIANGLES);
	//开始画
	for (int i = 0; i < this->faces.size(); i++) {
		//一个面的三个坐标
		Vector3f v1 = currentVertices[faces[i][0] - 1];
		Vector3f v2 = currentVertices[faces[i][1] - 1];
		Vector3f v3 = currentVertices[faces[i][2] - 1];

		//法向量
		Vector3f Normal = Vector3f::cross(v1 - v2, v1 - v3).normalized();

		glNormal3f(Normal.x(), Normal.y(), Normal.z());

		glVertex3f(v1.x(), v1.y(), v1.z());
		glVertex3f(v2.x(), v2.y(), v2.z());
		glVertex3f(v3.x(), v3.y(), v3.z());
	}

	glEnd();
	glFlush();

}

void Mesh::loadAttachments( const char* filename, int numJoints )
{
	// 2.2. Implement this method to load the per-vertex  weights
	// this method should update m_mesh.attachments
	//先读入每个关节的数据 然后再push back到attachment attachment的大小就是关节的数量
	ifstream in(filename, ios::in);

	float weight = 0;
	//外部向量的大小等于结点的数量
	while (attachments.size() <= bindVertices.size()) {
		vector<float>a;
		a.push_back(0);	//根节点

		for (int i = 0; i < numJoints - 1; ++i) {
			in >> weight;
			a.push_back(weight);
		}
		this->attachments.push_back(a);
	}
	in.close();
}
