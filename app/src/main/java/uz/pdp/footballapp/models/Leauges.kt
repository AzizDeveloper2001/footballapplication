package uz.pdp.footballapp.models

data class Leauges(
	val data: List<DataItem>,
	val status: Boolean
)

data class DataItem(
	val name: String,
	val id: String,
	val abbr: String,
	val logos: Logos,
	val slug: String
)

data class Logos(
	val light: String,
	val dark: String
)

