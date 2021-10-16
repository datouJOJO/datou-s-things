#ifndef MYWINDOW_H
#define MYWINDOW_H

#include <QMainWindow>
#include <QString>
#include <QFile>
#include <QFileDialog>
#include <QDataStream>
#include <compress.h>
#include <QDateTime>
#include <QMessageBox>
#include <QDebug>
#include <qlabel.h>

using namespace std;


namespace Ui {
class MyWindow;
}

class MyWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MyWindow(QWidget *parent = nullptr);
    ~MyWindow();

private slots:
    void on_but_compress_clicked();

    void on_but_uncompress_clicked();

    void on_progress_comp_valueChanged();

    void on_progress_uncomp_valueChanged();

    void updateProgressBar_comp(int value);

    void updateProgressBar_uncomp(int value);
private:
    Ui::MyWindow *ui;
    BITMAPINFOHEADER BitMapInfoHeader;
signals:
};

#endif // MYWINDOW_H
