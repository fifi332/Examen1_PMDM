package org.danielcastelao.dam

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class GestorTest {

    @AfterTest
    fun restaurarEstado() {
        estado_acual = Estados.Idle
        nivel_actual = 0
    }

    @Test
    fun `estado inicial`() {
        assertIs<Estados.Idle>(estado_acual)
        assertEquals(0, gestor())
    }

    @Test
    fun `subir desde nivel 0`() {
        pulsar_boton(nivel_1)
        assertIs<Estados.MovingUP>(estado_acual)
        assertEquals(2, gestor())
    }

    @Test
    fun `terminar subida`() {
        pulsar_boton(nivel_1)
        terminar_subida()
        assertIs<Estados.Idle>(estado_acual)
        assertEquals(1, gestor())
    }

    @Test
    fun `bajar desde nivel 1`() {
        nivel_actual = 1
        pulsar_boton(nivel_0)
        assertIs<Estados.MovingDOWN>(estado_acual)
        assertEquals(2, gestor())
    }

    @Test
    fun `terminar bajada`() {
        nivel_actual = 1
        pulsar_boton(nivel_0)
        terminar_bajada()
        assertIs<Estados.Idle>(estado_acual)
        assertEquals(0, gestor())
    }

    @Test
    fun `no subir si ya estamos en nivel 1`() {
        nivel_actual = 1
        pulsar_boton(nivel_1)
        assertIs<Estados.Idle>(estado_acual)
        assertEquals(1, gestor())
    }

    @Test
    fun `no bajar si ya estamos en nivel 0`() {
        pulsar_boton(nivel_0)
        assertIs<Estados.Idle>(estado_acual)
        assertEquals(0, gestor())
    }

    @Test
    fun `fallo desde Idle`() {
        fallo()
        assertIs<Estados.Error>(estado_acual)
        assertEquals(-1, gestor())
    }

    @Test
    fun `fallo durante la subida`() {
        pulsar_boton(nivel_1)
        fallo()
        assertIs<Estados.Error>(estado_acual)
        assertEquals(-1, gestor())
    }

    @Test
    fun `fallo durante la bajada`() {
        nivel_actual = 1
        pulsar_boton(nivel_0)
        fallo()
        assertIs<Estados.Error>(estado_acual)
        assertEquals(-1, gestor())
    }

    @Test
    fun `mantener el nivel 1 cuando esta parado`() {
        nivel_actual = 1
        assertEquals(1, gestor())
    }
}