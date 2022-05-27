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

private:
    Ui::selfModuleForm *ui;
};

#endif // SELFMODULEFORM_H
