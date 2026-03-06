@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder (recursive, all subpackages)
for /r ..\src\main\java %%f in (*.java) do (
    javac -cp ..\src\main\java -Xlint:none -d ..\bin "%%f"
    IF ERRORLEVEL 1 (
        echo ********** BUILD FAILURE **********
        exit /b 1
    )
)

REM run the program, feed commands from run_test.txt and redirect output to ACTUAL.TXT
java -classpath ..\bin bataille.Bataille < run_test.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT expected.txt