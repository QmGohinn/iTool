#include "maintab1form.h"
#include "ui_maintab1form.h"

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
