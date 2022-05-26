#ifndef ITOOLMAINFORM_H
#define ITOOLMAINFORM_H

#include <QMainWindow>

#include <_APL/MainFormTab1/maintab1form.h>

namespace Ui {
class iToolmainForm;
}

class iToolmainForm : public QMainWindow
{
    Q_OBJECT

public:
    explicit iToolmainForm(QWidget *parent = nullptr);
    ~iToolmainForm();

    void closeEvent(QCloseEvent* e);

private slots:
    void on_actionBaidu_D_triggered();
    void on_actionBilibili_triggered();
    void on_action_iTool_triggered();
    void on_action_X_triggered();
    void on_action_E_triggered();
    void on_action_Q_triggered();
    void on_action_M_triggered();

private:
    Ui::iToolmainForm *ui;

public:
    MainTab1Form* m_tab1Form = nullptr;
};

#endif // ITOOLMAINFORM_H
