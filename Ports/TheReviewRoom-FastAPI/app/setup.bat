@ECHO OFF
PUSHD ..
python -m venv .venv
.venv\Scripts\Activate.ps1
pip install -r requirements.txt
POPD
@REM PAUSE