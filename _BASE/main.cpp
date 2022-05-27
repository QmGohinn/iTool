#include <QApplication>

#include <_APL/iToolMainForm/itoolmainform.h>
#include <_BASE/iToolGlobal.h>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    QApplication::setStyle("Windows");
    iToolmainForm w;
    iToolGlobal::g_mainForm = &w;
    w.show();
    return a.exec();
}
