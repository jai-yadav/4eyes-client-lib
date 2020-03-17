
$invocationPath = $MyInvocation.MyCommand.Path
$directorypath = Split-Path $invocationPath
Set-Location ..

docker-compose stop
docker-compose rm --force

Set-Location $directorypath
