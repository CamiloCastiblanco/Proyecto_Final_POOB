# Parcial Primer Corte CVDS
### POOBTriz
##### ¿Qué es?
- Es un videojuego de lógica originalmente diseñado y programado por Alekséi Pázhitnov en la Unión Soviética. Fue lanzado el 6 de junio de 1984, mientras trabajaba para el Centro de Computación Dorodnitsyn de la Academia de Ciencias de la Unión Soviética en Moscú, RSFS de Rusia. Su nombre deriva del prefijo numérico griego tetra- (todas las piezas del juego, conocidas como Tetrominós que contienen cuatro segmentos) y del tenis, el deporte favorito de Pázhitnov.

------------

##### ¿Qué debíamos hacer?
- A partir del concepto básico del juego, se debía realizar un nuevo formato con algunas funcionalidades extras. Se debía poder escoger diferentes tipos de juego, como lo es el modo 2v2, solitario y contra una IA que tenía un modo fácil y otro difícil. Adicionalmente, habían unos nuevos objetos dentro del juego llamados "Buffos" que le permitían al jugador tener varias ayudas, como por ejemplo, duplicar sus puntos por cierto tiempo, congelar la ficha en el lugar, congelar el tiempo o hacer que el descenso de las fichas fuese mucho más lento por cierta cantidad de tiempo.
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/PoobTriz.png)

![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/Tablero.png)

------------

##### Git Fork y Git Clone
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/GitFork.png)

![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/GitClone.png)

------------


##### Errores en el código (SOLID)
- S, Single responsibility principle
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/Class%20Diagram0.png)
**Error encontrado:**
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/ProblemaCodigo.png)
**Solución:**
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/SolucionDiagrama.png)
*Clase TetrisBlock*
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/SolucionCodigoBlock.png)
*Clase IShape*
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/SolucionCodigoIShape.png

------------


##### Errores en los Tests (FIRST)

- Errores en los tests:
**Error en el setUp y After:**
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/CreacionInnecesaria.png)
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/After.png)
- R, Repeatable
**Error Encontrado:**
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/ErrorTest.png)
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/TestUnrepeatable.png)
**Solución:**
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/TestArreglado.png)

#### Commit y Push
![Image text](https://github.com/CamiloCastiblanco/Proyecto_Final_POOB/blob/main/img/CommitYPush.png)

