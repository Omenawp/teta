package com.oelrun.teta.data.source

import com.oelrun.teta.data.dto.CastDto
import com.oelrun.teta.database.entities.Actor

class CastDataSource {
    private val data = listOf(
        CastDto(
            movieId = 101,
            cast = listOf(
                Actor(
                    100,
                    "Джейсон Стейтем",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lldeQ91GwIVff43JBrpdbAAeYWj.jpg"
                ),
                Actor(
                    101,
                    "Холт Маккэллани",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8NvOcP35qv5UHWEdpqAvQrKnQQz.jpg"
                ),
                Actor(
                    102,
                    "Рокки Уильямс",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/e5GWh54fUmbupb5DKsSNU5axEx2.jpg"
                ),
                Actor(
                    103,
                    "Джош Хартнетт",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dCfu2EN7FjISACcjilaJu7evwEc.jpg"
                )
            )
        ),
        CastDto(
            movieId = 102,
            cast = listOf(
                Actor(
                    104,
                    "Льюис Тан",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lkW8gh20BuwzHecXqYH1eRVuWpb.jpg"
                ),
                Actor(
                    105,
                    "Джессика Макнэми",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aAfaMEEqD8syHv5bLi5B3sccrM2.jpg"
                ),
                Actor(
                    106,
                    "Джош Лоусон",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/Am9vM77uZd9bGODugwmWtOfzx6E.jpg"
                )
            )
        ),
        CastDto(
            movieId = 103,
            cast = listOf(
                Actor(107, "Макс Каролан", null),
                Actor(108,"Дермот Магеннис", null),
                Actor(
                    109,
                    "Тара Флинн",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/17gBs4aux2NcnMvf3DK5UKUFttn.jpg"
                )
            )
        ),
        CastDto(
            movieId = 104,
            cast = listOf(
                Actor(
                    110,
                    "Пак Чханёль",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qqvTuk4CTvS1IE47CUozhcHVahz.jpg"
                ),
                Actor(
                    111,
                    "Чо Даль Хван",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jpEPPXmVC3EDMqrDQDYyXEMYlah.jpg"
                ),
                Actor(
                    112,
                    "Гаеко",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fDO7vJVRkZOOY1GtQMJzf4N136q.jpg"
                )
            )
        ),
        CastDto(
            movieId = 105,
            cast = listOf(
                Actor(
                    113,
                    "Даниэль Эрнандес",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xAlvyeC9zLbygGMxmmyTHymwuZP.jpg"
                )
            )
        ),
        CastDto(
            movieId = 106,
            cast = listOf(
                Actor(
                    114,
                    "Бенсон Джек Энтони",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aVfEldX1ksEMrx45yNBAf9MAIDZ.jpg"
                ),
                Actor(
                    115,
                    "Фрэнсис Берри",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qCp0psD5qzguABpRxWmMuC04kcl.jpg"
                ),
                Actor(
                    116,
                    "Кристиан Харисиу",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8OpoYvO1QqBYRAp1LxxUIiRdQG0.jpg"
                )
            )
        ),
        CastDto(
            movieId = 107,
            cast = listOf(
                Actor(
                    117,
                    "Эмма Стоун",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2hwXbPW2ffnXUe1Um0WXHG0cTwb.jpg"
                ),
                Actor(
                    118,
                    "Эмма Томпсон",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xr8Ki3CIqweWWqS5q0kUYdiK6oQ.jpg"
                ),
                Actor(
                    119,
                    "Джоэл Фрай",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4nEKEWJpaTHncCTv6zeP98V0qGI.jpg"
                ),
                Actor(
                    120,
                    "Пол Уолтер Хаузер",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hV2oiKF2xlDMXtuq3Si1TA4b6DA.jpg"
                ),
            )
        ),
        CastDto(
            movieId = 108,
            cast = listOf(
                Actor(
                    121,
                    "Скарлетт Йоханссон",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6NsMbJXRlDZuDzatN2akFdGuTvx.jpg"
                ),
                Actor(
                    122,
                    "Флоренс Пью",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/75PvULemW8BvheSKtPMoBBsvPLh.jpg"
                ),
                Actor(
                    123,
                    "Рэйчел Уайс",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3QbFXeiUzXUVUrJ7fdiCn7A7ReW.jpg"
                )
            )
        ),
        CastDto(
            movieId = 111,
            cast = listOf(
                Actor(
                    124,
                    "Цзяинь Лэй",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vlYri4zkSMLRXzw7icuPsm5Fwxb.jpg"
                ),
                Actor(
                    125,
                    "Ян Ми",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6RezRxlQBsYmlotybR3q7riSrvV.jpg"
                ),
                Actor(
                    126,
                    "Донг Цзыцзянь",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kiD5fhNmyIMWKam5xRP3rrasnBE.jpg"
                )
            )
        ),
        CastDto(
            movieId = 112,
            cast = listOf(
                Actor(
                    127,
                    "Джейкоб Трамбле",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oNLhzkZXNw1RNihne9P5q57cRcd.jpg"
                ),
                Actor(
                    128,
                    "Джек Дилан Грейзер",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/cZdGLa78UP7VzMgNbDRnoaSkZm1.jpg"
                ),
                Actor(
                    129,
                    "Эмма Берман",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/r3QkZtW6Iaq56ziZqvPXAQLOcTr.jpg"
                )
            )
        ),
        CastDto(
            movieId = 113,
            cast = listOf(
                Actor(
                    130,
                    "Харрисон Форд",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5M7oN3sznp99hWYQ9sX0xheswWX.jpg"),
                Actor(
                    131,
                    "Дэн Стивенс",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6ioq5zfiwSZbPRWY8fVUO8bWRBC.jpg"),
                Actor(
                    132,
                    "Колин Вуделл",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/niBr8ODh8Lv9uSM6yNS8tWonf0K.jpg"
                )
            )
        ),
        CastDto(
            movieId = 114,
            cast = listOf(
                Actor(
                    133,
                    "Хонор Книфси",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgk3qwXCUjYReOwrGCLWm8qCbBx.jpg"
                ),
                Actor(134,"Эва Уиттакер", null),
                Actor(
                    135,
                    "Шон Бин",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kTjiABk3TJ3yI0Cto5RsvyT6V3o.jpg"
                )
            )
        ),
        CastDto(
            movieId = 115,
            cast = listOf(
                Actor(
                    136,
                    "Мирай Шида",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xAlvyeC9zLbygGMxmmyTHymwuZP.jpg"
                ),
                Actor(
                    137,
                    "Нацуки Ханаэ",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/A1lGrpBEdAUxZA7RoAw4Zr4ved3.jpg"
                )
            )
        ),
        CastDto(
            movieId = 116,
            cast = listOf(
                Actor(
                    138,
                    "Лю Ифэй",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/cL6JccAYqiZQEAIEFObEUC9LTt7.jpg"
                ),
                Actor(
                    139,
                    "Джет Ли",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7d4hgOzFW7CWPcE92eTSEFRSObC.jpg"
                ),
                Actor(
                    140,
                    "Ци Ма",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/x4fz0LCIiBNGdil3nBYO22W7QJ0.jpg"
                )
            )
        ),
        CastDto(
            movieId = 117,
            cast = listOf(
                Actor(
                    141,
                    "Том Хенкс",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xndWFsBlClOJFRdhSt4NBwiPq2o.jpg"
                ),
                Actor(
                    142,
                    "Хелена Ценгель",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/bUzX1rQkwgLzFL9JiklxjL5B9hn.jpg"
                )
            )
        ),
        CastDto(
            movieId = 118,
            cast = listOf(
                Actor(
                    143,
                    "Сирша Ронан",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9buDPdqKN9vbnQLLkHEinDtMrCG.jpg"
                ),
                Actor(
                    144,
                    "Флоренс Пью",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/75PvULemW8BvheSKtPMoBBsvPLh.jpg"
                ),
                Actor(
                    145,
                    "Эмма Уотсон",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dMbd2Rx9ZD5Gl9VXAkGcIKGrAxJ.jpg"
                )
            )
        )
    )

    fun getCastByMovieId(id: Int): CastDto {
        val item = data.filter { it.movieId == id }
        return item[0]
    }
}