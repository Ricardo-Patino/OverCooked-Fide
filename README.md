# OverCooked-Fide
 juego chef de un restaurante

Instrucciones Generales - Juego OverCooked-Fide

En este juego el jugador es el chef de un restaurante que prepara las mejores hamburguesas.
Como chef prepara las hamburguesas que soliciten los clientes. Al recibir las órdenes debe
agregar los ingredientes adecuados según la orden. Debido a que es el mejor restaurante, cada
20 segundos ingresa una nueva orden. Si ya hay una orden en proceso se debe terminar antes
de poder continuar con la siguiente. Las órdenes se preparan en el orden en que van llegando.

Las órdenes indican el tipo de hamburguesa quiere el cliente y ahí se indican los ingredientes
necesarios para prepararla. El jugador como chef, prepara las órdenes utilizando los ingredientes
que están en una cinta transportadora.

El jugador tiene 5 minutos para preparar la mayor cantidad de órdenes posibles antes de que se
acabe el tiempo. El tiempo es visible para el jugador en todo momento.

Cinta Transportadora

La cinta transportadora puede tener 5 ingredientes sobre ella a la vez. Se mueve de derecha a
izquierda. La cinta transportadora se mueve 1 posición hacia la izquierda cada vez que el jugador
toma un ingrediente. Cuando la cinta transportadora tenga solamente 3 ingredientes deben
aparecer nuevos ingredientes en las posiciones vacías.

Debido a que la cinta transportadora es circular, cuando un ingrediente está en la posición del
extremo izquierdo y se realiza un movimiento a la izquierda, el ingrediente aparecerá en el
extremo derecho.

Adicionalmente, el jugador puede tirar un ingrediente al basurero en caso de que ninguno de los
que esté sobre la cinta transportadora le sirva para completar su orden.

Ingredientes y órdenes
Hay 3 tipos de hamburguesas:
● Hamburguesa de carne (pan, carne) 5pts
● Hamburguesa con queso (pan, carne, queso) 10pts
● Hamburguesa clásica (pan, carne, lechuga, queso) 15pts
Las órdenes se generan de manera aleatoria cada 20 segundos. Se pueden generar un
máximo de 3 órdenes a la vez y estas deben ser visibles al usuario. Si transcurren los 20
segundos y aún hay 3 órdenes, no se genera una nueva.

Cada tipo de orden tiene un puntaje asignado. Conforme se van completando se deben
sumar los puntos al puntaje final del jugador
