# language: ru
#@allure.label.layer:ui
#@allure.label.owner:kimds
Функция: Авторизация в Otus

  @main @regress @critical @Login
  Структура сценария: Авторизация в Otus
    Когда Открыть страницу StartPage в браузере
    И Отображается текст Авторские онлайн‑курсы на странице StartPage
    И Нажата кнопка Вход и регистрация на странице StartPage
    И Заполнено поле Электронная почта на странице LoginPage значением <Логин>
    И Заполнено поле Пароль на странице LoginPage значением <Пароль>
    И Нажата кнопка Войти на странице LoginPage
    Примеры:
      | Логин                       | Пароль        |
      | diman_the_red_devil@mail.ru | JAKARTA12345- |