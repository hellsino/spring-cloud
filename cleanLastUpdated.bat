set REPOSITORY_PATH=C:\Users\zxwang\.m2\repository
rem searching...
for /f "delims=" %%i in ('dir /b /s "%REPOSITORY_PATH%\*lastUpdated*"') do (
    del /s /q %%i
)
rem searching finish
pause