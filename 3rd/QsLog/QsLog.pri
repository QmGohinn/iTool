#DEFINES += QS_LOG_LINE_NUMBERS    # automatically writes the file and line for each log message
#DEFINES += QS_LOG_DISABLE         # logging code is replaced with a no-op
#DEFINES += QS_LOG_SEPARATE_THREAD # messages are queued and written from a separate thread

DEFINES += QS_LOG_LINE_NUMBERS

CONFIG += c++11

HEADERS += \
    $$PWD/include/QsLog.h \
    $$PWD/include/QsLogDest.h \
    $$PWD/include/QsLogDisableForThisFile.h \
    $$PWD/include/QsLogLevel.h \
    $$PWD/include/QsLogDestConsole.h \
    $$PWD/include/QsLogDestFile.h \
    $$PWD/include/QsLogDestFunctor.h

LIBS += -L$$PWD/lib -lQsLog2
