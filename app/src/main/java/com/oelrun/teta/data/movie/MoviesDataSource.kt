package com.oelrun.teta.data.movie

import com.oelrun.teta.data.genre.GenreDto

class MoviesDataSource {
    private val data = listOf(
        MovieDto(
            id = 101,
            title = "Гнев человеческий",
            genre = listOf(
                GenreDto(8, "Криминал"),
                GenreDto(1, "Боевик"),
                GenreDto(16, "Триллер")
            ),
            description = "Грузовики лос-анджелесской инкассаторской компании Fortico Security часто подвергаются нападениям, и во время очередного ограбления погибают оба охранника. Через некоторое время в компанию устраивается крепкий немногословный британец Патрик Хилл. Он получает от босса прозвище Эйч и, впритык к необходимому минимуму пройдя тесты по фитнесу, стрельбе и вождению, отправляется на первое задание. Вскоре и его грузовик пытаются ограбить вооруженные налётчики, но Эйч в одиночку расправляется с целой бандой и становится героем. Кажется, слава и уважение коллег его совершенно не интересуют, ведь он преследует свои цели.",
            rateScore = 3,
            ageRestriction = 18,
            releaseDate = "22.04.2021",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5JP9X5tCZ6qz7DYMabLmrQirlWh.jpg",
            cast = listOf(
                PersonDto(
                    "Джейсон Стейтем",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lldeQ91GwIVff43JBrpdbAAeYWj.jpg"
                ),
                PersonDto(
                    "Холт Маккэллани",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8NvOcP35qv5UHWEdpqAvQrKnQQz.jpg"
                ),
                PersonDto(
                    "Рокки Уильямс",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/e5GWh54fUmbupb5DKsSNU5axEx2.jpg"
                ),
                PersonDto(
                    "Джош Хартнетт",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dCfu2EN7FjISACcjilaJu7evwEc.jpg"
                )
            )
        ),
        MovieDto(
            id = 102,
            title = "Мортал Комбат",
            genre = listOf(
                GenreDto(1, "Боевик"),
                GenreDto(18, "Фэнтези"),
                GenreDto(14, "Приключения")
            ),
            description = "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии и почему император Внешнего мира Шан Цзун посылает могущественного криомансера Саб-Зиро на охоту за Коулом. Янг боится за безопасность своей семьи, и майор спецназа Джакс, обладатель такой же отметки в виде дракона, как и у Коула, советует ему отправиться на поиски Сони Блейд. Вскоре Коул, Соня и наёмник Кано оказываются в храме Лорда Рейдена, Старшего Бога и защитника Земного царства, который дает убежище тем, кто носит метку. Здесь прибывшие тренируются с опытными воинами Лю Каном и Кун Лао, готовясь противостоять врагам из Внешнего мира, а для этого им нужно раскрыть в себе аркану — могущественную силу души.",
            rateScore = 5,
            ageRestriction = 18,
            releaseDate = "07.04.2021",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pMIixvHwsD5RZxbvgsDSNkpKy0R.jpg",
            cast = listOf(
                PersonDto(
                    "Льюис Тан",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lkW8gh20BuwzHecXqYH1eRVuWpb.jpg"
                ),
                PersonDto(
                    "Джессика Макнэми",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aAfaMEEqD8syHv5bLi5B3sccrM2.jpg"
                ),
                PersonDto(
                    "Джош Лоусон",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/Am9vM77uZd9bGODugwmWtOfzx6E.jpg"
                )
            )
        ),
        MovieDto(
            id = 103,
            title = "Упс... Приплыли!",
            genre = listOf(
                GenreDto(12, "Мультфильм"),
                GenreDto(14, "Приключения"),
                GenreDto(7, "Комедия"),
                GenreDto(15, "Семейный")
            ),
            description = "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать с него куда угодно. Нервы на пределе. Хищники готовы забыть про запреты и заглядываются на травоядных. Единственное спасение — найти райский остров. Там простор и полно еды. Но даже если он совсем близко, будут ли рады местные такому количеству гостей?",
            rateScore = 5,
            ageRestriction = 6,
            releaseDate = "29.04.2021",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/546RNYy9Wi5wgboQ7EtD6i0DY5D.jpg",
            cast = listOf(
                PersonDto("Макс Каролан", null),
                PersonDto("Дермот Магеннис", null),
                PersonDto(
                    "Тара Флинн",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/17gBs4aux2NcnMvf3DK5UKUFttn.jpg"
                )
            )
        ),
        MovieDto(
            id = 104,
            title = "The Box",
            genre = listOf(
                GenreDto(11, "Музыка"),
                GenreDto(5, "Драма")
            ),
            description = "Уличный музыкант знакомится с музыкальным продюсером, и они вдвоём отправляются в путешествие, которое перевернёт их жизни.",
            rateScore = 4,
            ageRestriction = 12,
            releaseDate = "13.05.2021",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fq3DSw74fAodrbLiSv0BW1Ya4Ae.jpg",
            cast = listOf(
                PersonDto(
                    "Пак Чханёль",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qqvTuk4CTvS1IE47CUozhcHVahz.jpg"
                ),
                PersonDto(
                    "Чо Даль Хван",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jpEPPXmVC3EDMqrDQDYyXEMYlah.jpg"
                ),
                PersonDto(
                    "Гаеко",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fDO7vJVRkZOOY1GtQMJzf4N136q.jpg"
                )
            )
        ),
        MovieDto(
            id = 105,
            title = "Сага о Дэнни Эрнандесе",
            genre = listOf(
                GenreDto(11, "Музыка"),
                GenreDto(4, "Документальный")
            ),
            description = "Tekashi69 или Сикснайн — знаменитый бруклинский рэпер с радужными волосами — прогремел синглом «Gummo», коллабом с Ники Минаж, а также многочисленными преступлениями. В документальном расследовании о жизни и творчестве рэпера разворачивается настоящая гангстерская история, в которой количество обвинений растет пропорционально интернет-популярности, а репутация секс-наркомана получает свое подтверждение не только в скандальных видео музыканта. Похоже, это последний антигерой нашего времени, которого не коснулась культура отмены: у Tekashi69 24 млн. подписчиков в Instagram, миллиарды просмотров на Youtube и несколько судимостей.",
            rateScore = 2,
            ageRestriction = 18,
            releaseDate = "29.04.2021",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5xXGQLVtTAExHY92DHD9ewGmKxf.jpg",
            cast = listOf(
                PersonDto(
                    "Даниэль Эрнандес",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xAlvyeC9zLbygGMxmmyTHymwuZP.jpg"
                )
            )
        ),
        MovieDto(
            id = 106,
            title = "Пчелка Майя",
            genre = listOf(
                GenreDto(14, "Приключения"),
                GenreDto(12, "Мультфильм"),
                GenreDto(15, "Семейный")
            ),
            description = "Когда упрямая пчелка Майя и ее лучший друг Вилли спасают принцессу-муравьишку, начинается сказочное и опасное приключение, которое приведет их в необычные новые миры и проверит их дружбу на прочность.",
            rateScore = 4,
            ageRestriction = 0,
            releaseDate = "06.05.2021",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xltjMeLlxywym14NEizl0metO10.jpg",
            cast = listOf(
                PersonDto(
                    "Бенсон Джек Энтони",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aVfEldX1ksEMrx45yNBAf9MAIDZ.jpg"
                ),
                PersonDto(
                    "Фрэнсис Берри",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qCp0psD5qzguABpRxWmMuC04kcl.jpg"
                ),
                PersonDto(
                    "Кристиан Харисиу",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8OpoYvO1QqBYRAp1LxxUIiRdQG0.jpg"
                )
            )
        ),
        MovieDto(
            id = 107,
            title = "Круэлла",
            genre = listOf(
                GenreDto(7, "Комедия"),
                GenreDto(8, "Криминал")
            ),
            description = "Лондон 70-х годов охвачен зарождающейся культурой панк-рока. Невероятно одаренная мошенница по имени Эстелла решает сделать себе имя в мире моды. Её лучшие друзья — парочка юных карманников, которые ценят страсть Эстеллы к приключениям и надеются вместе с ней отвоевать себе место под солнцем на улицах британской столицы. В один прекрасный день модное чутье Эстеллы привлекает внимание шикарной и пугающе высокомерной баронессы фон Хельман.",
            rateScore = 4,
            ageRestriction = 12,
            releaseDate = "03.06.2021",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUfyYGP9Xf6cHF9y44JXJV3NxZM.jpg",
            cast = listOf(
                PersonDto(
                    "Эмма Стоун",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2hwXbPW2ffnXUe1Um0WXHG0cTwb.jpg"
                ),
                PersonDto(
                    "Эмма Томпсон",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xr8Ki3CIqweWWqS5q0kUYdiK6oQ.jpg"
                ),
                PersonDto(
                    "Джоэл Фрай",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4nEKEWJpaTHncCTv6zeP98V0qGI.jpg"
                ),
                PersonDto(
                    "Пол Уолтер Хаузер",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hV2oiKF2xlDMXtuq3Si1TA4b6DA.jpg"
                ),
            )
        ),
        MovieDto(
            id = 108,
            title = "Чёрная вдова",
            genre = listOf(
                GenreDto(1, "Боевик"),
                GenreDto(14, "Приключения"),
                GenreDto(16, "Триллер"),
                GenreDto(13, "Научная фантастика")
            ),
            description = "Наташе Романофф предстоит лицом к лицу встретиться со своим прошлым. Чёрной Вдове придется вспомнить о том, что было в её жизни задолго до присоединения к команде Мстителей, и узнать об опасном заговоре, в который оказываются втянуты её старые знакомые — Елена, Алексей (известный как Красный Страж) и Мелина.",
            rateScore = 3,
            ageRestriction = 16,
            releaseDate = "08.07.2021",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mbtN6V6y5kdawvAkzqN4ohi576a.jpg",
            cast = listOf(
                PersonDto(
                    "Скарлетт Йоханссон",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6NsMbJXRlDZuDzatN2akFdGuTvx.jpg"
                ),
                PersonDto(
                    "Флоренс Пью",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/75PvULemW8BvheSKtPMoBBsvPLh.jpg"
                ),
                PersonDto(
                    "Рэйчел Уайс",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3QbFXeiUzXUVUrJ7fdiCn7A7ReW.jpg"
                )
            )
        ),
        MovieDto(
            id = 111,
            title = "Ассасин: Битва миров",
            genre = listOf(
                GenreDto(5, "Драма"),
                GenreDto(1, "Боевик"),
                GenreDto(18, "Фэнтези")
            ),
            description = "В неком фэнтезийном мире правит жестокий Бог, которому нужны лишь смерть и разрушения. Потеряв сестру, но обретя волшебный доспех, молодой воин Кунвэнь отправляется в путешествие, чтобы разыскать и убить Бога. В это время в нашем мире мужчина Гуань Нин уже шесть лет находится на грани отчаяния, разыскивая пропавшую дочку. Он выходит на торговцев детьми, но полиция ошибочно принимает его за преступника и арестовывает. Сбежать Нину помогает ассистентка влиятельного бизнесмена и предлагает ему сделку. Один популярный писатель в данный момент пишет роман в жанре фэнтези, который каким-то образом вредит здоровью её босса, а Нин в обмен на информацию о местонахождении дочери должен этого писателя убить.",
            rateScore = 3,
            ageRestriction = 18,
            releaseDate = "27.05.2021",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9o0g9kRHD8SSO73ExDekcx9SPT9.jpg",
            cast = listOf(
                PersonDto(
                    "Цзяинь Лэй",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vlYri4zkSMLRXzw7icuPsm5Fwxb.jpg"
                ),
                PersonDto(
                    "Ян Ми",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6RezRxlQBsYmlotybR3q7riSrvV.jpg"
                ),
                PersonDto(
                    "Донг Цзыцзянь",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kiD5fhNmyIMWKam5xRP3rrasnBE.jpg"
                )
            )
        ),
        MovieDto(
            id = 112,
            title = "Лука",
            genre = listOf(
                GenreDto(12, "Мультфильм"),
                GenreDto(7, "Комедия"),
                GenreDto(15, "Семейный")
            ),
            description = "Свои незабываемые каникулы, в которых есть место и домашней пасте, и мороженному, и бесконечным поездкам на мопеде, мальчик по имени Лука проводит в красивом приморском городке, расположенном на итальянской Ривьере. Ни одно приключение Луки не обходится без участия его нового лучшего друга, и беззаботность отдыха омрачает только лишь тот факт, что на самом деле в облике мальчика скрывается морской монстр из глубин лагуны, на берегу которой стоит городок.",
            rateScore = 4,
            ageRestriction = 6,
            releaseDate = "17.06.2021",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/99wTOb5v97nVPnuCR9E9JtPeVzU.jpg",
            cast = listOf(
                PersonDto(
                    "Джейкоб Трамбле",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oNLhzkZXNw1RNihne9P5q57cRcd.jpg"
                ),
                PersonDto(
                    "Джек Дилан Грейзер",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/cZdGLa78UP7VzMgNbDRnoaSkZm1.jpg"
                ),
                PersonDto(
                    "Эмма Берман",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/r3QkZtW6Iaq56ziZqvPXAQLOcTr.jpg"
                )
            )
        ),
        MovieDto(
            id = 113,
            title = "Зов предков",
            genre = listOf(
                GenreDto(14, "Приключения"),
                GenreDto(15, "Семейный"),
                GenreDto(5, "Драма"),
            ),
            description = "История Бэка, дружелюбного пса, чья размеренная домашняя жизнь перевернулась с ног на голову во времена золотой лихорадки в 1880-х, когда его вырвали из дома в Калифорнии и перевезли в дикую и холодную Аляску. Будучи новичком в упряжке почтовой службы, а впоследствии лидером, Бак попадает в невероятное приключение, находит свое место в мире и становится хозяином своей жизни.",
            rateScore = 4,
            ageRestriction = 6,
            releaseDate = "20.02.2020",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yzF53Gw27HiVNO9ykg8LOxt5q6m.jpg",
            cast = listOf(
                PersonDto(
                    "Харрисон Форд",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5M7oN3sznp99hWYQ9sX0xheswWX.jpg"),
                PersonDto(
                    "Дэн Стивенс",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6ioq5zfiwSZbPRWY8fVUO8bWRBC.jpg"),
                PersonDto(
                    "Колин Вуделл",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/niBr8ODh8Lv9uSM6yNS8tWonf0K.jpg"
                )
            )
        ),
        MovieDto(
            id = 114,
            title = "Легенда о волках",
            genre = listOf(
                GenreDto(12, "Мультфильм"),
                GenreDto(15, "Семейный"),
                GenreDto(14, "Приключения")
            ),
            description = "Юная охотница и ее отец отправляются в Ирландию, чтобы истребить последнюю стаю волков. Но все меняется после встречи с девушкой из загадочного племени, которое, по слухам, ночью превращается в волков.",
            rateScore = 5,
            ageRestriction = 12,
            releaseDate = "26.10.2020",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ofhlSAHtcAUerSb4vYHN1D7tfoQ.jpg",
            cast = listOf(
                PersonDto(
                    "Хонор Книфси",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgk3qwXCUjYReOwrGCLWm8qCbBx.jpg"
                ),
                PersonDto("Эва Уиттакер", null),
                PersonDto(
                    "Шон Бин",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kTjiABk3TJ3yI0Cto5RsvyT6V3o.jpg"
                )
            )
        ),
        MovieDto(
            id = 115,
            title = "Сквозь слёзы я притворяюсь кошкой",
            genre = listOf(
                GenreDto(12, "Мультфильм"),
                GenreDto(5, "Драма"),
                GenreDto(18, "Фэнтези")
            ),
            description = "На фестивале фейерверков обычная японская старшеклассница Миё, распереживавшись из-за развода родителей и несправедливости мира, только в сердцах пожелала этому миру исчезнуть, как тут же встретила загадочного продавца. Незнакомец подарил девушке волшебную маску, с помощью которой Миё теперь может превращаться в белую кошечку. В таком виде она проводит время с одноклассником Кэнто, ведь он, несмотря на все её знаки внимания в школе, упорно игнорирует девушку. Но тот странный тип с фестиваля преследует Миё и пытается убедить её остаться в кошачьем обличье навсегда.",
            rateScore = 4,
            ageRestriction = 6,
            releaseDate = "18.06.2020",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iNeeVDibeeJaBYT2NtTuKk8pUrI.jpg",
            cast = listOf(
                PersonDto(
                    "Мирай Шида",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xAlvyeC9zLbygGMxmmyTHymwuZP.jpg"
                ),
                PersonDto(
                    "Нацуки Ханаэ",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/A1lGrpBEdAUxZA7RoAw4Zr4ved3.jpg"
                )
            )
        ),
        MovieDto(
            id = 116,
            title = "Мулан",
            genre = listOf(
                GenreDto(14, "Приключения"),
                GenreDto(18, "Фэнтези")
            ),
            description = "История о бесстрашной молодой девушке, которая выдаёт себя за мужчину, чтобы вступить в ряды армии, противостоящей Северным захватчикам, надвигающимся на Китай. Старшая дочь храброго воина Хуа, Мулан — энергичная и решительная девушка. Когда Император издаёт указ о том, что один мужчина из каждой семьи должен вступить в ряды Имперской армии, Мулан занимает место своего больного отца, еще не зная, что ей предстоит прославиться как одному из величайших воинов в истории Китая.",
            rateScore = 3,
            ageRestriction = 12,
            releaseDate = "04.09.2020",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/uxPauLtzjl9oWZtS1Bwp9mCDRFh.jpg",
            cast = listOf(
                PersonDto(
                    "Лю Ифэй",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/cL6JccAYqiZQEAIEFObEUC9LTt7.jpg"
                ),
                PersonDto(
                    "Джет Ли",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7d4hgOzFW7CWPcE92eTSEFRSObC.jpg"
                ),
                PersonDto(
                    "Ци Ма",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/x4fz0LCIiBNGdil3nBYO22W7QJ0.jpg"
                )
            )
        ),
        MovieDto(
            id = 117,
            title = "Новости со всех концов света",
            genre = listOf(
                GenreDto(1, "Боевик"),
                GenreDto(14, "Приключения"),
                GenreDto(5, "Драма")
            ),
            description = "Ветеран Гражданской войны в США капитан Джефферсон Кайл Кидд зарабатывает на жизнь, путешествуя по стране и зачитывая неграмотным местным последние новости из газет. Однажды его нанимают отвезти к родственникам 10-летнюю девочку-сироту, которая долгое время жила в индийском племени Кайова. Путешествуя из городка в городок, вдовец Кидд, давно не общавшийся с собственной дочерью, начинает испытывать к юной попутчице отеческие чувства. Вместе они должны преодолеть суровую природу американского Запада и избежать бандитов, намеренных похитить девочку.",
            rateScore = 3,
            ageRestriction = 12,
            releaseDate = "07.01.2021",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/q7h2jqHMug8APfMef0g0co9qTGQ.jpg",
            cast = listOf(
                PersonDto(
                    "Том Хенкс",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xndWFsBlClOJFRdhSt4NBwiPq2o.jpg"
                ),
                PersonDto(
                    "Хелена Ценгель",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/bUzX1rQkwgLzFL9JiklxjL5B9hn.jpg"
                )
            )
        ),
        MovieDto(
            id = 118,
            title = "Маленькие женщины",
            genre = listOf(
                GenreDto(5, "Драма"),
                GenreDto(9, "Мелодрама")
            ),
            description = "Фильм рассказывает о взрослении четырех непохожих друг на друга сестер. Действие разворачивается во времена Гражданской войны в США, но проблемы, с которыми сталкиваются девушки, актуальны как никогда: первая любовь, горькое разочарование, томительная разлука и непростые поиски себя и своего места в жизни.",
            rateScore = 4,
            ageRestriction = 12,
            releaseDate = "30.01.2020",
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/n0x5mnqovrodeZ5NTiQAFDwKqrm.jpg",
            cast = listOf(
                PersonDto(
                    "Сирша Ронан",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9buDPdqKN9vbnQLLkHEinDtMrCG.jpg"
                ),
                PersonDto(
                    "Флоренс Пью",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/75PvULemW8BvheSKtPMoBBsvPLh.jpg"
                ),
                PersonDto(
                    "Эмма Уотсон",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dMbd2Rx9ZD5Gl9VXAkGcIKGrAxJ.jpg"
                )
            )
        )
    )

    //"refreshed"
    fun getMovies(refresh: Boolean): List<MovieDto> {
        Thread.sleep(2000)
        return if(refresh) data.takeLast(8) else data.take(8)
    }
    fun getMovieById(id: Int?): MovieDto? {
        val item = data.filter { it.id == id }
        return if (item.isNotEmpty()) item[0] else null
    }
}