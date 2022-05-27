include($$PWD/3rd/QHotKey/QHotkey.pri)

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

CONFIG += c++11

# The following define makes your compiler emit warnings if you use
# any Qt feature that has been marked deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

HEADERS += \
    _APL/MainFormTab1/maintab1form.h \
    _APL/iToolMainForm/itoolmainform.h \
    _APL/selfModuleCheckDlg/selfmodulecheckdlg.h \
    _BASE/iToolGlobal.h

SOURCES += \
    _APL/MainFormTab1/maintab1form.cpp \
    _APL/iToolMainForm/itoolmainform.cpp \
    _APL/selfModuleCheckDlg/selfmodulecheckdlg.cpp \
    _BASE/iToolGlobal.cpp \
    _BASE/main.cpp

DISTFILES += \
    3rd/readme.txt \
    BAT/readme.txt \
    CFG/readme.txt \
    DOC/readme.txt \
    _APL/readme.txt \
    _BASE/readme.txt \
    _BK/readme.txt \
    _old/readme.txt \
    _output/readme.txt \ \
    res/action/tab.ico

#程序LOGO
RC_ICONS += res/logo/logo.ico

#资源文件
RESOURCES += \
    iToolres.qrc

FORMS += \
    _APL/MainFormTab1/maintab1form.ui \
    _APL/iToolMainForm/itoolmainform.ui \
    _APL/selfModuleCheckDlg/selfmodulecheckdlg.ui
