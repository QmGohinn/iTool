#include <QApplication>
#include <_APL/iToolMainForm/itoolmainform.h>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    QApplication::setStyle("Windows");
    iToolmainForm w;
    w.show();
    return a.exec();
}
