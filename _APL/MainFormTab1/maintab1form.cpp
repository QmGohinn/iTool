#include "maintab1form.h"
#include "ui_maintab1form.h"

#include <_BASE/iToolGlobal.h>

MainTab1Form::MainTab1Form(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::MainTab1Form)
{
    ui->setupUi(this);
}

MainTab1Form::~MainTab1Form()
{
    delete ui;
}

void MainTab1Form::on_pushButton_17_clicked()
{
    iToolGlobal::OpenUrl("https://github.com/");
}

void MainTab1Form::on_pushButton_clicked()
{
    iToolGlobal::OpenUrl("https://www.neusoft.com/cn/");
}

void MainTab1Form::on_pushButton_24_clicked()
{
    iToolGlobal::OpenUrl("https://www.chazidian.com/");
}

void MainTab1Form::on_pushButton_29_clicked()
{
    iToolGlobal::OpenUrl("https://translate.google.cn");
}

void MainTab1Form::on_pushButton_34_clicked()
{
    iToolGlobal::OpenUrl("https://www.csdn.net/");
}

void MainTab1Form::on_pushButton_39_clicked()
{
    iToolGlobal::OpenUrl("https://www.linshiyouxiang.net/");
}

void MainTab1Form::on_pushButton_44_clicked()
{
    iToolGlobal::OpenUrl("https://tools.kalvinbg.cn/");
}

void MainTab1Form::on_pushButton_19_clicked()
{
    iToolGlobal::OpenUrl("https://stackoverflow.com/");
}

void MainTab1Form::on_pushButton_2_clicked()
{
    iToolGlobal::OpenUrl("https://leetcode.cn/");
}

void MainTab1Form::on_pushButton_23_clicked()
{
    iToolGlobal::OpenUrl("https://www.zhihu.com/");
}

void MainTab1Form::on_pushButton_28_clicked()
{
    iToolGlobal::OpenUrl("https://www.samsung.com/cn/");
}

void MainTab1Form::on_pushButton_35_clicked()
{
    iToolGlobal::OpenUrl("https://www.bangongziyuan.com/");
}

void MainTab1Form::on_pushButton_30_clicked()
{
    iToolGlobal::OpenUrl("https://www.lanqiao.cn/");
}

void MainTab1Form::on_pushButton_20_clicked()
{
    iToolGlobal::OpenUrl("https://it-ebooks.info/");
}

void MainTab1Form::on_pushButton_7_clicked()
{
    iToolGlobal::OpenUrl("https://github.com/justjavac/free-programming-books-zh_CN");
}

void MainTab1Form::on_pushButton_9_clicked()
{
    iToolGlobal::OpenUrl("https://www.51zxw.net/");
}

void MainTab1Form::on_pushButton_33_clicked()
{
    iToolGlobal::OpenUrl("https://www.getitfree.cn/");
}

void MainTab1Form::on_pushButton_42_clicked()
{
    iToolGlobal::OpenUrl("https://www.miguyu.com/");
}

void MainTab1Form::on_pushButton_37_clicked()
{
    iToolGlobal::OpenUrl("https://www.moulem.com/");
}

void MainTab1Form::on_pushButton_32_clicked()
{
    iToolGlobal::OpenUrl("https://36kr.com/");
}

void MainTab1Form::on_pushButton_18_clicked()
{
    iToolGlobal::OpenUrl("https://www.jd.com/");
}

void MainTab1Form::on_pushButton_3_clicked()
{
    iToolGlobal::OpenUrl("https://www.benbenla.com/");
}

void MainTab1Form::on_pushButton_22_clicked()
{
    iToolGlobal::OpenUrl("https://www.fu365.com/");
}

void MainTab1Form::on_pushButton_40_clicked()
{
    iToolGlobal::OpenUrl("https://www.baidu.com/");
}

void MainTab1Form::on_pushButton_12_clicked()
{
    iToolGlobal::OpenUrl("https://www.bilibili.com/");
}

void MainTab1Form::on_pushButton_10_clicked()
{
    iToolGlobal::OpenUrl("https://www.xust.edu.cn/");
}

void MainTab1Form::on_pushButton_11_clicked()
{
    iToolGlobal::OpenUrl("https://jwc.xust.edu.cn/");
}

void MainTab1Form::on_pushButton_27_clicked()
{
    iToolGlobal::OpenUrl("https://5iux.cn/");
}
