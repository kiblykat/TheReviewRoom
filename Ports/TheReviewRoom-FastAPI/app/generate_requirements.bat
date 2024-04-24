@ECHO OFF

PUSHD ..

CALL .venv\Scripts\activate

pip freeze > app\requirements.txt

POPD

@REM PAUSE