package com.example.miniscout

class Timeline(team: String) {
    var timeline: MutableList<TimelineEntry> = mutableListOf()

    data class TimelineEntry(
        private var actionType: ActionType,
        private var time: Long,
        private var concurrentValue: Int
    ) {
        fun getActionType(): ActionType {
            return actionType
        }

        fun getTime(): Long {
            return time
        }

        fun getConcurrentValue(): Int {
            return concurrentValue
        }
    }

    enum class ActionType {
        Placement,
        Incap
    }

    fun getTeam(team: String): String {
        return team
    }

    fun getTimelineEntry(index: Int): TimelineEntry {
        return timeline[index]
    }
    fun add(actionType: ActionType, time: Long, concurrentValue: Int) {
        timeline.add(TimelineEntry(actionType, time, concurrentValue))
    }

}
