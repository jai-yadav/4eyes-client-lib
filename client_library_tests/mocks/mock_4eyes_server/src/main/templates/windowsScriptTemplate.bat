@echo off
SETLOCAL ENABLEDELAYEDEXPANSION 

REM JAVACMD should point to the JVM to execute

set JAVACMD=#JAVA_BINARY#

REM COMPONENTBINDIR variable must point to the directory where the #APP_NAME# package has been installed.
REM If you are running a Process Manager you could consider to set it according to the MKVBINDIR setting:
REM    COMPONENTBINDIR=<MyBINDIRPath>\#APP_NAME#
REM By default COMPONENTBINDIR links the Daemon binary path (i.e. %BINDIR_COMP%).

set COMPONENTBINDIR=%BINDIR_COMP%

REM JDBCDRIVERPATH should point JDBC driver jar for the configured DBMS

REM MySQL JDBC driver jar
REM  set JDBCDRIVERPATH=...\mysql-connector-java-5.1.13-bin.jar
REM MSSQL JDBC driver jar
REM  set JDBCDRIVERPATH=...\jtds-1.2.5.jar
REM ORACLE JDBC driver jar
REM  set JDBCDRIVERPATH=...\ojdbc6.jar
REM SYBASE JDBC driver jar
REM  set JDBCDRIVERPATH=...\jconn3.jar

REM set JDBCDRIVERPATH=driver\ojdbc6.jar

REM JAVAARGS allows you to customize the JVM.
REM For a complete list of available options, please consult the manual of your JVM vendor.
REM
REM The following settings highlight a few essential settings of the Sun JVM:
REM -server: enables the Server HotSpot VM
REM -Xmx1024m: limits the memory usage to 1024 Mb. If the process tries to use more memory, it is stopped.
REM -Xms1024m: preallocates 1024 Mb of memory. Generally, doing so makes the start-up faster.
REM -XX:+UseConcMarkSweepGC use concurrent mark sweep garbage collector
REM -XX:+CMSIncrementalMode enable incremental mode for concurrent mark sweep garbage collector

set JAVAARGS=#EXTRA_JVM_ARGUMENTS#

REM Parses args to support the following syntax:
REM 	-cshost <host1> <host2>
REM in addition to the standard syntax:
REM	-cshost "<host1> <host2>"
set host=
set port=
set hosts=
set ports=

for %%i in (%*) do (
	set Q=%%i
	set COD=!Q:~0,1!
	IF !Q!==-cshost (
		set host=1
		set port=0
	) ELSE (
		IF !Q!==-csport (
			set host=0
			set port=1
		) ELSE (
			IF !COD!==- (
				set host=0
				set port=0
			) ELSE (
				IF !host!==1 (
					set hosts=!hosts! !Q!
				) ELSE (
					IF !port!==1 set ports=!ports! !Q!
				)
			)
		)
        )
)

REM Launches the #APP_NAME# component
set ExtVar=
if not "1%hosts%"=="1" set ExtVar=-cshost "%hosts%"
if not "1%ports%"=="1" set ExtVar=%ExtVar% -csport "%ports%"


set REPO = %COMPONENTBINDIR%\lib
set CLASSPATH=#CLASSPATH#;%COMPONENTBINDIR%/#APP_NAME#.jar

%JAVACMD% %JAVAARGS% -classpath %CLASSPATH% #MAINCLASS# %* %ExtVar% 

ENDLOCAL
