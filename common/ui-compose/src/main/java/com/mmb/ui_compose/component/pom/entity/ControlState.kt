package com.mmb.ui_compose.component.pom.entity

sealed class ControlState {
    object Running : ControlState()
    object Paused : ControlState()
}