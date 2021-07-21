package com.oelrun.teta.data.genre

class GenresDataSource {
    fun getGenres() = listOf(
        GenreDto(1, "Боевик", false),
        GenreDto(2, "Вестерн", false),
        GenreDto(3, "Военный",false),
        GenreDto(4, "Документальный",false),
        GenreDto(5, "Драма",false),
        GenreDto(6, "Исторический",false),
        GenreDto(7, "Комедия",false),
        GenreDto(8, "Криминал",false),
        GenreDto(9, "Мелодрама",false),
        GenreDto(10, "Мистика",false),
        GenreDto(11, "Музыкальный",false),
        GenreDto(12, "Мультфильм",false),
        GenreDto(13, "Научная фантастика",false),
        GenreDto(14, "Приключения",false),
        GenreDto(15, "Семейный",false),
        GenreDto(16, "Триллер",false),
        GenreDto(17, "Ужасы",false),
        GenreDto(18, "Фантастика",false))
}