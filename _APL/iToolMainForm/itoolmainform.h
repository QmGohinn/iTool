#ifndef ITOOLMAINFORM_H
#define ITOOLMAINFORM_H

#include <QMainWindow>

#include <_APL/selfModuleCheckDlg/selfmodulecheckdlg.h>
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
    void on_action_iTool_triggered();
    void on_action_Q_triggered();
    void on_action_M_triggered();
    void on_action_X_triggered();
    void on_action_P_triggered();

    void on_action_S_triggered();

private:
    Ui::iToolmainForm *ui;

public:
    MainTab1Form* m_tab1Form = nullptr;
    MainTab1Form* m_tab2Form = nullptr;
    selfModuleCheckDlg* m_checkDlg = nullptr;

    void InputSelfModule();
    void ExitSelfModule();
    void CancelCheck();
};

#endif // ITOOLMAINFORM_H
