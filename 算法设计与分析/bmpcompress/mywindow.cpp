#include "mywindow.h"
#include "ui_mywindow.h"

MyWindow::MyWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MyWindow)
{
    ui->setupUi(this);
}

MyWindow::~MyWindow()
{
    delete ui;
}

void MyWindow::on_but_compress_clicked()
{
    QString path = QFileDialog::getOpenFileName(this, "open","../");

    if(!path.isEmpty()){
        QFile file(path);
        //bool类型
        bool isok = file.open(QIODevice::ReadOnly);

        if(isok){
            const char* ch;
            QByteArray ba = path.toLocal8Bit();  // must

            ch=ba.data();

            ui->textEdit->setText(QStringLiteral("文件地址:") + path);

            QFileInfo fileInFo(path);
            QDateTime CreateT = fileInFo.created();
            QDateTime LateCT=fileInFo.lastModified();
            QDateTime LateVT = fileInFo.lastRead();
            qint64 Size = fileInFo.size();
            QString b=tr("%1").arg(Size);//转字节
            ui->textEdit->append(QStringLiteral("文件大小:") + b + QStringLiteral("字节"));
            ui->textEdit->append(QStringLiteral("创建时间:") + CreateT.toString());
            ui->textEdit->append(QStringLiteral("最近修改时间：") + LateCT.toString());
            ui->textEdit->append(QStringLiteral("最近访问时间：") + LateVT.toString());
            QImage img;
            img.load(path);
            if(img.depth()!=8){
                QMessageBox::warning(this,tr("Error"),QStringLiteral("位深度不为8，不是.bmp文件，请重新选取照片或者关闭！"),QMessageBox::Ok);
                return;
            }
            compress com;
            connect(&com,SIGNAL(Signal1(int)),this,SLOT(updateProgressBar_comp(int)));
            com.CompressBmp(path.toStdString());            
            com.Signal1(100);
            QMessageBox::information(NULL,"successful","compress successed");
        }

        else{
            QMessageBox::warning(this,tr("Error"),QStringLiteral("保存文件失败！"),QMessageBox::Ok);
            return;
        }
        file.close();
    }
}

void MyWindow::on_but_uncompress_clicked()
{
    QString path = QFileDialog::getOpenFileName(this, "open","../");

    if(!path.isEmpty()){
        QFile file(path);
        //bool类型
        bool isok = file.open(QIODevice::ReadOnly);

        if(isok){
            const char* ch;
            QByteArray ba = path.toLocal8Bit();  // must

            ch=ba.data();

            ui->textEdit->setText(QStringLiteral("文件地址:") + path);

            QFileInfo fileInFo(path);
            QDateTime CreateT = fileInFo.created();
            QDateTime LateCT=fileInFo.lastModified();
            QDateTime LateVT = fileInFo.lastRead();
            qint64 Size = fileInFo.size();
            QString b=tr("%1").arg(Size);//转字节
            ui->textEdit->append(QStringLiteral("文件大小:") + b + QStringLiteral("字节"));
            ui->textEdit->append(QStringLiteral("创建时间:") + CreateT.toString());
            ui->textEdit->append(QStringLiteral("最近修改时间：") + LateCT.toString());
            ui->textEdit->append(QStringLiteral("最近访问时间：") + LateVT.toString());

//            ifstream fin(ch,ios::binary);
//            ofstream fout(ch,ios::binary);

//            compress com;
//            com.UncompressBmp(fin,fout);
//            fin.close();
//            fout.close();
            QImage img;
            img.load(path);
            if(img.depth()!=8){
                QMessageBox::warning(this,tr("Error"),QStringLiteral("位深度不为8，不是.bmp文件，请重新选取照片或者关闭！"),QMessageBox::Ok);
                return;
            }
            compress com;
            connect(&com,SIGNAL(Signal2(int)),this,SLOT(updateProgressBar_uncomp(int)));
            com.UncompressBmp(path.toStdString());            
            com.Signal2(100);
            QMessageBox::information(NULL,"successful","decompress successed");
        }

        else{
            QMessageBox::warning(this,tr("Error"),QStringLiteral("保存文件失败！"),QMessageBox::Ok);
            return;
        }
    }
}

void MyWindow::on_progress_comp_valueChanged()
{
    //
}

void MyWindow::on_progress_uncomp_valueChanged()
{
    //
}

void MyWindow::updateProgressBar_comp(int value){
    this->ui->progress_comp->setValue(value);
}

void MyWindow::updateProgressBar_uncomp(int value){
    this->ui->progress_uncomp->setValue(value);
}
