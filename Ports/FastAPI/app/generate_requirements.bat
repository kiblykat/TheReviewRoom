@ECHO OFF
PUSHD ..
.venv\Scripts\Activate.ps1
pip freeze > app\requirements.txt
POPD
@REM PAUSE