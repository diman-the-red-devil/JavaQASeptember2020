# language: ru
Функция: Главная -> Курсы

  @main @regress
  Структура сценария: Просмотр курсов "Программирование"
    Когда Открыть страницу StartPage в браузере
    И Отображается текст Авторские онлайн‑курсы на странице StartPage
    И Нажата ссылка Программирование на странице StartPage
    И Отображается текст Программирование на странице CoursesProgrammingPage
    И Проверить: В карточке курса по Программирование отображается текст <Текст>
    Примеры:
      | Текст                               |
      | Highload Architect                  |
      | Software Architect                  |
      | Java Developer. Professional        |
      | Unity Game Developer. Professional  |
      | Kotlin Backend Developer            |

  @main @regress
  Структура сценария: Просмотр курсов "Инфраструктура"
    Когда Открыть страницу StartPage в браузере
    И Отображается текст Авторские онлайн‑курсы на странице StartPage
    И Нажата ссылка Инфраструктура на странице StartPage
    И Отображается текст Инфраструктура на странице CoursesInfrastructurePage
    И Проверить: В карточке курса по Инфраструктура отображается текст <Текст>
    Примеры:
      | Текст                               |
      | Highload Architect                  |
      | Software Architect                  |
      | Administrator Linux. Advanced       |
      | Administrator Linux. Professional   |
      | Разработчик программных роботов (RPA) на базе UiPath и PIX            |

  @main @regress
  Структура сценария: Просмотр курсов "Информационная безопасность"
    Когда Открыть страницу StartPage в браузере
    И Отображается текст Авторские онлайн‑курсы на странице StartPage
    И Нажата ссылка Информационная безопасность на странице StartPage
    И Отображается текст Информационная безопасность на странице CoursesSecurityPage
    И Проверить: В карточке курса по Информационная безопасность отображается текст <Текст>
    Примеры:
      | Текст                                           |
      | Пентест. Практика тестирования на проникновение |
      | Reverse-Engineering. Basic                      |
      | Безопасность Linux                              |
      | Безопасность веб-приложений                     |
      | Внедрение и работа в DevSecOps                  |

  @main @regress
  Структура сценария: Просмотр курсов "Data Science"
    Когда Открыть страницу StartPage в браузере
    И Отображается текст Авторские онлайн‑курсы на странице StartPage
    И Нажата ссылка Data Science на странице StartPage
    И Отображается текст Data Science на странице CoursesDataSciencePage
    И Проверить: В карточке курса по Data Science отображается текст <Текст>
    Примеры:
      | Текст                           |
      | Data Engineer                   |
      | Экосистема Hadoop, Spark, Hive  |
      | Математика для Data Science     |
      | Machine Learning. Advanced      |
      | Нереляционные базы данных       |