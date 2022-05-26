#ifndef ITOOLGLOBAL
#define ITOOLGLOBAL

#include <QDesktopServices>
#include <QUrl>
#include <QDebug>
#include <QMessageBox>

class iToolGlobal{

private:

public:
    static int g_currentStyle;
    static QVector<QString> g_widgetStyle;

public:
    static void OpenUrl(QString url);
};

#endif
