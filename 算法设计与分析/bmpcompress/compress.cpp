#include "compress.h"
#include <QDebug>

compress::compress(QWidget *parent) : QWidget(parent)
{

}

int length(int i)
    {
        int k = 1;
        i = i / 2;
        while (i>0)
        {
            k++;
            i = i / 2;
        }
        return k;
    }
void dp(int n, unsigned int *p, unsigned int *s, unsigned int *l, unsigned int *b){
    int Lmax = 256, header = 11;
    s[0] = 0;
    for (int i = 1; i <= n; i++) {
        b[i] = length(p[i]);//计算像素点p需要的存储位数
    unsigned int bmax = b[i];
    s[i] = s[i - 1] + bmax;
    l[i] = 1;
    for (int j = 2; j <= i && j <= Lmax; j++) {
        if (bmax<b[i - j + 1]) {
            bmax = b[i - j + 1];
            }
        if (s[i]>s[i - j] + j * bmax) {
            s[i] = s[i - j] + j * bmax;
            l[i] = j;
        }
    }
    s[i] += header;
    }
}

void Traceback(int n, int& i, unsigned int s[], unsigned int l[])
{
    if (n == 0)
        return;
    Traceback(n - l[n], i, s, l);
    s[i++] = n - l[n];//重新为s[]数组赋值，用来存储分段位置
}

int Output(unsigned int s[], unsigned int l[], unsigned int b[], int n)
{
    int m = 0;
    Traceback(n, m, s, l);
    s[m] = n;
    for (int j = 1; j <= m; j++)
    {
        l[j] = l[s[j]];
        b[j] = b[s[j]];
    }
    return m;
}

void compress::CompressBmp(string filename){
    emit Signal1(1);
    ifstream f(&filename[0],ios::binary);
    if(f){
        //文件头信息
        f.read((char*)&FileHeader,sizeof(FileHeader));
        emit Signal1(3);
        //信息头信息
        f.read((char*)&InfoHeader,sizeof(InfoHeader));
        //颜色表信息的位数等于总位数减去文件头信息位数减去信息头信息位数
        f.read((char*)&ipRGB,FileHeader.bfOffBits-sizeof(InfoHeader)-sizeof(FileHeader));
        //决定每行都多少个数
        int lineNum = (InfoHeader.biWidth*InfoHeader.biBitCount / 8 + 3 ) / 4 * 4;
        //像素总数
        int Num = InfoHeader.biWidth*InfoHeader.biHeight;
        //跳转开始读像素信息
        f.seekg(FileHeader.bfOffBits,0);
        imgData = new unsigned char*[InfoHeader.biHeight];
        for(int i =0;i<InfoHeader.biHeight;i++){
            imgData[i] = new unsigned char [lineNum];
            f.read((char*)imgData[i],lineNum);
        }
        f.close();
        //读完了关闭了
        emit Signal1(7);

        filename +=".img";
        ofstream n_f(&filename[0],ios::binary);
        if(!n_f){
            QMessageBox::information(NULL,"error","fail to open file!");
            return;
        }
        n_f.write((char*)&FileHeader,sizeof(FileHeader));
        n_f.write((char*)&InfoHeader,sizeof(InfoHeader));
        n_f.write((char*)&ipRGB,FileHeader.bfOffBits - sizeof(InfoHeader)- sizeof(FileHeader));
        emit Signal1(10);

        //蛇形存储
        snake = new unsigned int[Num + 1];
        snake[0] = 0;
        unsigned int flag = 1;
        for (int i = 0; i < InfoHeader.biHeight; i++)//蛇形
        {
            if (i % 2 == 0)//奇数行
            {
                for (int j = 0; j < lineNum; j++)
                {
                    snake[flag] = imgData[i][j];
                    flag++;
                }
            }
            else if (i % 2 != 0)//偶数行
            {
                for (int j = lineNum - 1; j >= 0; j--)
                {
                    snake[flag] = imgData[i][j];
                    flag++;
                }
            }
        }
        emit Signal1(15);

        unsigned int *s = new unsigned int [Num+1];//总位数
        s[0]=0;
        unsigned int *b = new unsigned int [Num+1];//每段最大位数
        b[0]=0;
        unsigned int *l = new unsigned int [Num+1];//每段个数
        l[0]=0;

        dp(Num,snake,s,l,b);
        emit Signal1(20);

        int pointNum = Output(s,l,b,Num);
        int begin = 1;
        for(int i =1;i<=pointNum;i++){
            //重新设置每段最大的位数
            int bmax=0;
            for(unsigned int j=begin;j<begin + l[i];j++){
                bmax = max(bmax,length(snake[j]));
            }
            begin+=l[i];
            b[i]=bmax;
        }

        emit Signal1(25);

        //开始写操作
        flag=0;
        begin=1;
        int track=0;
        for(int i=1;i<=pointNum;i++){
            if(track+8<32){
                flag<<=8;
                flag = flag|(l[i]-1);
                track+=8;
            }
            else if(track+8==0){
                flag<<=8;
                flag = flag|(l[i]-1);
                n_f.write((char*)&flag,sizeof(flag));
                flag=0;
                track=0;
            }
            else{   //track+8>32 就分段存32的存
                int t =32-track;
                flag<<=t;
                flag= flag|(l[i]-1)>>(8-t);//先存前t位
                n_f.write((char*)&flag,sizeof(flag));
                flag=0;
                flag = flag|(((l[i]-1)) << (32-8+t))>>(32-8+t); //再存后8-t位
                track=8-t;
            }//8位
            if(track+3<32){
                flag<<=3;
                flag = flag|(b[i]-1);
                track+=3;
            }
            else if(track+3==32){
                flag<<=3;
                flag = flag|(b[i]-1);
                n_f.write((char*)&flag,sizeof(flag));
                flag=0;
                track=0;
            }
            else{
                int t =32-track;
                flag<<=t;
                flag= flag|(b[i]-1)>>(3-t);//先存前t位
                n_f.write((char*)&flag,sizeof(flag));
                flag=0;
                flag = flag|(((b[i]-1)) << (32-3+t))>>(32-3+t); //再存后3-t位
                track = 3-t;
            }//3位
            for(unsigned int j=0;j<l[i];j++){
                if(track+b[i]<32){
                    flag<<=b[i];
                    flag = flag|snake[begin];
                    begin++;
                    track+=b[i];
                }
                else if(track+b[i]==32){
                    flag<<=b[i];
                    flag=flag|snake[begin];
                    begin++;
                    n_f.write((char*)&flag,sizeof(flag));
                    flag=0;
                    track=0;
                }
                else{
                    int t=32 -track;
                    flag<<=t;
                    flag = flag|(snake[begin]>>(b[i]-t));
                    n_f.write((char*)&flag,sizeof(flag));
                    flag=0;
                    flag = flag|(snake[begin]<<(32-b[i]+t)>>(32-b[i]+t));
                    track = b[i]-t;
                    begin++;
                    if(begin==Num+1){//最后一个
                        flag<<=(32-track);//补0
                        n_f.write((char*)&flag,sizeof(flag));
                        flag=0;
                    }
                }
            }
            emit Signal1(25+i/pointNum/2);
        }
        emit Signal1(95);
        n_f.close();
    }
    else{
        QMessageBox::information(NULL,"error","fail to open file");
        emit Signal1(99);
        return;
    }
}

void compress::UncompressBmp(string filename){
    emit Signal2(1);

    ifstream f(&filename[0],ios::binary);
    if(f){
        f.read((char*)&FileHeader, sizeof(FileHeader));
        f.read((char*)&InfoHeader, sizeof(InfoHeader));
        f.read((char*)&ipRGB, FileHeader.bfOffBits - sizeof(InfoHeader) - sizeof(FileHeader));
        f.seekg(FileHeader.bfOffBits, 0);
        emit Signal2(10);
        filename += ".bmp";
        ofstream fout(&filename[0], ios::binary);
        if (!fout){
            QMessageBox::information(NULL,"error","fail to open file");
            emit Signal2(99);
            return;
        }
        fout.write((char*)&FileHeader, sizeof(FileHeader));
        fout.write((char*)&InfoHeader, sizeof(InfoHeader));
        fout.write((char*)&ipRGB, FileHeader.bfOffBits - sizeof(InfoHeader) - sizeof(FileHeader));
        //头文件写入
        emit Signal2(20);
        vector<unsigned int>v;
        unsigned int temp = 0;//中间变量
        while(!f.eof())
        {
            f.read((char*)&temp, sizeof(temp));//这里temp用来暂存数据
            v.push_back(temp);
        }
        v.pop_back(); temp = 0;//退掉多出来的最后一个，并使中间变量为0
        emit Signal2(30);
        f.close();
        //开始分析数据
        int count = InfoHeader.biWidth*InfoHeader.biHeight;
        int track = 0;//用来记录跟踪和写回了几个像素
        int l, b;//个数，位数
        vector<unsigned char>data;
        while (1)
        {
            if (track + 8 < 32)
            {//没毛病
                l = ((v[0] << track) >> 24) + 1;
                track += 8;
            }
            else if (track + 8 == 32)
            {
                l = ((v[0] << 24) >> 24) + 1;
                track = 0;
                v.erase(v.begin());
            }
            else// track + 8 > 32
            {
                int t = 32 - track;//先存t位
                l = (v[0] << track) >> track;
                track = 8 - t;//再用8-t位
                v.erase(v.begin());
                l = ((l << track) | (v[0] >> (32 - track))) + 1;
            }
            //8位读完
            if (track + 3 < 32)
            {
                b = ((v[0] << track) >> 29) + 1;
                track += 3;
            }
            else if (track + 3 == 32)
            {
                b = ((v[0] << 29) >> 29) + 1;
                track = 0;
                v.erase(v.begin());
            }
            else// track + 3 >32
            {
                int t = 32 - track;//先存t位
                b = (v[0] << track) >> track;
                track = 3 - t;//再用3-t位
                v.erase(v.begin());
                b = ((b << track) | (v[0] >> (32 - track))) + 1;
            }
            //3位读完
            for (int i = 0; i < l; i++)
            {
                if (b + track < 32)
                {
                    temp = ((v[0] << track) >> (32 - b));
                    data.push_back(temp);
                    if (data.size() == count)goto Finsh;
                    track += b;
                }
                else if (b + track == 32)
                {
                    temp = (v[0] << track) >> track;
                    data.push_back(temp);
                    if (data.size() == count)goto Finsh;
                    track = 0;
                    v.erase(v.begin());
                }
                else// y + track > 32
                {
                    int t = 32 - track;//先存t位
                    temp = (v[0] << track) >> track;
                    track = b - t;//再用b-t位
                    v.erase(v.begin());
                    temp = (temp << track) | ((v[0]) >> (32 - track));
                    data.push_back(temp);
                    if (data.size() == count)goto Finsh;
                }
            }
        }
    Finsh:
        vector<unsigned char>Pixel;
        for (int i = 1; i <= InfoHeader.biHeight; i++)
        {
            for (int j = 1; j <= InfoHeader.biWidth; j++)
            {
                Pixel.push_back(data[0]);
                data.erase(data.begin());
            }
            if (i % 2 != 0)//奇数行
            {
                while (Pixel.size() > 0)
                {
                    fout.write((char*)&Pixel[0], sizeof(Pixel[0]));
                    Pixel.erase(Pixel.begin());
                }
            }
            else //偶数
            {
                while (Pixel.size() > 0)
                {
                    fout.write((char*)&Pixel.back(), sizeof(Pixel.back()));
                    Pixel.pop_back();
                }
            }
        }
        fout.close();
    }
    else{
            QMessageBox::information(NULL,"error","fail to open file");
            emit Signal2(99);
            return;
    }
    emit Signal2(90);
}
