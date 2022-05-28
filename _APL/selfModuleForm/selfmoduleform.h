#ifndef SELFMODULEFORM_H
#define SELFMODULEFORM_H

#include <QWidget>

namespace Ui {
class selfModuleForm;
}

class selfModuleForm : public QWidget
{
    Q_OBJECT

public:
    explicit selfModuleForm(QWidget *parent = nullptr);
    ~selfModuleForm();

private slots:
    void on_pushButton_3_clicked();

    void on_pushButton_6_clicked();

    void on_pushButton_5_clicked();

    void on_pushButton_2_clicked();

    void on_pushButton_clicked();

    void on_pushButton_12_clicked();

    void on_pushButton_10_clicked();

    void on_pushButton_11_clicked();

    void on_pushButton_7_clicked();

    void on_pushButton_8_clicked();

    void on_pushButton_4_clicked();

private:
    Ui::selfModuleForm *ui;
};

#endif // SELFMODULEFORM_H
