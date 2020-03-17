#!/bin/sh

# JAVACMD must point to the JVM to execute

JAVACMD=@JAVA_BINARY@

# COMPONENTBINDIR variable must point to the directory where the @APP_NAME@ package has been installed.
# If you are running a Process Manager you could consider to set it according to the MKVBINDIR setting:
#    COMPONENTBINDIR=<MyBINDIRPath>/@APP_NAME@
# By default COMPONENTBINDIR links the Daemon binary path (i.e. ${BINDIR_COMP}).

COMPONENTBINDIR=${BINDIR_COMP}

# JDBCDRIVERPATH should point JDBC driver jar for the configured DBMS

# MySQL JDBC driver jar
#JDBCDRIVERPATH=.../mysql-connector-java-<version>/mysql-connector-java-<version>-bin.jar
# MSSQL JDBC driver jar
# MySQL JDBC driver jar
#  JDBCDRIVERPATH=.../mysql-connector-java-5.1.13-bin.jar
#  MSSQL JDBC driver jar
#  JDBCDRIVERPATH=.../jtds-1.2.5.jar
#  ORACLE JDBC driver jar
# JDBCDRIVERPATH=.../ojdbc6.jar
#  SYBASE JDBC driver jar
#  JDBCDRIVERPATH=.../jconn3.jar

#JDBCDRIVERPATH=driver/jtds-1.2.5.jar


# JAVAARGS allows you to customize the JVM.
# For a complete list of available options, please consult the manual of your JVM vendor.
#
# The following settings highlight a few essential settings of the Sun JVM:
# -server: enables the Server HotSpot VM
# -Xrs: reduces the signal usage. As consequence, the JVM doesn't trap the QUIT signal.
# -Xmx1024m: limits the memory usage to 1024 Mb. If the process tries to use more memory, it is stopped.
# -Xms1024m: preallocates 1024 Mb of memory. Generally, doing so makes the start-up faster.
# -XX:+UseConcMarkSweepGC use concurrent mark sweep garbage collector
# -XX:+CMSIncrementalMode enable incremental mode for concurrent mark sweep garbage collector

JAVAARGS="@EXTRA_JVM_ARGUMENTS@"



# Parses args to support the following syntax:
#   -cshost <host1> <host2>
# in addition to the standard syntax:
# -cshost "<host1> <host2>"
ports=
hosts=
port=0
host=0
for arg
do
    case "$arg" in
    -cshost) host=1 ; port=0 ;;
    -csport) port=1 ; host=0 ;;
    -*) port=0 ; host=0 ;;
    *)  if [ $host -ne 0 ]
        then
          hosts="$hosts $arg"
        fi ;
  if [ $port -ne 0 ]
  then
    ports="$ports $arg"
  fi ;;
    esac
done

REPO=${COMPONENTBINDIR}/lib
CLASSPATH=${COMPONENTBINDIR}/@APP_NAME@.jar:@CLASSPATH@


# Launches the @APP_NAME@ component
if [ -n "$hosts" ] 
then 
  exec ${JAVACMD} ${JAVAARGS} -classpath ${CLASSPATH} @MAINCLASS@ $* -cshost "$hosts" -csport "$ports"
else
  exec ${JAVACMD} ${JAVAARGS} -classpath ${CLASSPATH} @MAINCLASS@ $*
fi
