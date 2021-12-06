package com.solucioneshr.soft.testrappi.data

data class DataMovie(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)