package uz.pdp.footballapp.models

data class Response(
	val data: Data?=null,
	val status: Boolean
)

data class Data(
	val name: String?=null,
	val season: Int?=null,
	val abbreviation: String?=null,
	val seasonDisplay: String?=null,
	val standings: List<StandingsItem>
)

data class StatsItem(
	val shortDisplayName: String?=null,
	val summary: String?=null,
	val displayValue: String?=null,
	val displayName: String?=null,
	val name: String?=null,
	val description: String?=null,
	val id: String?=null,
	val abbreviation: String?=null,
	val type: String?=null,
	val value: Int
)

data class StandingsItem(
	val note: Note?=null,
	val stats: List<StatsItem>?=null,
	val team: Team
)

data class Note(
	val color: String?=null,
	val description: String?=null,
	val rank: Int
)

data class Team(
	val shortDisplayName: String?=null,
	val uid: String?=null,
	val displayName: String?=null,
	val name: String?=null,
	val location: String?=null,
	val id: String?=null,
	val abbreviation: String??=null,
	val isActive: Boolean?=null,
	val logos: List<LogosItem>?
)

data class LogosItem(
	val lastUpdated: String?=null,
	val width: Int?=null,
	val alt: String?=null,
	val rel: List<String>?=null,
	val href: String?=null,
	val height: Int
)

