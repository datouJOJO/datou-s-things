#include <cmath>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <vector>

#ifdef _WIN32
#include "GL/freeglut.h"
#else
#include <GL/glut.h>
#endif
#include <vecmath.h>
#include "camera.h"

///TODO: include more headers if necessary

#include "TimeStepper.hpp"
#include "simpleSystem.h"
#include "pendulumSystem.h"
#include "ClothSystem.h"
#include "particle.h"
using namespace std;

// Globals here.
namespace
{

    ParticleSystem *system;
    TimeStepper * timeStepper;
	bool isWind = false;	//判断是否加风
	bool isWeb = true;		//判断是不是要画布料
	bool isSg = false;		//判断是不是单摆
	vector<particle>createParticles(int s,int r){
		vector<particle>particles;

		for(int i=0;i<s;i++){
			particle p;
			p.m = 20;	//质量
			p.lenth.push_back(r);	//弹簧静态长度	
			p.capacitance_coefficient = 10;		//摩擦系数
			p.elastic_coefficient.push_back(50);	//劲度系数
			
			if(!isSg){
				if(i==0){
					p.isStatic = true;	//静止
				}
			}
			//连接粒子下标
			if(i-1>=0)
				p.link_index.push_back(i-1);
			if(i+1<=s-1){
				p.link_index.push_back(i+1);
			}
			particles.push_back(p);
		}
		return particles;
	}

	//构造结构弹簧, 抗剪弹簧, 抗弯弹簧
	vector< particle> createWeb(int s,float r) {
	
		vector< particle>particles;

		int e1 = 50;	//结构弹簧的劲度系数
		int e2 = 30;	//抗剪弹簧的劲度系数
		int e3 = 30;	//抗弯弹簧的劲度系数

		for(int i = 0;i<s*s;i++){
			particle p;
			p.m = 20;
			p.capacitance_coefficient = 10;

			//结构弹簧
			if(i-s>=0){
				//上面的粒子
				p.link_index.push_back(i-s);
				p.elastic_coefficient.push_back(e1);
				p.lenth.push_back(r);
			}

			if(i+s<s*s){
				//下面的粒子
				p.link_index.push_back(i+s);
				p.elastic_coefficient.push_back(e1);
				p.lenth.push_back(r);
			}

			if(i+1<s*s&&(i+1)%s!=0){
				//右边的粒子
				p.link_index.push_back(i+1);
				p.elastic_coefficient.push_back(e1);
				p.lenth.push_back(r);
			}

			if(i-1>=0&&i%s!=0){
				//左边的粒子
				p.link_index.push_back(i-1);
				p.elastic_coefficient.push_back(e1);
				p.lenth.push_back(r);
			}

			//抗剪弹簧
			//左上
			//注意弹簧的长度
			if(i-s-1>=0&&i%s!=0){
				p.link_index.push_back(i-s-1);
				p.elastic_coefficient.push_back(e2);
				p.lenth.push_back(sqrt(2.0)*r);
			}

			//左下
			if(i+s-1<s*s&&i%s!=0){
				p.link_index.push_back(i+s-1);
				p.elastic_coefficient.push_back(e2);
				p.lenth.push_back(sqrt(2.0)*r);
			}

			//右上
			if(i-s+1>=0&&(i+1)%s!=0){
				p.link_index.push_back(i-s+1);
				p.elastic_coefficient.push_back(e2);
				p.lenth.push_back(sqrt(2.0)*r);
			}

			//右下
			if(i+s+1<s*s&&(i+1)%s!=0){
				p.link_index.push_back(i+s+1);
				p.elastic_coefficient.push_back(e2);
				p.lenth.push_back(sqrt(2.0)*r);
			}

			//抗弯弹簧
			//注意弹簧的长度
			//上面的粒子
			if(i-2*s>=0){
				p.link_index.push_back(i-2*s);
				p.elastic_coefficient.push_back(e3);
				p.lenth.push_back(2*r);
			}

			//下面的粒子
			if(i+2*s<s*s){
				p.link_index.push_back(i+2*s);
				p.elastic_coefficient.push_back(e3);
				p.lenth.push_back(2*r);
			}

			//左边的粒子
			if(i-2>=0&&(i-1)%s!=0&&i%s!=0){
				p.link_index.push_back(i-2);
				p.elastic_coefficient.push_back(e3);
				p.lenth.push_back(2*r);
			}

			//右边的粒子
			if(i+2>=0&&(i+2)%s!=0&&(i+1)%s!=0){
				p.link_index.push_back(i+2);
				p.elastic_coefficient.push_back(e3);
				p.lenth.push_back(2*r);
			}
			//静止的粒子
			//最上面的行
			if (i / s == 0) {
				p.isStatic = true;
				p.elastic_coefficient[0] = 100;
				p.elastic_coefficient[1] = 100;
				p.elastic_coefficient[2] = 100;
				p.elastic_coefficient[3] = 100;
				p.elastic_coefficient[4] = 100;
			}
			particles.push_back(p);
		}
		return particles;
	}

  // initialize your particle systems
  ///TODO: read argv here. set timestepper , step size etc
  void initSystem(int argc, char * argv[])
  {
    // seed the random number generator with the current time
    srand( time( NULL ) );
	
	//在这里初始化粒子系统
	int s = 10;
	float r = 0.2;
	vector< particle> particles = createWeb(s,r);

	//在这里修改系统和计数器信息

	//system =new SimpleSystem();
	//设置初始状态

	//改变系统
	//system = new PendulumSystem(s,particles);

	system = new ClothSystem(s, particles);
	vector<Vector3f> state;


	//if(!isWeb){
	//	if(system->isSingle){
	//		state.push_back(Vector3f(0,-2,0));	//设置初始位置		
	//		state.push_back(Vector3f(0.5,-1.0,0.0));	//设置初始速度变量
	//	}else{
	//		for(int i =0;i<s;i++){
	//			state.push_back(Vector3f(0,-(i+1),0));	//设置初始位置	
	//			if(i==0){
	//				state.push_back(Vector3f(0.0,0.0,0.0));		//固定的粒子没有初速度
	//			}else{
	//				state.push_back(Vector3f(-rand()%2,-rand()%2,-rand()%2));	//设置初始速度变量
	//			}
	//		}
	//	}
	//}else{
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				state.push_back(Vector3f(r*i,2,j*r));	//设置初始位置		
				state.push_back(Vector3f(0., 0.0, 0.));	//设置初始速度变量
			}
		}
	//}

	system->setState(state);
    timeStepper = new Trapzoidal();
  }

  // Take a step forward for the particle shower
  ///TODO: Optional. modify this function to display various particle systems
  ///and switch between different timeSteppers
  void stepSystem()
  {
      ///TODO The stepsize should change according to commandline arguments
    const float h = 0.04f;
    if(timeStepper!=0){
      timeStepper->takeStep(system,h);
    }
  }

  //单摆
  void drawSingleString(int i){
	  glColor3f(0.0,1.0,1.0);
	  glLineWidth(3);
	  glEnable(GL_LINE_SMOOTH);
	  //开始
	  glBegin(GL_LINES);
	  glVertex3f(0.0,0.0,0.0);
	  glVertex3f(system->getState()[2*i].x(),system->getState()[2*i].y(),system->getState()[2*i].z());
	  //std::cout<<system->getState()[2*i].x()<<" "<<system->getState()[2*i].y()<<" "<<system->getState()[2*i].z()<<std::endl;
	  //结束
	  glEnd();
	  //刷新
	  glFlush();
  }

  //多粒子链
    void drawString(int i){
	  glColor3f(0.0,1.0,1.0);
	  glLineWidth(3);
	  glEnable(GL_LINE_SMOOTH);
	  //开始
	  glBegin(GL_LINES);
	  glVertex3f(system->getState()[2*(i-1)].x(),system->getState()[2*(i-1)].y(),system->getState()[2*(i-1)].z());
	  glVertex3f(system->getState()[2*i].x(),system->getState()[2*i].y(),system->getState()[2*i].z());
	  //std::cout<<system->getState()[2*i].x()<<" "<<system->getState()[2*i].y()<<" "<<system->getState()[2*i].z()<<std::endl;
	  //结束
	  glEnd();
	  //刷新
	  glFlush();
	}

  // Draw the current particle positions
  void drawSystem()
  {
    
    // Base material colors (they don't change)
    GLfloat particleColor[] = {0.4f, 0.7f, 1.0f, 1.0f};
    GLfloat floorColor[] = {1.0f, 0.0f, 0.0f, 1.0f};
    
    glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, particleColor);
    
    //glutSolidSphere(0.1f,10.0f,10.0f);
    
    system->draw();
	//std::cout<<system->isSingle;

	if(!isWeb){
		//这里画绳子
		//画单摆
		if(system->isSingle){
			drawSingleString(0);
		}
		//画多粒子链
		else{
			drawString(1);
			drawString(2);
			drawString(3);
		}
	}

    glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, floorColor);
    glPushMatrix();
    glTranslatef(0.0f,-5.0f,0.0f);
    glScaled(50.0f,0.01f,50.0f);
    glutSolidCube(1);
    glPopMatrix();
    
  }
        

    //-------------------------------------------------------------------
    
        
    // This is the camera
    Camera camera;

    // These are state variables for the UI
    bool g_mousePressed = false;

    // Declarations of functions whose implementations occur later.
    void arcballRotation(int endX, int endY);
    void keyboardFunc( unsigned char key, int x, int y);
    void specialFunc( int key, int x, int y );
    void mouseFunc(int button, int state, int x, int y);
    void motionFunc(int x, int y);
    void reshapeFunc(int w, int h);
    void drawScene(void);
    void initRendering();

    // This function is called whenever a "Normal" key press is
    // received.
    void keyboardFunc( unsigned char key, int x, int y )
    {
        switch ( key )
        {
        case 27: // Escape key
            exit(0);
            break;
        case 'q':
        {
            Matrix4f eye = Matrix4f::identity();
            camera.SetRotation( eye );
            camera.SetCenter( Vector3f::ZERO );
            break;
        }
		case 'a':
		{
			//小球圆周运动
			Matrix4f eye = Matrix4f::identity();
            camera.SetRotation( eye );
            camera.SetCenter( Vector3f::ZERO );
			system =new SimpleSystem();
			break;
		}
		case 's':
		{
			//单摆
			Matrix4f eye = Matrix4f::identity();
            camera.SetRotation( eye );
            camera.SetCenter( Vector3f::ZERO );
			isSg = true;
			vector< particle> particles = createParticles(1,0.2);

			system = new PendulumSystem(1,particles);
			system->isSingle = true;

			vector<Vector3f> state;
			state.push_back(Vector3f(0,-2,0));	//设置初始位置		
			state.push_back(Vector3f(0.5,-1.0,0.0));//设置初始速度变量
			system->setState(state);

			//不画网了
			isWeb = false;
			printf("单摆");
			break;
		}
		case 'd':
		{
			//多粒子链
			Matrix4f eye = Matrix4f::identity();
            camera.SetRotation( eye );
            camera.SetCenter( Vector3f::ZERO );
			isSg = false;
			int s = 4;
			float r = 0.2;
			vector< particle> particles = createParticles(s,r);

			system = new PendulumSystem(s,particles);
			system->isSingle = false;
			vector<Vector3f> state;
			for(int i =0;i<s;i++){
				state.push_back(Vector3f(0,-(i+1),0));	//设置初始位置	
				if(i==0){
					state.push_back(Vector3f(0.0,0.0,0.0));		//固定的粒子没有初速度
				}else{
					state.push_back(Vector3f(-rand()%2,-rand()%2,-rand()%2));	//设置初始速度变量
				}
			}
			system->setState(state);

			//不画网了
			isWeb = false;
			printf("多粒子链");
			break;
		}
		case 'e':
		{
			//欧拉法时间积分器
			timeStepper = new ForwardEuler();
			printf("ForwardEuler");
			break;
		}
		case 't':
		{
			//梯形法时间积分器
			timeStepper = new Trapzoidal();
			printf("Trapzoidal");
			break;
		}
		//case 'r':
		//{
		//	//RK4时间积分器
		//	timeStepper = new RK4();
		//	printf("RK4");
		//	break;
		//}
		case 'w':
		{
			//加风
			isWind = !isWind;
			if(isWind){
				system->setWind(Vector3f(6, 0, 0));
			}else{
				system->setWind(Vector3f());
			}
			break;
		}
		case 'c':
		{
			//粒子布料系统
			Matrix4f eye = Matrix4f::identity();
			camera.SetRotation(eye);
			camera.SetCenter(Vector3f::ZERO);
			int s = 15;
			float x = 0.2;
			vector< particle> particles = createWeb(s, x);
			
			system = new ClothSystem(s, particles);	//在这里改变系统
			system->isSingle = false;
			isWeb = true;
			isSg = false;

			vector<Vector3f> state;
			for (int i = 0; i < s; i++) {
				for (int j = 0; j < s; j++) {
					state.push_back(Vector3f(x * i, 3, j * x));	//设置初始位置		
					state.push_back(Vector3f(0., 0.0, 0.));	//设置初始速度变量
				}
			}
			system->setState(state);
			break;
		}
        default:
            cout << "Unhandled key press " << key << "." << endl;      
        }

        glutPostRedisplay();
    }

    // This function is called whenever a "Special" key press is
    // received.  Right now, it's handling the arrow keys.
    void specialFunc( int key, int x, int y )
    {
        switch ( key )
        {

        }
        //glutPostRedisplay();
    }

    //  Called when mouse button is pressed.
    void mouseFunc(int button, int state, int x, int y)
    {
        if (state == GLUT_DOWN)
        {
            g_mousePressed = true;
            
            switch (button)
            {
            case GLUT_LEFT_BUTTON:
                camera.MouseClick(Camera::LEFT, x, y);
                break;
            case GLUT_MIDDLE_BUTTON:
                camera.MouseClick(Camera::MIDDLE, x, y);
                break;
            case GLUT_RIGHT_BUTTON:
                camera.MouseClick(Camera::RIGHT, x,y);
            default:
                break;
            }                       
        }
        else
        {
            camera.MouseRelease(x,y);
            g_mousePressed = false;
        }
        glutPostRedisplay();
    }

    // Called when mouse is moved while button pressed.
    void motionFunc(int x, int y)
    {
        camera.MouseDrag(x,y);        
    
        glutPostRedisplay();
    }

    // Called when the window is resized
    // w, h - width and height of the window in pixels.
    void reshapeFunc(int w, int h)
    {
        camera.SetDimensions(w,h);

        camera.SetViewport(0,0,w,h);
        camera.ApplyViewport();

        // Set up a perspective view, with square aspect ratio
        glMatrixMode(GL_PROJECTION);

        camera.SetPerspective(50);
        glLoadMatrixf( camera.projectionMatrix() );
    }

    // Initialize OpenGL's rendering modes
    void initRendering()
    {
        glEnable(GL_DEPTH_TEST);   // Depth testing must be turned on
        glEnable(GL_LIGHTING);     // Enable lighting calculations
        glEnable(GL_LIGHT0);       // Turn on light #0.

        glEnable(GL_NORMALIZE);

        // Setup polygon drawing
        glShadeModel(GL_SMOOTH);
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        
	glEnable(GL_CULL_FACE);
	glCullFace(GL_BACK);

        // Clear to black
        glClearColor(0,0,0,1);
    }

    // This function is responsible for displaying the object.
    void drawScene(void)
    {
        // Clear the rendering window
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glMatrixMode( GL_MODELVIEW );  
        glLoadIdentity();              

        // Light color (RGBA)
        GLfloat Lt0diff[] = {1.0,1.0,1.0,1.0};
        GLfloat Lt0pos[] = {3.0,3.0,5.0,1.0};
        glLightfv(GL_LIGHT0, GL_DIFFUSE, Lt0diff);
        glLightfv(GL_LIGHT0, GL_POSITION, Lt0pos);

        glLoadMatrixf( camera.viewMatrix() );

        // THIS IS WHERE THE DRAW CODE GOES.

        drawSystem();

        // This draws the coordinate axes when you're rotating, to
        // keep yourself oriented.
        if( g_mousePressed )
        {
            glPushMatrix();
            Vector3f eye = camera.GetCenter();
            glTranslatef( eye[0], eye[1], eye[2] );

            // Save current state of OpenGL
            glPushAttrib(GL_ALL_ATTRIB_BITS);

            // This is to draw the axes when the mouse button is down
            glDisable(GL_LIGHTING);
            glLineWidth(3);
            glPushMatrix();
            glScaled(5.0,5.0,5.0);
            glBegin(GL_LINES);
            glColor4f(1,0.5,0.5,1); glVertex3f(0,0,0); glVertex3f(1,0,0);
            glColor4f(0.5,1,0.5,1); glVertex3f(0,0,0); glVertex3f(0,1,0);
            glColor4f(0.5,0.5,1,1); glVertex3f(0,0,0); glVertex3f(0,0,1);

            glColor4f(0.5,0.5,0.5,1);
            glVertex3f(0,0,0); glVertex3f(-1,0,0);
            glVertex3f(0,0,0); glVertex3f(0,-1,0);
            glVertex3f(0,0,0); glVertex3f(0,0,-1);

            glEnd();
            glPopMatrix();

            glPopAttrib();
            glPopMatrix();
        }
                 
        // Dump the image to the screen.
        glutSwapBuffers();
    }

    void timerFunc(int t)
    {
        stepSystem();

        glutPostRedisplay();

        glutTimerFunc(t, &timerFunc, t);
    }

    

    
    
}

// Main routine.
// Set up OpenGL, define the callbacks and start the main loop
int main( int argc, char* argv[] )
{
    glutInit( &argc, argv );

    // We're going to animate it, so double buffer 
    glutInitDisplayMode( GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH );

    // Initial parameters for window position and size
    glutInitWindowPosition( 60, 60 );
    glutInitWindowSize( 600, 600 );
    
    camera.SetDimensions( 600, 600 );

    camera.SetDistance( 10 );
    camera.SetCenter( Vector3f::ZERO );
    
    glutCreateWindow("Assignment 4");

    // Initialize OpenGL parameters.
    initRendering();

    // Setup particle system
    initSystem(argc,argv);

    // Set up callback functions for key presses
    glutKeyboardFunc(keyboardFunc); // Handles "normal" ascii symbols
    glutSpecialFunc(specialFunc);   // Handles "special" keyboard keys

    // Set up callback functions for mouse
    glutMouseFunc(mouseFunc);
    glutMotionFunc(motionFunc);

    // Set up the callback function for resizing windows
    glutReshapeFunc( reshapeFunc );

    // Call this whenever window needs redrawing
    glutDisplayFunc( drawScene );

    // Trigger timerFunc every 20 msec
    glutTimerFunc(20, timerFunc, 20);

        
    // Start the main loop.  glutMainLoop never returns.
    glutMainLoop();

    return 0;	// This line is never reached.
}
