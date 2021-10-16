#ifndef COMPRESS_H
#define COMPRESS_H

#include<Windows.h>
#include<iostream>
#include<fstream>
#include<string>
#include<vector>
#include <string>
#include <QFileDialog>
#include <QMessageBox>
#include<stack>

using namespace std;

class compress : public QWidget
{
    Q_OBJECT
public:
    explicit compress(QWidget *parent = nullptr);
    //解析.bmp文件信息并压缩
    void CompressBmp(string filename);
    void UncompressBmp(string filename);
private:
    //存放文件信息头 位图信息头 颜色序列 数据
    BITMAPFILEHEADER FileHeader;
    BITMAPINFOHEADER InfoHeader;
    RGBQUAD ipRGB[256];
    //像素信息矩阵
    unsigned char**imgData;
    //位图长宽高
    int  Height;
    int  Width;
    //s矩阵
    unsigned int*snake;
signals:
    void Signal1(int value);
    void Signal2(int value);
public slots:
};

#endif // COMPRESS_H
