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
        this->close();
    }
    else{
        ui->lineEdit->clear();
    }
}

void selfModuleCheckDlg::on_buttonBox_rejected()
{
    iToolGlobal::g_mainForm->CancelCheck();
    ui->lineEdit->clear();
    this->close();
}
