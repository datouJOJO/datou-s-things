#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <math.h>       /* floor */
#include <iostream>
#include <stdio.h>
#include <QtCore>
#include <QtWidgets>
#include <QMainWindow>
#include <QPushButton>
#include <vector>
#include <QMessageBox>
#include <qlabel.h>

using namespace std;

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT
    QWidget * sudo_Map;
    QGridLayout*Box_input;
public:
    explicit MainWindow(QWidget *parent = nullptr);
    ~MainWindow();
    void show_result(int a,int b,int flag);//x和y轴坐标,flag表示是否是提示或者删除
    void get_number();
    void build_data();
    void creatMatrix();
    void loadmap();
    void search(int t);
private slots:
    void on_pushButton_ts_clicked();

    void on_level_box_currentIndexChanged(int index);

    void on_pushButton_replay_clicked();

    void updateTime200();

    void on_pushButton_begin_clicked();

    void on_pushButton_delete_clicked();

    void on_pushButton_submit_clicked();

    void on_pushButton_stop_clicked();

private:
    Ui::MainWindow *ui;
    QTimer * timer;      //定时器 每秒更新时间
    QTime * TimeRecord;  //记录时间
    bool isStart;        //记录是否已经开始计时
};

#endif // MAINWINDOW_H
