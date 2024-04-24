pushd ..

.venv\Scripts\Activate.ps1

pip freeze > app\requirements.txt

popd

# pause