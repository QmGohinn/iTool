#include "selfmoduleform.h"
#include "ui_selfmoduleform.h"

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
