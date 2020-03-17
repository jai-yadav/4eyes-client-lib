

$invocationPath = $MyInvocation.MyCommand.Path
$directorypath = Split-Path $invocationPath
Set-Location ..

Remove-Item -Path .\wrk\dm_logs -Recurse -Force -ErrorAction Ignore
Remove-Item -Path .\wrk\installer_logs -Recurse -Force -ErrorAction Ignore
Remove-Item -Path .\wrk\platform_logs -Recurse -Force -ErrorAction Ignore
Remove-Item -Path .\wrk\ITAS -Recurse -Force -ErrorAction Ignore
Remove-Item -Path .\wrk\ion_repo -Recurse -Force -ErrorAction Ignore

docker-compose up -d --no-recreate --scale controller_host=0

Start-Sleep -s 5

docker-compose run controller_host /bin/bash -c /src/scripts/install_from_local_repo.sh

Set-Location $directorypath
