@ECHO OFF
PUSHD ..
@REM uvicorn app.main:the_review_room --reload
@REM uvicorn app.main:the_review_room --host 0.0.0.0 --port 80
@REM uvicorn app.main:the_review_room --host 0.0.0.0 --port 8000 --reload
uvicorn app.main:the_review_room --host 0.0.0.0 --port 80 --reload
POPD
@REM PAUSE