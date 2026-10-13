@echo off
setlocal
title Slime Frontier - VenomBlade JAR Builder
cd /d "%~dp0"

echo ============================================
echo   Slime Frontier - VenomBlade JAR Builder
echo   Minecraft 1.21.1 / NeoForge / Java 21
echo ============================================
echo.

where java >nul 2>nul
if errorlevel 1 (
  echo [HATA] Java bulunamadi. JDK 21 kurulu olmali.
  echo Java 21 kurduktan sonra bu dosyayi yeniden calistir.
  pause
  exit /b 1
)

java -version 2>&1 | findstr /C:"21." >nul
if errorlevel 1 (
  echo [UYARI] Varsayilan Java surumu 21 gibi gorunmuyor.
  echo Minecraft 1.21.1 NeoForge icin JDK 21 onerilir.
  echo.
)

where gradle >nul 2>nul
if errorlevel 1 (
  echo [HATA] Gradle komutu bulunamadi.
  echo Bu proje icin Gradle veya Gradle Wrapper gerekir.
  echo Gradle kuruluysa PATH ayarini kontrol et.
  pause
  exit /b 1
)

echo [1/3] Gradle derlemesi baslatiliyor...
call gradle --stop >nul 2>nul
call gradle clean build --no-daemon
if errorlevel 1 (
  echo.
  echo [HATA] Derleme basarisiz oldu. Yukaridaki hata mesajlarini kontrol et.
  echo Gradle onbellegiyle ilgili hata varsa bilgisayari yeniden baslatip tekrar dene.
  pause
  exit /b 1
)

if not exist "build\libs" (
  echo [HATA] build\libs klasoru bulunamadi.
  pause
  exit /b 1
)

if not exist "dist" mkdir "dist"
for %%F in ("build\libs\*.jar") do (
  if exist "%%~fF" (
    echo [2/3] JAR kopyalaniyor: %%~nxF
    copy /Y "%%~fF" "dist\%%~nxF" >nul
  )
)

echo.
echo [3/3] Islem tamamlandi.
echo JAR dosyalarin proje klasorundeki dist klasorunde.
echo Minecraft 1.21.1 NeoForge mods klasorune kopyalayabilirsin.
echo.
explorer "%~dp0dist"
pause
