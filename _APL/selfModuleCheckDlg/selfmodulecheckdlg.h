#ifndef SELFMODULECHECKDLG_H
#define SELFMODULECHECKDLG_H

#include <QDialog>

namespace Ui {
class selfModuleCheckDlg;
}

class selfModuleCheckDlg : public QDialog
{
    Q_OBJECT

public:
    explicit selfModuleCheckDlg(QWidget *parent = nullptr);
    ~selfModuleCheckDlg();

private slots:
    void on_buttonBox_accepted();

    void on_buttonBox_rejected();

private:
    Ui::selfModuleCheckDlg *ui;
};

#endif // SELFMODULECHECKDLG_H
