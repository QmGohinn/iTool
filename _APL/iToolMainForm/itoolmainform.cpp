#include "itoolmainform.h"
#include "ui_itoolmainform.h"
#include <_APL/MainFormTab1/maintab1form.h>

#include <3rd/QHotKey/QHotkey/QHotkey>
#include <QCloseEvent>
#include <_BASE/iToolGlobal.h>

iToolmainForm::iToolmainForm(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::iToolmainForm)
{
    ui->setupUi(this);
    this->showMaximized();

    QHotkey *hotkey = new QHotkey(QKeySequence("Ctrl+Q"), true);
    connect(hotkey, &QHotkey::activated, [this](){
        this->showMaximized();
    });

    if(m_tab1Form == nullptr){
        m_tab1Form = new MainTab1Form;
    }

    ui->action_P->setCheckable(true);
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

/// exit itool
void iToolmainForm::on_action_iTool_triggered()
{
    this->close();
}

/// shift widget style
void iToolmainForm::on_action_Q_triggered()
{
    QApplication::setStyle(iToolGlobal::g_widgetStyle.at(iToolGlobal::g_currentStyle % iToolGlobal::g_widgetStyle.size()));
    iToolGlobal::g_currentStyle += 1;
}

void iToolmainForm::on_action_M_triggered()
{
    this->showMinimized();
    this->ExitSelfModule();
}

void iToolmainForm::on_action_X_triggered()
{
    this->showMaximized();
}

void iToolmainForm::on_action_P_triggered()
{
    if(ui->action_P->isChecked() == true){
        if(m_checkDlg == nullptr){
            m_checkDlg = new selfModuleCheckDlg(this);
        }
        m_checkDlg->move(20, 900);
        if(m_checkDlg->exec() == QDialog::Rejected){
            this->CancelCheck();
        }
    }
    else{
        ExitSelfModule();
    }
}

void iToolmainForm::InputSelfModule()
{
    if(m_tab2Form == nullptr){
        m_tab2Form = new MainTab1Form;
    }
    ui->tabWidget->addTab(m_tab2Form, "111");
}

void iToolmainForm::ExitSelfModule()
{
    ui->tabWidget->removeTab(ui->tabWidget->indexOf(m_tab2Form));
    ui->action_P->setChecked(false);
}

void iToolmainForm::CancelCheck()
{
    ui->action_P->setChecked(false);
}

void iToolmainForm::on_action_S_triggered()
{
    auto _bef = ui->tabWidget->currentWidget();
    ui->tabWidget->setCurrentIndex(ui->tabWidget->currentIndex() + 1);
    auto _aft = ui->tabWidget->currentWidget();
    if(_bef == _aft){
        ui->tabWidget->setCurrentIndex(0);
    }
}
