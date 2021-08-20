package com.oelrun.teta.data.source

import com.oelrun.teta.database.entities.Genre

class GenresDataSource {
    private val dataGenres = listOf(
        Genre(1, "Боевик"),
        Genre(2, "Вестерн"),
        Genre(3, "Военный"),
        Genre(4, "Документальный"),
        Genre(5, "Драма"),
        Genre(6, "Исторический"),
        Genre(7, "Комедия"),
        Genre(8, "Криминал"),
        Genre(9, "Мелодрама"),
        Genre(10, "Мистика"),
        Genre(11, "Музыка"),
        Genre(12, "Мультфильм"),
        Genre(13, "Научная фантастика"),
        Genre(14, "Приключения"),
        Genre(15, "Семейный"),
        Genre(16, "Триллер"),
        Genre(17, "Ужасы"),
        Genre(18, "Фэнтези")
    )

    fun getGenres(): List<Genre> {
        Thread.sleep(1000)
        return dataGenres
    }
}