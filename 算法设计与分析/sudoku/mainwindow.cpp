#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <stdio.h>
using namespace std;
#define Max_Col 325 //9*9*4
#define Max_Row 730 //9*9*9

#define S_Offset 0
#define R_Offset 81
#define C_Offset 162
#define B_Offset 243 //分别是格子，行，列，宫的偏移量

//默认题目
int question[9][9] = {
    { 0, 0, 0, 7, 0, 0, 8, 0, 0 },
    { 0, 0, 0, 0, 4, 0, 0, 3, 0 },
    { 0, 0, 0, 0, 0, 9, 0, 0, 1 },
    { 6, 0, 0, 5, 0, 0, 0, 0, 0 },
    { 0, 1, 0, 0, 3, 0, 0, 4, 0 },
    { 0, 0, 5, 0, 0, 1, 0, 0, 7 },
    { 5, 0, 0, 2, 0, 0, 6, 0, 0 },
    { 0, 3, 0, 0, 8, 0, 0, 9, 0 },
    { 0, 0, 7, 0, 0, 0, 0, 0, 2 }
  };
int sudoku_array[9][9]={};
struct Node{
    struct Node * Header;
    struct Node * Left;
    struct Node * Right;
    struct Node * Up;
    struct Node * Down;  //头 上下左右的指针

    char Name;  //列头名
    int Number; //列头数
};

int Col;
int Row;
struct Node Matrix[Max_Col][Max_Row];
struct Node Root;
struct Node*RootNode = &Root;
struct Node*RowHeader[Max_Row];
int Data[Max_Col][Max_Row];
int Result[Max_Row];
int n=0;
int finish;
int added=0;
int Sudoku[9][9]={};
// 返回真索引
int RetNb(int n){return n/81;}//每个格子
int RetRw(int n){return (n/9)%9;}//行
int RetCl(int n){return n%9;}//列
int RetBx(int n){return ((RetRw(n)/3)*3)+(RetCl(n)/3);}//宫
int retSq(int n){return RetRw(n)*9+RetCl(n);}//第一条件对应的下标
int retRn(int N) { return RetNb(N)*9 + RetRw(N); }//第二条件对应的下标
int retCn(int N) { return RetNb(N)*9 + RetCl(N); }//第三条件对应的下标
int retBn(int N) { return RetNb(N)*9 + RetBx(N); }//第四条件对应的下标
int current_index(int Nb, int Rw, int Cl) { return Nb*81 + Rw*9 + Cl; }//当前下标


MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    this->setWindowTitle("sudoku game");
    this->setMaximumSize(517,393);
    this->setMinimumSize(517,393);
    isStart = false;     //初始为还未计时
    timer = new QTimer(this);  //初始化定时器
    TimeRecord = new QTime(0, 0, 0); //初始化时间

    ui->lcdNumber->setDigitCount(8);
    ui->lcdNumber->setSegmentStyle(QLCDNumber::Flat);
    ui->lcdNumber->display(TimeRecord->toString("hh:mm:ss"));

    connect(timer,SIGNAL(timeout()),this,SLOT(updateTime200()));

    //将所有按钮设置没有焦点
    ui->pushButton_ts->setFocusPolicy(Qt::NoFocus);
    ui->pushButton_begin->setFocusPolicy(Qt::NoFocus);
    ui->pushButton_delete->setFocusPolicy(Qt::NoFocus);
    ui->pushButton_replay->setFocusPolicy(Qt::NoFocus);
    ui->pushButton_submit->setFocusPolicy(Qt::NoFocus);
    ui->pushButton_stop->setFocusPolicy(Qt::NoFocus);

    ui->num_01->setFocus();
}

void MainWindow::show_result(int a,int b,int flag){
        QTextEdit* Num [9][9] ={ui->num_01,ui->num_02,ui->num_03,ui->num_04,ui->num_05,ui->num_06,ui->num_07,ui->num_08,ui->num_09
            ,ui->num_10,ui->num_11,ui->num_12,ui->num_13,ui->num_14,ui->num_15,ui->num_16,ui->num_17,ui->num_18
            ,ui->num_19,ui->num_20,ui->num_21,ui->num_22,ui->num_23,ui->num_24,ui->num_25,ui->num_26,ui->num_27
            ,ui->num_28,ui->num_29,ui->num_30,ui->num_31,ui->num_32,ui->num_33,ui->num_34,ui->num_35,ui->num_36
            ,ui->num_37,ui->num_38,ui->num_39,ui->num_40,ui->num_41,ui->num_42,ui->num_43,ui->num_44,ui->num_45
            ,ui->num_46,ui->num_47,ui->num_48,ui->num_49,ui->num_50,ui->num_51,ui->num_52,ui->num_53,ui->num_54
            ,ui->num_55,ui->num_56,ui->num_57,ui->num_58,ui->num_59,ui->num_60,ui->num_61,ui->num_62,ui->num_63
            ,ui->num_64,ui->num_65,ui->num_66,ui->num_67,ui->num_68,ui->num_69,ui->num_70,ui->num_71,ui->num_72
            ,ui->num_73,ui->num_74,ui->num_75,ui->num_76,ui->num_77,ui->num_78,ui->num_79,ui->num_80,ui->num_81};

        if(flag==1){
                Num[a][b]->setText(QString::number(Sudoku[a][b]+1));
        }
        if(flag==0){
            Num[a][b]->setText("");
        }
}

void MainWindow::get_number(){
    QTextEdit* Num [9][9] ={ui->num_01,ui->num_02,ui->num_03,ui->num_04,ui->num_05,ui->num_06,ui->num_07,ui->num_08,ui->num_09
        ,ui->num_10,ui->num_11,ui->num_12,ui->num_13,ui->num_14,ui->num_15,ui->num_16,ui->num_17,ui->num_18
        ,ui->num_19,ui->num_20,ui->num_21,ui->num_22,ui->num_23,ui->num_24,ui->num_25,ui->num_26,ui->num_27
        ,ui->num_28,ui->num_29,ui->num_30,ui->num_31,ui->num_32,ui->num_33,ui->num_34,ui->num_35,ui->num_36
        ,ui->num_37,ui->num_38,ui->num_39,ui->num_40,ui->num_41,ui->num_42,ui->num_43,ui->num_44,ui->num_45
        ,ui->num_46,ui->num_47,ui->num_48,ui->num_49,ui->num_50,ui->num_51,ui->num_52,ui->num_53,ui->num_54
        ,ui->num_55,ui->num_56,ui->num_57,ui->num_58,ui->num_59,ui->num_60,ui->num_61,ui->num_62,ui->num_63
        ,ui->num_64,ui->num_65,ui->num_66,ui->num_67,ui->num_68,ui->num_69,ui->num_70,ui->num_71,ui->num_72
        ,ui->num_73,ui->num_74,ui->num_75,ui->num_76,ui->num_77,ui->num_78,ui->num_79,ui->num_80,ui->num_81};

    for(int i=0;i<9;i++){
        for(int j=0;j<9;j++){
        QString temp = Num[i][j]->toPlainText();
        if(temp=="")temp="0";
        int temp1 = temp.toInt();
//        std::cout<<temp1;
        sudoku_array[i][j] = temp1;
        }
    }
}

void MainWindow::build_data(){
    int a,b,c;
    int index;

    Col = 324;
    Row = 729 + 1;

    for(a=0;a<9;a++)
    {
        for(b=0;b<9;b++)
        {
            for(c=0;c<9;c++)
            {
                index = current_index(c, a, b); //获取索引

                Data[S_Offset + retSq(index)][index] = 1; //规则1-单元格
                Data[R_Offset + retRn(index)][index] = 1; //规则2-行
                Data[C_Offset + retCn(index)][index] = 1; //规则3-列
                Data[B_Offset + retBn(index)][index] = 1; //规则4-宫
            }
        }
    }
    for(a=0;a<Col;a++)
    {
        Data[a][Row-1] = 2;
    }

    creatMatrix();

    //向矩阵中添加类型和列信息
    //给四个条件的表头都取一个名字
    for(a=0;a<R_Offset;a++){
        Matrix[a][Row-1].Name = 'S';
        Matrix[a][Row-1].Number = a;
    }
    for(a=R_Offset;a<C_Offset;a++){
        Matrix[a][Row-1].Name = 'R';
        Matrix[a][Row-1].Number = a-R_Offset;
    }
    for(a=C_Offset;a<B_Offset;a++){
        Matrix[a][Row-1].Name = 'C';
        Matrix[a][Row-1].Number = a-C_Offset;
    }
    for(a=B_Offset;a<Col;a++){
        Matrix[a][Row-1].Name = 'B';
        Matrix[a][Row-1].Number = a-B_Offset;
    }
}

//链表矩阵运算；辅助函数
int dataLeft(int i){
    if(i-1<0){
        return Col-1;
        //最后一个
    }
    else{
        return i-1;
    }
}
int dataRight(int i){
    return (i+1) % Col;
}
int dataUp(int i){
    if(i-1<0){
        return Row-1;
        //最后一个
    }
    else{
        return i-1;
    }
}
int dataDown(int i){
    return (i+1) % Row;
}

void MainWindow::creatMatrix(){
    int i,j;
    //建立链表矩阵，节点连接等
    for(int a=0;a<Col;a++) {
        for(int b=0;b<Row;b++) {
            if(Data[a][b]!=0) {
                // 左邻居
                i = a;
                j = b;
                do {
                    i = dataLeft(i);
                } while (Data[i][j]==0);
                Matrix[a][b].Left = &Matrix[i][j];
                // 右邻居
                i = a;
                j = b;
                do {
                    i = dataRight(i);
                } while (Data[i][j]==0);
                Matrix[a][b].Right = &Matrix[i][j];
                // 上邻居
                i = a; j = b;
                do {
                    j = dataUp(j);
                } while (Data[i][j]==0);
                Matrix[a][b].Up = &Matrix[i][j];
                // 下邻居
                i = a;
                j = b;
                do {
                    j = dataDown(j);
                } while (Data[i][j]==0);
                Matrix[a][b].Down = &Matrix[i][j];
                // Header pointer
                Matrix[a][b].Header = &Matrix[a][Row-1];
                Matrix[a][b].Number = b;//当前的数字
                //Row Header
                RowHeader[b] = &Matrix[a][b];
            }
        }
    }

    for(int a=0;a<Col;a++) {//给每个列头取个名字
        Matrix[a][Row-1].Name = 'C';
        Matrix[a][Row-1].Number = a;
    }

    Root.Name = 'R';
    Root.Left = &Matrix[Col-1][Row-1];
    Root.Right = &Matrix[0][Row-1];
    Matrix[Col-1][Row-1].Right = &Root;
    Matrix[0][Row-1].Left = &Root;
}

//删除相关的行和列
void cover(struct Node * col){
    col ->Right->Left = col->Left;
    col->Left->Right = col->Right;

    for(struct Node * node = col->Down;node!=col;node = node->Down){
        for(struct Node * temp = node->Right;temp!=node;temp = temp->Right){
            temp->Up->Down = temp->Down;
            temp->Down->Up = temp->Up;
        }
    }
}

//恢复相关的行和列
void uncover(struct Node*col){
    for(struct Node*node = col->Up;col!=node;node = node->Up){
        for(struct Node*temp = node->Left;temp!=node;temp= temp->Left){
            temp->Down->Up = temp;
            temp->Up->Down = temp;
        }
    }
    col->Left->Right = col;
    col->Right->Left = col;
}

void MainWindow::loadmap() {
    int a,b;
    int temp;
    for(a=0;a<9;a++) {
        for(b=0;b<9;b++) {
            temp=sudoku_array[a][b];
            struct Node *RowNode;
            if(temp>=1&&temp<=9){
                RowNode = RowHeader[current_index(temp-1, a, b)];
                cover(RowNode->Header);
                for(struct Node * RightNode = RowNode->Right; RightNode!=RowNode; RightNode = RightNode->Right)
                {
                    cover(RightNode->Header);
                }
                added++;
                Result[n++] = current_index(temp-1, a, b);
            }
        }
    }
}

void getresult(void) {
    //赋值的一步
    for(int a=0;a<n;a++) {
        Sudoku[RetRw(Result[a])][RetCl(Result[a])] = RetNb(Result[a]);
    }
}
void MainWindow::search(int k) {

    if((RootNode->Left == RootNode && RootNode->Right==RootNode) || k == (81-added)){
        getresult();
        finish = 1;
        return;
    }
    struct Node *Column = RootNode->Right; //向右取列
    cover(Column);

    for(struct Node *RowNode = Column->Down; RowNode!=Column && !finish; RowNode = RowNode->Down) {
        Result[n++] = RowNode->Number;//在内部插入列
        for(struct Node *RightNode = RowNode->Right; RightNode!=RowNode; RightNode = RightNode->Right) {
            cover(RightNode->Header);
        }
        search(k+1);
        //如果不满足要求恢复
        for(struct Node *RightNode = RowNode->Right; RightNode!=RowNode; RightNode = RightNode->Right) {
            uncover(RightNode->Header);
        }
        Result[--n] = 0;//移除列
    }
    uncover(Column);
}
MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pushButton_ts_clicked()
{

    get_number();
    finish = 0;
    added=0;
    build_data();
    loadmap();
    search(0);

    int flag=1;
    if(ui->num_01->hasFocus())show_result(0,0,flag);
    if(ui->num_02->hasFocus())show_result(0,1,flag);
    if(ui->num_03->hasFocus())show_result(0,2,flag);
    if(ui->num_04->hasFocus())show_result(0,3,flag);
    if(ui->num_05->hasFocus())show_result(0,4,flag);
    if(ui->num_06->hasFocus())show_result(0,5,flag);
    if(ui->num_07->hasFocus())show_result(0,6,flag);
    if(ui->num_08->hasFocus())show_result(0,7,flag);
    if(ui->num_09->hasFocus())show_result(0,8,flag);
    if(ui->num_10->hasFocus())show_result(1,0,flag);
    if(ui->num_11->hasFocus())show_result(1,1,flag);
    if(ui->num_12->hasFocus())show_result(1,2,flag);
    if(ui->num_13->hasFocus())show_result(1,3,flag);
    if(ui->num_14->hasFocus())show_result(1,4,flag);
    if(ui->num_15->hasFocus())show_result(1,5,flag);
    if(ui->num_16->hasFocus())show_result(1,6,flag);
    if(ui->num_17->hasFocus())show_result(1,7,flag);
    if(ui->num_18->hasFocus())show_result(1,8,flag);
    if(ui->num_19->hasFocus())show_result(2,0,flag);
    if(ui->num_20->hasFocus())show_result(2,1,flag);
    if(ui->num_21->hasFocus())show_result(2,2,flag);
    if(ui->num_22->hasFocus())show_result(2,3,flag);
    if(ui->num_23->hasFocus())show_result(2,4,flag);
    if(ui->num_24->hasFocus())show_result(2,5,flag);
    if(ui->num_25->hasFocus())show_result(2,6,flag);
    if(ui->num_26->hasFocus())show_result(2,7,flag);
    if(ui->num_27->hasFocus())show_result(2,8,flag);
    if(ui->num_28->hasFocus())show_result(3,0,flag);
    if(ui->num_29->hasFocus())show_result(3,1,flag);
    if(ui->num_30->hasFocus())show_result(3,2,flag);
    if(ui->num_31->hasFocus())show_result(3,3,flag);
    if(ui->num_32->hasFocus())show_result(3,4,flag);
    if(ui->num_33->hasFocus())show_result(3,5,flag);
    if(ui->num_34->hasFocus())show_result(3,6,flag);
    if(ui->num_35->hasFocus())show_result(3,7,flag);
    if(ui->num_36->hasFocus())show_result(3,8,flag);
    if(ui->num_37->hasFocus())show_result(4,0,flag);
    if(ui->num_38->hasFocus())show_result(4,1,flag);
    if(ui->num_39->hasFocus())show_result(4,2,flag);
    if(ui->num_40->hasFocus())show_result(4,3,flag);
    if(ui->num_41->hasFocus())show_result(4,4,flag);
    if(ui->num_42->hasFocus())show_result(4,5,flag);
    if(ui->num_43->hasFocus())show_result(4,6,flag);
    if(ui->num_44->hasFocus())show_result(4,7,flag);
    if(ui->num_45->hasFocus())show_result(4,8,flag);
    if(ui->num_46->hasFocus())show_result(5,0,flag);
    if(ui->num_47->hasFocus())show_result(5,1,flag);
    if(ui->num_48->hasFocus())show_result(5,2,flag);
    if(ui->num_49->hasFocus())show_result(5,3,flag);
    if(ui->num_50->hasFocus())show_result(5,4,flag);
    if(ui->num_51->hasFocus())show_result(5,5,flag);
    if(ui->num_52->hasFocus())show_result(5,6,flag);
    if(ui->num_53->hasFocus())show_result(5,7,flag);
    if(ui->num_54->hasFocus())show_result(5,8,flag);
    if(ui->num_55->hasFocus())show_result(6,0,flag);
    if(ui->num_56->hasFocus())show_result(6,1,flag);
    if(ui->num_57->hasFocus())show_result(6,2,flag);
    if(ui->num_58->hasFocus())show_result(6,3,flag);
    if(ui->num_59->hasFocus())show_result(6,4,flag);
    if(ui->num_60->hasFocus())show_result(6,5,flag);
    if(ui->num_61->hasFocus())show_result(6,6,flag);
    if(ui->num_62->hasFocus())show_result(6,7,flag);
    if(ui->num_63->hasFocus())show_result(6,8,flag);
    if(ui->num_64->hasFocus())show_result(7,0,flag);
    if(ui->num_65->hasFocus())show_result(7,1,flag);
    if(ui->num_66->hasFocus())show_result(7,2,flag);
    if(ui->num_67->hasFocus())show_result(7,3,flag);
    if(ui->num_68->hasFocus())show_result(7,4,flag);
    if(ui->num_69->hasFocus())show_result(7,5,flag);
    if(ui->num_70->hasFocus())show_result(7,6,flag);
    if(ui->num_71->hasFocus())show_result(7,7,flag);
    if(ui->num_72->hasFocus())show_result(7,8,flag);
    if(ui->num_73->hasFocus())show_result(8,0,flag);
    if(ui->num_74->hasFocus())show_result(8,1,flag);
    if(ui->num_75->hasFocus())show_result(8,2,flag);
    if(ui->num_76->hasFocus())show_result(8,3,flag);
    if(ui->num_77->hasFocus())show_result(8,4,flag);
    if(ui->num_78->hasFocus())show_result(8,5,flag);
    if(ui->num_79->hasFocus())show_result(8,6,flag);
    if(ui->num_80->hasFocus())show_result(8,7,flag);
    if(ui->num_81->hasFocus())show_result(8,8,flag);


}

void MainWindow::on_level_box_currentIndexChanged(int index)
{
    timer->stop();    //计时器停止
    TimeRecord->setHMS(0,0,0); //时间设为0
    ui->lcdNumber->display(TimeRecord->toString("hh:mm:ss")); //显示00:00:00
    isStart = false;

    QString filename;
    index = ui->level_box->currentIndex();

    if(index==0){
        int sudoku[9][9] = {
            { 0, 6, 1, 0, 3, 0, 0, 2, 0 },
            { 0, 5, 0, 0, 0, 8, 1, 0, 7 },
            { 0, 0, 0, 0, 0, 7, 0, 3, 4 },
            { 0, 0, 9, 0, 0, 6, 0, 7, 8 },
            { 0, 0, 3, 2, 0, 9, 5, 0, 0 },
            { 5, 7, 0, 3, 0, 0, 9, 0, 0 },
            { 1, 9, 0, 7, 0, 0, 0, 0, 0 },
            { 8, 0, 2, 4, 0, 0, 0, 6, 0 },
            { 0, 4, 0, 0, 1, 0, 2, 5, 0 }
        };
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                question[i][j]=sudoku[i][j];
            }
        }
    }
    //简单难度
    if(index==1){
        int sudoku[9][9] = {
            { 0, 0, 0, 7, 0, 0, 8, 0, 0 },
            { 0, 0, 0, 0, 4, 0, 0, 3, 0 },
            { 0, 0, 0, 0, 0, 9, 0, 0, 1 },
            { 6, 0, 0, 5, 0, 0, 0, 0, 0 },
            { 0, 1, 0, 0, 3, 0, 0, 4, 0 },
            { 0, 0, 5, 0, 0, 1, 0, 0, 7 },
            { 5, 0, 0, 2, 0, 0, 6, 0, 0 },
            { 0, 3, 0, 0, 8, 0, 0, 9, 0 },
            { 0, 0, 7, 0, 0, 0, 0, 0, 2 }
        };
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                question[i][j]=sudoku[i][j];
            }
        }
    }
    //中等难度
    if(index==2)
    {
        int sudoku[9][9]= {
          { 0, 0, 0, 0, 5, 7, 0, 8, 0 },
          { 3, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 2, 9, 0, 0, 4, 0, 0 },
          { 2, 0, 0, 8, 0, 9, 3, 0, 0 },
          { 4, 0, 0, 0, 0, 0, 0, 0, 7 },
          { 0, 0, 7, 4, 0, 6, 0, 0, 8 },
          { 0, 0, 6, 0, 0, 2, 7, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
          { 0, 9, 0, 3, 1, 0, 0, 0, 0 }
        };
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                question[i][j]=sudoku[i][j];
            }
        }
    }
    //困难难度
    if(index==3){
        int sudoku[9][9]= {
          { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                question[i][j]=sudoku[i][j];
            }
        }
    }
    //变态难度
}

void MainWindow::on_pushButton_replay_clicked()
{
    timer->stop();    //计时器停止
    TimeRecord->setHMS(0,0,0); //时间设为0
    ui->lcdNumber->display(TimeRecord->toString("hh:mm:ss")); //显示00:00:00
    isStart = false;

    QTextEdit* Num [9][9] ={ui->num_01,ui->num_02,ui->num_03,ui->num_04,ui->num_05,ui->num_06,ui->num_07,ui->num_08,ui->num_09
        ,ui->num_10,ui->num_11,ui->num_12,ui->num_13,ui->num_14,ui->num_15,ui->num_16,ui->num_17,ui->num_18
        ,ui->num_19,ui->num_20,ui->num_21,ui->num_22,ui->num_23,ui->num_24,ui->num_25,ui->num_26,ui->num_27
        ,ui->num_28,ui->num_29,ui->num_30,ui->num_31,ui->num_32,ui->num_33,ui->num_34,ui->num_35,ui->num_36
        ,ui->num_37,ui->num_38,ui->num_39,ui->num_40,ui->num_41,ui->num_42,ui->num_43,ui->num_44,ui->num_45
        ,ui->num_46,ui->num_47,ui->num_48,ui->num_49,ui->num_50,ui->num_51,ui->num_52,ui->num_53,ui->num_54
        ,ui->num_55,ui->num_56,ui->num_57,ui->num_58,ui->num_59,ui->num_60,ui->num_61,ui->num_62,ui->num_63
        ,ui->num_64,ui->num_65,ui->num_66,ui->num_67,ui->num_68,ui->num_69,ui->num_70,ui->num_71,ui->num_72
        ,ui->num_73,ui->num_74,ui->num_75,ui->num_76,ui->num_77,ui->num_78,ui->num_79,ui->num_80,ui->num_81};

    for(int i=0;i<9;i++){
        for(int j=0;j<9;j++){
            if(question[i][j]==0){
                Num[i][j]->setText("");
                continue;
            }
            Num[i][j]->setText(QString::number(question[i][j]));
        }
    }
}

void MainWindow::updateTime200()
{
    *TimeRecord = TimeRecord->addSecs(1);
    ui->lcdNumber->display(TimeRecord->toString("hh:mm:ss"));
}

void MainWindow::on_pushButton_begin_clicked()
{
    if(!isStart) //尚未开始 开始计时
    {
        timer->start(1000);
        QTextEdit* Num [9][9] ={ui->num_01,ui->num_02,ui->num_03,ui->num_04,ui->num_05,ui->num_06,ui->num_07,ui->num_08,ui->num_09
            ,ui->num_10,ui->num_11,ui->num_12,ui->num_13,ui->num_14,ui->num_15,ui->num_16,ui->num_17,ui->num_18
            ,ui->num_19,ui->num_20,ui->num_21,ui->num_22,ui->num_23,ui->num_24,ui->num_25,ui->num_26,ui->num_27
            ,ui->num_28,ui->num_29,ui->num_30,ui->num_31,ui->num_32,ui->num_33,ui->num_34,ui->num_35,ui->num_36
            ,ui->num_37,ui->num_38,ui->num_39,ui->num_40,ui->num_41,ui->num_42,ui->num_43,ui->num_44,ui->num_45
            ,ui->num_46,ui->num_47,ui->num_48,ui->num_49,ui->num_50,ui->num_51,ui->num_52,ui->num_53,ui->num_54
            ,ui->num_55,ui->num_56,ui->num_57,ui->num_58,ui->num_59,ui->num_60,ui->num_61,ui->num_62,ui->num_63
            ,ui->num_64,ui->num_65,ui->num_66,ui->num_67,ui->num_68,ui->num_69,ui->num_70,ui->num_71,ui->num_72
            ,ui->num_73,ui->num_74,ui->num_75,ui->num_76,ui->num_77,ui->num_78,ui->num_79,ui->num_80,ui->num_81};

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(question[i][j]==0){
                    Num[i][j]->setText("");
                    continue;
                }
                Num[i][j]->setText(QString::number(question[i][j]));
            }
        }
        isStart = !isStart;
    }
    else //已经开始，暂停后开始
    {
        timer->start();
    }
}

void MainWindow::on_pushButton_delete_clicked()
{
    int flag=0;
    if(ui->num_01->hasFocus())show_result(0,0,flag);
    if(ui->num_02->hasFocus())show_result(0,1,flag);
    if(ui->num_03->hasFocus())show_result(0,2,flag);
    if(ui->num_04->hasFocus())show_result(0,3,flag);
    if(ui->num_05->hasFocus())show_result(0,4,flag);
    if(ui->num_06->hasFocus())show_result(0,5,flag);
    if(ui->num_07->hasFocus())show_result(0,6,flag);
    if(ui->num_08->hasFocus())show_result(0,7,flag);
    if(ui->num_09->hasFocus())show_result(0,8,flag);
    if(ui->num_10->hasFocus())show_result(1,0,flag);
    if(ui->num_11->hasFocus())show_result(1,1,flag);
    if(ui->num_12->hasFocus())show_result(1,2,flag);
    if(ui->num_13->hasFocus())show_result(1,3,flag);
    if(ui->num_14->hasFocus())show_result(1,4,flag);
    if(ui->num_15->hasFocus())show_result(1,5,flag);
    if(ui->num_16->hasFocus())show_result(1,6,flag);
    if(ui->num_17->hasFocus())show_result(1,7,flag);
    if(ui->num_18->hasFocus())show_result(1,8,flag);
    if(ui->num_19->hasFocus())show_result(2,0,flag);
    if(ui->num_20->hasFocus())show_result(2,1,flag);
    if(ui->num_21->hasFocus())show_result(2,2,flag);
    if(ui->num_22->hasFocus())show_result(2,3,flag);
    if(ui->num_23->hasFocus())show_result(2,4,flag);
    if(ui->num_24->hasFocus())show_result(2,5,flag);
    if(ui->num_25->hasFocus())show_result(2,6,flag);
    if(ui->num_26->hasFocus())show_result(2,7,flag);
    if(ui->num_27->hasFocus())show_result(2,8,flag);
    if(ui->num_28->hasFocus())show_result(3,0,flag);
    if(ui->num_29->hasFocus())show_result(3,1,flag);
    if(ui->num_30->hasFocus())show_result(3,2,flag);
    if(ui->num_31->hasFocus())show_result(3,3,flag);
    if(ui->num_32->hasFocus())show_result(3,4,flag);
    if(ui->num_33->hasFocus())show_result(3,5,flag);
    if(ui->num_34->hasFocus())show_result(3,6,flag);
    if(ui->num_35->hasFocus())show_result(3,7,flag);
    if(ui->num_36->hasFocus())show_result(3,8,flag);
    if(ui->num_37->hasFocus())show_result(4,0,flag);
    if(ui->num_38->hasFocus())show_result(4,1,flag);
    if(ui->num_39->hasFocus())show_result(4,2,flag);
    if(ui->num_40->hasFocus())show_result(4,3,flag);
    if(ui->num_41->hasFocus())show_result(4,4,flag);
    if(ui->num_42->hasFocus())show_result(4,5,flag);
    if(ui->num_43->hasFocus())show_result(4,6,flag);
    if(ui->num_44->hasFocus())show_result(4,7,flag);
    if(ui->num_45->hasFocus())show_result(4,8,flag);
    if(ui->num_46->hasFocus())show_result(5,0,flag);
    if(ui->num_47->hasFocus())show_result(5,1,flag);
    if(ui->num_48->hasFocus())show_result(5,2,flag);
    if(ui->num_49->hasFocus())show_result(5,3,flag);
    if(ui->num_50->hasFocus())show_result(5,4,flag);
    if(ui->num_51->hasFocus())show_result(5,5,flag);
    if(ui->num_52->hasFocus())show_result(5,6,flag);
    if(ui->num_53->hasFocus())show_result(5,7,flag);
    if(ui->num_54->hasFocus())show_result(5,8,flag);
    if(ui->num_55->hasFocus())show_result(6,0,flag);
    if(ui->num_56->hasFocus())show_result(6,1,flag);
    if(ui->num_57->hasFocus())show_result(6,2,flag);
    if(ui->num_58->hasFocus())show_result(6,3,flag);
    if(ui->num_59->hasFocus())show_result(6,4,flag);
    if(ui->num_60->hasFocus())show_result(6,5,flag);
    if(ui->num_61->hasFocus())show_result(6,6,flag);
    if(ui->num_62->hasFocus())show_result(6,7,flag);
    if(ui->num_63->hasFocus())show_result(6,8,flag);
    if(ui->num_64->hasFocus())show_result(7,0,flag);
    if(ui->num_65->hasFocus())show_result(7,1,flag);
    if(ui->num_66->hasFocus())show_result(7,2,flag);
    if(ui->num_67->hasFocus())show_result(7,3,flag);
    if(ui->num_68->hasFocus())show_result(7,4,flag);
    if(ui->num_69->hasFocus())show_result(7,5,flag);
    if(ui->num_70->hasFocus())show_result(7,6,flag);
    if(ui->num_71->hasFocus())show_result(7,7,flag);
    if(ui->num_72->hasFocus())show_result(7,8,flag);
    if(ui->num_73->hasFocus())show_result(8,0,flag);
    if(ui->num_74->hasFocus())show_result(8,1,flag);
    if(ui->num_75->hasFocus())show_result(8,2,flag);
    if(ui->num_76->hasFocus())show_result(8,3,flag);
    if(ui->num_77->hasFocus())show_result(8,4,flag);
    if(ui->num_78->hasFocus())show_result(8,5,flag);
    if(ui->num_79->hasFocus())show_result(8,6,flag);
    if(ui->num_80->hasFocus())show_result(8,7,flag);
    if(ui->num_81->hasFocus())show_result(8,8,flag);
}

void MainWindow::on_pushButton_submit_clicked()
{
    QTextEdit* Num [9][9] ={ui->num_01,ui->num_02,ui->num_03,ui->num_04,ui->num_05,ui->num_06,ui->num_07,ui->num_08,ui->num_09
        ,ui->num_10,ui->num_11,ui->num_12,ui->num_13,ui->num_14,ui->num_15,ui->num_16,ui->num_17,ui->num_18
        ,ui->num_19,ui->num_20,ui->num_21,ui->num_22,ui->num_23,ui->num_24,ui->num_25,ui->num_26,ui->num_27
        ,ui->num_28,ui->num_29,ui->num_30,ui->num_31,ui->num_32,ui->num_33,ui->num_34,ui->num_35,ui->num_36
        ,ui->num_37,ui->num_38,ui->num_39,ui->num_40,ui->num_41,ui->num_42,ui->num_43,ui->num_44,ui->num_45
        ,ui->num_46,ui->num_47,ui->num_48,ui->num_49,ui->num_50,ui->num_51,ui->num_52,ui->num_53,ui->num_54
        ,ui->num_55,ui->num_56,ui->num_57,ui->num_58,ui->num_59,ui->num_60,ui->num_61,ui->num_62,ui->num_63
        ,ui->num_64,ui->num_65,ui->num_66,ui->num_67,ui->num_68,ui->num_69,ui->num_70,ui->num_71,ui->num_72
        ,ui->num_73,ui->num_74,ui->num_75,ui->num_76,ui->num_77,ui->num_78,ui->num_79,ui->num_80,ui->num_81};

    int flag=0;
    //检查有没有填完
    for(int i=0;i<9;i++){
        for(int j=0;j<9;j++){
            if(Num[i][j]->toPlainText()==""){
            QMessageBox::information(NULL,QStringLiteral("提交失败"),QStringLiteral("还没有填完 不能提交"));
            flag=1;
            return;
            }
        }
    }
    //检查填完后是否填对
    if(!flag){
        get_number();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
//                qDebug() << sudoku_array[i][j] << " " << Sudoku[i][j];
                if(sudoku_array[i][j]!=Sudoku[i][j]+1){
                    QMessageBox::information(NULL,QStringLiteral("游戏失败"),QStringLiteral("你输啦！"));

                    return;
                }
            }
        }
        QMessageBox::information(NULL,QStringLiteral("游戏成功"),QStringLiteral("你赢啦！"));
        timer->stop();
        isStart = false;
    }
}

void MainWindow::on_pushButton_stop_clicked()
{
    timer->stop();
}
