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
            description = "Эйч — загадочный и холодный на вид джентльмен, но внутри него пылает жажда справедливости. Преследуя...",
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
            description = "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии...",
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
            description = "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать с него куда угодно...",
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
            description = "Уличный музыкант знакомится с музыкальным продюсером, и они вдвоём отправляются в путешествие...",
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
            description = "Tekashi69 или Сикснайн — знаменитый бруклинский рэпер с радужными волосами — прогремел...",
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
            description = "Когда упрямая пчелка Майя и ее лучший друг Вилли спасают принцессу-муравьишку, начинается сказочное...",
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
            description = "Невероятно одаренная мошенница по имени Эстелла решает сделать себе имя в мире моды.",
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
            description = "Чёрной Вдове придется вспомнить о том, что было в её жизни задолго до присоединения к команде Мстителей",
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
    )

    fun getMovies() = data
    fun getMovieById(id: Int?): MovieDto? {
        val item = data.filter { it.id == id }
        return if (item.isNotEmpty()) item[0] else null
    }
}