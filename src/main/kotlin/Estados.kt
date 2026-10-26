
package org.danielcastelao.dam

sealed class Estados {
    // Representa el estado de reposo de la maquina
    object Idle : Estados()
    // Indica que la máquina está subiendo
    object MovingUP : Estados()
    // Indica que la máquina está bajando
    object MovingDOWN : Estados()
    // Representa el estado de error
    object Error : Estados()
}
