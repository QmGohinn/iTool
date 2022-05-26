#include "itoolmainform.h"
#include "ui_itoolmainform.h"
#include <_APL/MainFormTab1/maintab1form.h>

#include <QCloseEvent>
#include <_BASE/iToolGlobal.hpp>

iToolmainForm::iToolmainForm(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::iToolmainForm)
{
    ui->setupUi(this);
    this->showMaximized();

    if(m_tab1Form == nullptr){
        m_tab1Form = new MainTab1Form;
    }

    ui->tabWidget->addTab(m_tab1Form, "导航页");
}

iToolmainForm::~iToolmainForm()
{
    delete ui;
}

void iToolmainForm::closeEvent(QCloseEvent *e)
{
    if(0 == QMessageBox::information(this, "提示", "您确定退出iTool吗?", tr("安全退出"), tr("取消关闭")))
    {
        this->~iToolmainForm();
        e->accept();
    }
    else
    {
        e->ignore();
    }
}

/// open baidu
void iToolmainForm::on_actionBaidu_D_triggered()
{
    iToolGlobal::OpenUrl("https://www.baidu.com/");
}

/// open bilibili
void iToolmainForm::on_actionBilibili_triggered()
{
    iToolGlobal::OpenUrl("https://www.bilibili.com/");
}

/// exit itool
void iToolmainForm::on_action_iTool_triggered()
{
    this->close();
}

/// open xust mainpage
void iToolmainForm::on_action_X_triggered()
{
    iToolGlobal::OpenUrl("https://www.xust.edu.cn/");
}

/// open xust jwc
void iToolmainForm::on_action_E_triggered()
{
    iToolGlobal::OpenUrl("https://jwc.xust.edu.cn/");
}

/// shift widget style
void iToolmainForm::on_action_Q_triggered()
{
    QApplication::setStyle(iToolGlobal::g_widgetStyle.at(iToolGlobal::g_currentStyle % iToolGlobal::g_widgetStyle.size()));
    iToolGlobal::g_currentStyle += 1;
}
