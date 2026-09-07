package com.dv.apps.komic

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform