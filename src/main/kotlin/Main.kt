package org.danielcastelao.dam

fun main() {
    println("Estado inicial: $estado_acual")
    println("Nivel actual: ${gestor()}")

    println("\nPulsamos nivel_1")
    pulsar_boton(nivel_1)
    println("Estado: $estado_acual")
    println("Nivel: ${gestor()}")

    terminar_subida()
    println("Llegamos al nivel 1")
    println("Estado: $estado_acual")
    println("Nivel: ${gestor()}")

    println("\nPulsamos nivel_0")
    pulsar_boton(nivel_0)
    println("Estado: $estado_acual")
    println("Nivel: ${gestor()}")

    terminar_bajada()
    println("Llegamos al nivel 0")
    println("Estado: $estado_acual")
    println("Nivel: ${gestor()}")

    fallo()
    println("\nPulsamos al nivel_1")
    pulsar_boton(nivel_1)
    println("Estado: $estado_acual")
    println("Nivel: ${gestor()}")
}