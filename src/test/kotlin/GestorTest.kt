package org.danielcastelao.dam

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class GestorTest {

    //Se ejecuta ANTES de cada test para garantizar un punto de partida limpio
    @AfterTest
    fun restaurarEstado() {
        estado_acual = Estados.Idle
        nivel_actual = 0
    }

    // Estado inicial
    @Test
    fun `estado inicial`() {
        assertIs<Estados.Idle>(estado_acual)
        assertEquals(0, gestor())
    }

    // Probar estado MovingUP, subiendo desde el nivel 0 al 1, pasando por 2 que indica que esta en movimiento
    @Test
    fun `subir desde nivel 0`() {
        pulsar_boton(nivel_1)
        assertIs<Estados.MovingUP>(estado_acual)
        assertEquals(2, gestor())
    }

    // Probar que termino la subida en el nivel 1 y que esté en reposo
    @Test
    fun `terminar subida`() {
        pulsar_boton(nivel_1)
        terminar_subida()
        assertIs<Estados.Idle>(estado_acual)
        assertEquals(1, gestor())
    }

    // Probar estado MovingDOWN, bajando desde el nivel 1 al 0
    @Test
    fun `bajar desde nivel 1`() {
        nivel_actual = 1
        pulsar_boton(nivel_0)
        assertIs<Estados.MovingDOWN>(estado_acual)
        assertEquals(2, gestor())
    }

    // Probar que termino la bajada en nivel 0 y que esté en reposo
    @Test
    fun `terminar bajada`() {
        nivel_actual = 1
        pulsar_boton(nivel_0)
        terminar_bajada()
        assertIs<Estados.Idle>(estado_acual)
        assertEquals(0, gestor())
    }

    // Probar que una vez que esté en el nivel 1 no pueda subir más
    @Test
    fun `no subir si ya estamos en nivel 1`() {
        nivel_actual = 1
        pulsar_boton(nivel_1)
        assertIs<Estados.Idle>(estado_acual)
        assertEquals(1, gestor())
    }

    // Probar que una vez que esté en el nivel 0 no pueda bajar más
    @Test
    fun `no bajar si ya estamos en nivel 0`() {
        pulsar_boton(nivel_0)
        assertIs<Estados.Idle>(estado_acual)
        assertEquals(0, gestor())
    }

    // Probar error en reposo
    @Test
    fun `fallo desde Idle`() {
        fallo()
        assertIs<Estados.Error>(estado_acual)
        assertEquals(-1, gestor())
    }

    // Probar error mientas se ejecuta el estado MovingUP
    @Test
    fun `fallo durante la subida`() {
        pulsar_boton(nivel_1)
        fallo()
        assertIs<Estados.Error>(estado_acual)
        assertEquals(-1, gestor())
    }

    // Probar error mientras se ejecuta el estado MovingDOWN
    @Test
    fun `fallo durante la bajada`() {
        nivel_actual = 1
        pulsar_boton(nivel_0)
        fallo()
        assertIs<Estados.Error>(estado_acual)
        assertEquals(-1, gestor())
    }

    // Probar que se mantenga en el nivel ejecutado
    @Test
    fun `mantener el nivel 1 cuando esta parado`() {
        nivel_actual = 1
        assertEquals(1, gestor())
    }
}