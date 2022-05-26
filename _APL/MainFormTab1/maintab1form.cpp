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
