# Comparación de número de comparaciones: Secuencial clásico vs Centinela

> Promedios por dataset calculados sobre las 4 claves probadas (`7, 5, 2, 42`).  
> Reducción relativa (%) = 100 × (1 − (Centinela / Clásico)).

| Dataset | Promedio comparaciones — Secuencial clásico | Promedio comparaciones — Centinela | Reducción relativa (%) |
|---|---:|---:|---:|
| A (`[8, 3, 6, 3, 9]`) | 10.00 | 5.00 | 50.0% |
| B (`[5, 4, 3, 2, 1]`) | 7.50 | 3.75 | 50.0% |
| C (`[1, 2, 3, 4, 5]`) | 8.50 | 4.25 | 50.0% |
| D (`[2, 2, 2, 2]`) | 6.50 | 3.25 | 50.0% |
| **Global (todos)** | **8.13** | **4.06** | **50.0%** |

**Interpretación breve:**  
Con los nuevos datos, la variante con centinela mantiene una ventaja consistente, reduciendo en promedio el número de comparaciones en un **50%** respecto al método secuencial clásico en todos los datasets evaluados. Esta reducción se sostiene tanto cuando la clave está presente como cuando está ausente, evidenciando la eficiencia estructural del enfoque con centinela al evitar comprobaciones adicionales de límite en cada iteración.