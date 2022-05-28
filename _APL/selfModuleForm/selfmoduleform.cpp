#include "selfmoduleform.h"
#include "ui_selfmoduleform.h"

#include <_BASE/iToolGlobal.h>

selfModuleForm::selfModuleForm(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::selfModuleForm)
{
    ui->setupUi(this);
}

selfModuleForm::~selfModuleForm()
{
    delete ui;
}

void selfModuleForm::on_pushButton_3_clicked()
{
    iToolGlobal::OpenUrl("https://fs1.app/");
}

void selfModuleForm::on_pushButton_6_clicked()
{
    iToolGlobal::OpenUrl("www.pornhub.com");
}

void selfModuleForm::on_pushButton_5_clicked()
{
    iToolGlobal::OpenUrl("https://www.youtube.com/");
}

void selfModuleForm::on_pushButton_2_clicked()
{
    iToolGlobal::OpenUrl("https://asianmomxxx.com/zh/");
}

void selfModuleForm::on_pushButton_clicked()
{
    iToolGlobal::OpenUrl("https://141jj.com/");
}

void selfModuleForm::on_pushButton_12_clicked()
{
    iToolGlobal::OpenUrl("https://www.kmaa42.com/");
    iToolGlobal::OpenUrl("https://www.kmbbb54.com/");
}

void selfModuleForm::on_pushButton_10_clicked()
{
    iToolGlobal::OpenUrl("https://www.tubeoffline.com/");
}

void selfModuleForm::on_pushButton_11_clicked()
{
    iToolGlobal::OpenUrl("https://theporndude.com/zh");
}

void selfModuleForm::on_pushButton_7_clicked()
{
    iToolGlobal::OpenUrl("https://legs.a-hadaka.jp/");
}

void selfModuleForm::on_pushButton_8_clicked()
{
    iToolGlobal::OpenUrl("https://javhd.com/zh/");
}

void selfModuleForm::on_pushButton_4_clicked()
{
    iToolGlobal::OpenUrl("https://twitter.com/explore");
}
