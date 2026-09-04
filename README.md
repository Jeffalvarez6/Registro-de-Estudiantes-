# Análisis del Algoritmo de Ordenamiento para Lista Enlazada Simple

## Pregunta de Análisis
¿Por qué el algoritmo de ordenamiento que usted seleccionó (Merge Sort) resulta más apropiado para una lista enlazada que otros algoritmos conocidos? Explique desde el punto de vista de acceso a memoria y recorrido de nodos.

## Respuesta Térmica y Algorítmica

Para una **Lista Enlazada Simple**, el algoritmo **Merge Sort (Ordenamiento por Mezcla)** es sustancialmente superior a algoritmos como *QuickSort*, *HeapSort* o *BubbleSort* por las siguientes razones de arquitectura de software y gestión de memoria:

### 1. Acceso a Memoria y Acceso Secuencial vs. Aleatorio
* En estructuras contiguas como arreglos (`Array`), el acceso a un índice $i$ toma tiempo $O(1)$ gracias a la aritmética de punteros. 
* En una **Lista Enlazada**, los nodos se encuentran dispersos en la memoria Heap. Acceder al elemento $i$-ésimo requiere un recorrido secuencial de puntero en puntero, lo cual toma tiempo $O(n)$.
* Algoritmos como **QuickSort** o **HeapSort** dependen fuertemente del acceso aleatorio $O(1)$ para realizar intercambio de elementos y pivotes. Si se aplican a listas enlazadas, el costo de búsqueda de índices degrada la complejidad teórica.
* **Merge Sort** opera únicamente mediante acceso secuencial, dividiendo la lista secuencialmente y uniendo (mezclando) nodos contiguos ajustando referencias de punteros (`siguiente`), sin requerir acceso por índice.

### 2. Eficiencia Espacial en Listas Enlazadas (Sin uso de memoria auxiliar)
* En un arreglo, *Merge Sort* requiere un arreglo auxiliar de tamaño $O(n)$, lo que representa una desventaja en espacio memoria.
* En una **Lista Enlazada**, la mezcla (*merge*) **no requiere crear nuevos nodos ni usar arreglos adicionales**. Se efectúa reorganizando únicamente los enlaces de punteros existentes (`actual.setSiguiente(...)`). Esto hace que el consumo de memoria extra sea $O(log\ n)$ únicamente debido a la pila de llamadas recursivas.

### 3. Ausencia de Intercambios Costosos de Datos
* Algoritmos de intercambio como *BubbleSort* o *InsertionSort* requieren copiar los datos o hacer reasignaciones repetitivas de contenido entre nodos ($O(n^2)$ iteraciones).
* *Merge Sort* solo cambia las referencias del atributo `siguiente`, logrando una complejidad temporal garantizada de $O(n \log n)$ tanto en el peor, promedio como mejor caso.
