package org.danielcastelao.dam

// nivel en el que se encuentra el ascensor
var nivel_actual: Int = 0
// estado actual del ascensor
var estado_acual: Estados = Estados.Idle

// Variables de botones
const val nivel_0 = 0
const val nivel_1 = 1

// Comprueba el estado y devuelve el nivel actual.
fun gestor(): Int {
    when (estado_acual) {
        is Estados.Idle -> {
            if (nivel_actual != 0 && nivel_actual != 1) {
                nivel_actual = 0
            }
        }
        is Estados.MovingUP -> {
            nivel_actual = 2
        }
        is Estados.MovingDOWN -> {
            nivel_actual = 2
        }
        is Estados.Error -> {
            nivel_actual = -1
        }
    }
    return nivel_actual
}

fun pulsar_boton(boton: Int) {
    if (estado_acual is Estados.Idle) {
        if (boton == nivel_1 && nivel_actual == 0) {
            estado_acual = Estados.MovingUP
            nivel_actual = 2
        } else if (boton == nivel_0 && nivel_actual == 1) {
            estado_acual = Estados.MovingDOWN
            nivel_actual = 2
        }
    }
}

fun terminar_subida() {
    if (estado_acual is Estados.MovingUP) {
        nivel_actual = 1
        estado_acual = Estados.Idle
    }
}

fun terminar_bajada() {
    if (estado_acual is Estados.MovingDOWN) {
        nivel_actual = 0
        estado_acual = Estados.Idle
    }
}

fun fallo() {
    estado_acual = Estados.Error
    nivel_actual = -1
}