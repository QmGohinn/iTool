#include <_BASE/iToolGlobal.h>

int iToolGlobal::g_currentStyle = 0;
QVector<QString> iToolGlobal::g_widgetStyle = {"Fusion", "windowsvista", "Windows"};

void iToolGlobal::OpenUrl(QString url){
    QDesktopServices::openUrl(QUrl(url));
}

iToolmainForm* iToolGlobal::g_mainForm = nullptr;
