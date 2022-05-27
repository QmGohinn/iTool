#ifndef ITOOLGLOBAL
#define ITOOLGLOBAL

#include <QDesktopServices>
#include <QUrl>
#include <QDebug>
#include <QMessageBox>

#include <_APL/iToolMainForm/itoolmainform.h>

class iToolGlobal{

private:

public:
    static int g_currentStyle;
    static QVector<QString> g_widgetStyle;

public:
    static iToolmainForm* g_mainForm;
    static void OpenUrl(QString url);
};

#endif
