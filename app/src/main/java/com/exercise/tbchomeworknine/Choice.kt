package com.exercise.tbchomeworknine

data class Choice(
    val id: Int,
    val image: Int,
    val title: String,
    val type: ItemType
)

enum class ItemType {
    NORMAL,
    DIFFERENT
}

data class Output(
    val id: Int,
    val image: Int,
    val description: String,
    val price: String,
    val categoryType: TypeOfItem
)

enum class TypeOfItem{
    ALL,
    Party,
    Camping,
    Catergory1,
    Catergory2,
    Catergory3
}