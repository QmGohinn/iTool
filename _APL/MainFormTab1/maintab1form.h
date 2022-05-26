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

private slots:
    void on_pushButton_17_clicked();
    void on_pushButton_clicked();
    void on_pushButton_24_clicked();
    void on_pushButton_29_clicked();
    void on_pushButton_34_clicked();
    void on_pushButton_39_clicked();
    void on_pushButton_44_clicked();

    void on_pushButton_19_clicked();

    void on_pushButton_2_clicked();

private:
    Ui::MainTab1Form *ui;
};

#endif // MAINTAB1FORM_H
