#include "selfmodulecheckdlg.h"
#include "ui_selfmodulecheckdlg.h"

#include <_BASE/iToolGlobal.h>

selfModuleCheckDlg::selfModuleCheckDlg(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::selfModuleCheckDlg)
{
    ui->setupUi(this);

    this->setWindowFlag(Qt::FramelessWindowHint);
}

selfModuleCheckDlg::~selfModuleCheckDlg()
{
    delete ui;
}

void selfModuleCheckDlg::on_buttonBox_accepted()
{
    if(ui->lineEdit->text().compare("221376") == 0){
        iToolGlobal::g_mainForm->InputSelfModule();
        ui->lineEdit->clear();
        this->accept();
    }
    else{
        ui->lineEdit->clear();
    }
}

void selfModuleCheckDlg::on_buttonBox_rejected()
{
    ui->lineEdit->clear();
    this->reject();
}
