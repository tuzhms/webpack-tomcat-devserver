# Сервер разработки tomcat + webpack
Пример сервера разработки, объединяющего возможности одновременной разработки jsp развёрнутого на tomcat и js/ts собираемого через webpack

## Запуск
```bash
./gradlew clean classes
./gradlew start
cd src/frontend
npm run start
```

[Смотреть тут](http://localhost:8080/)