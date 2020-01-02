## Replicado

"*Replicado*" es un juego para dos participantes ("Rojo" y "Azul"). La partida se realiza sobre dos tableros iguales de una misma cantidad de filas (5, 6 ó 7) y 5 columnas. Se disponen en forma contigua y vertical. En la primera fila de cada tablero se ubican fichas rojas y en la última fila se ubican fichas azules. Siempre comienza el jugador "Rojo". 

Se alternan los turnos entre los jugadores y no se puede pasar. En su turno, cada jugador indica un movimiento que contiene dos partes. La primera parte del movimiento se llama “preparación” y la segunda “réplica”. Si la parte correspondiente a la preparación o la réplica no es válida, debe ingresarse todo el movimiento de nuevo. En la “preparación”, el jugador elige una de sus fichas (de cualquiera de los tableros) y el desplazamiento (horizontal, vertical o diagonal) a realizar. En particular, indica el tablero, las coordenadas de su ficha dadas por su fila y columna, el sentido ("N": norte, "NE":noreste, "E": este, "SO": suroeste, "SE": sureste, "O": oeste, "NO": noroeste, "S": sur) y la cantidad de posiciones a mover. Todo el camino a recorrer debe estar totalmente libre y la posición final debe estar dentro de ese mismo tablero y vacía. Para la "réplica", indica solamente las coordenadas (fila y columna) de la ficha propia del otro tablero en la cual se replicará ese mismo desplazamiento. Este nuevo desplazamiento puede realizarse solamente si en esa posición hay una ficha propia, si la posición final luego del desplazamiento queda dentro del respectivo tablero y en el camino hay a lo sumo una ficha contraria. Esa ficha contraria será empujada en el mismo sentido y eventualmente puede quedar fuera del tablero. Nunca se puede empujar más de una ficha.

![tableros](https://i.imgur.com/J4Hy6Tz.png)

En cualquier momento el jugador puede ingresar “**X**”, que indica que abandona y pierde el partido. Siempre se debe indicar en pantalla a qué jugador corresponde el turno. También puede ofrecerse un empate. Para ello, se ingresa: "**E**" y si el otro jugador confirma, es empate. 

La forma de terminación del juego se define al comienzo de la partida. 

**Puede ser:** 

 - “**Sin Fichas**”: cuando en uno de los tableros no quede ninguna ficha
    de un color, gana el jugador del otro color.  
    
 - “**Por turnos**”: al realizarse una determinada cantidad de turnos por
        cada uno de los jugadores. En ese momento, considerando ambos
        tableros unidos como uno único, gana quien tenga mayor cantidad de
        columnas con mayoría de fichas de su color. Si no hay mayoría, es
        empate.

**En el sistema puede:** 

 -  Registrar jugador (nombre, edad, alias). El alias debe ser único. 
  

 - Jugar a “Replicado”. Se eligen los dos jugadores de una lista (el 
   primer elegido juega con "Rojo", siempre empieza a jugar "Rojo", el  
   otro jugador juega con "Azul"). Se indica la cantidad de filas de   
   cada tablero (ambos son iguales). Se elige además la forma de   
   terminación: “Sin Fichas” o por una cantidad de turnos que se   
   ingresará. Nota: Para el ingreso de las jugadas así como para mostrar
   el tablero deben utilizarse obligatoriamente los formatos presentados
   antes. Incluir la indicación de filas y columnas al costado del
   tablero y la separación debajo. 
   
   *Los formatos de movimiento son*: 

   **X**: abandonar, 

   **E**: empate, 

   **n F1C1 Sentido Cantidad F2C2** siendo:  **n** un 1 o 2 que representa el tablero (1 superior, 2 inferior), **F1C1** las coordenadas de preparación, **F2C2** las coordenadas de réplica, **Sentido** es: "N" "S" "E" "O" "NO" "SE" "SO" “NE” y  **cantidad** un entero positivo.
 - Ver ranking de jugadores (mostrar de    cada jugador la cantidad de
   partidas y la cantidad de ganadas,    ordenado por ganadas
   decreciente)
 - Terminar
