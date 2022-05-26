#ifndef MAINTAB1FORM_H
#define MAINTAB1FORM_H

#include <QWidget>

namespace Ui {
class MainTab1Form;
}

class MainTab1Form : public QWidget
{
    Q_OBJECT

public:
    explicit MainTab1Form(QWidget *parent = nullptr);
    ~MainTab1Form();

private:
    Ui::MainTab1Form *ui;
};

#endif // MAINTAB1FORM_H
