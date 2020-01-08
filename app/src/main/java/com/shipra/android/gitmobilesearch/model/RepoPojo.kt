package com.shipra.android.gitmobilesearch.model

data class RepoPojo(var name: String, var fullname: String,var url: String,var watcher_count: Int,
                    var commitCount: Int,var description: String,var contributors: Contributors) {
}