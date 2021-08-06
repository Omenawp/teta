package com.oelrun.teta.data.genre

class GenresDataSource {
    private val dataGenres = listOf(
        GenreDto(1, "Боевик"),
        GenreDto(2, "Вестерн"),
        GenreDto(3, "Военный"),
        GenreDto(4, "Документальный"),
        GenreDto(5, "Драма"),
        GenreDto(6, "Исторический"),
        GenreDto(7, "Комедия"),
        GenreDto(8, "Криминал"),
        GenreDto(9, "Мелодрама"),
        GenreDto(10, "Мистика"),
        GenreDto(11, "Музыка"),
        GenreDto(12, "Мультфильм"),
        GenreDto(13, "Научная фантастика"),
        GenreDto(14, "Приключения"),
        GenreDto(15, "Семейный"),
        GenreDto(16, "Триллер"),
        GenreDto(17, "Ужасы"),
        GenreDto(18, "Фэнтези"))

    fun getGenres(): List<GenreDto> {
        Thread.sleep(1000)
        return dataGenres
    }
}