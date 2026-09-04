public class ListaEstudiantes {
    private Nodo cabeza;
    private Nodo cola;
    private int tamanio;

    public ListaEstudiantes() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    // a) Insertar al inicio
    public void insertarInicio(Estudiante estudiante) {
        Nodo nuevoNodo = new Nodo(estudiante);
        if (estaVacia()) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
        }
        tamanio++;
    }

    // b) Insertar al final
    public void insertarFinal(Estudiante estudiante) {
        Nodo nuevoNodo = new Nodo(estudiante);
        if (estaVacia()) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.setSiguiente(nuevoNodo);
            cola = nuevoNodo;
        }
        tamanio++;
    }

    // c) Eliminar por código
    public boolean eliminarPorCodigo(String codigo) {
        if (estaVacia()) {
            return false;
        }

        // Caso 1: El nodo a eliminar es la cabeza (primer elemento)
        if (cabeza.getEstudiante().getCodigo().equalsIgnoreCase(codigo)) {
            cabeza = cabeza.getSiguiente();
            if (cabeza == null) { // Si la lista quedó vacía
                cola = null;
            }
            tamanio--;
            return true;
        }

        // Caso 2: Buscar en el resto de la lista (intermedio o último)
        Nodo actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getEstudiante().getCodigo().equalsIgnoreCase(codigo)) {
                // Caso 3: Eliminación del último nodo
                if (actual.getSiguiente() == cola) {
                    cola = actual;
                }
                // Puente de referencias para eliminar el nodo intermedio/último
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamanio--;
                return true;
            }
            actual = actual.getSiguiente();
        }

        // Caso 4: Elemento inexistente
        return false;
    }

    // d) Buscar estudiante por código
    public Estudiante buscarPorCodigo(String codigo) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getEstudiante().getCodigo().equalsIgnoreCase(codigo)) {
                return actual.getEstudiante();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    // e) Obtener posición de un estudiante (índice base 0, o -1 si no existe)
    public int obtenerPosicion(String codigo) {
        Nodo actual = cabeza;
        int posicion = 0;
        while (actual != null) {
            if (actual.getEstudiante().getCodigo().equalsIgnoreCase(codigo)) {
                return posicion;
            }
            actual = actual.getSiguiente();
            posicion++;
        }
        return -1;
    }

    // ORDENAMIENTO POR PROMEDIO USANDO MERGE SORT
    public void ordenarPorPromedio() {
        cabeza = mergeSort(cabeza);
        
        // Actualizar la referencia de la cola tras ordenar
        Nodo actual = cabeza;
        if (actual != null) {
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
        }
        cola = actual;
    }

    private Nodo mergeSort(Nodo head) {
        if (head == null || head.getSiguiente() == null) {
            return head;
        }

        // Encontrar punto medio usando técnica del puntero rápido y lento
        Nodo medio = obtenerMedio(head);
        Nodo siguienteDelMedio = medio.getSiguiente();
        medio.setSiguiente(null);

        // Dividir y conquistar
        Nodo izquierda = mergeSort(head);
        Nodo derecha = mergeSort(siguienteDelMedio);

        // Combinar sublistas ordenadas
        return mezclarOrdenado(izquierda, derecha);
    }

    private Nodo mezclarOrdenado(Nodo a, Nodo b) {
        if (a == null) return b;
        if (b == null) return a;

        Nodo resultado;
        // Ordenamiento de mayor a menor promedio
        if (a.getEstudiante().getPromedio() >= b.getEstudiante().getPromedio()) {
            resultado = a;
            resultado.setSiguiente(mezclarOrdenado(a.getSiguiente(), b));
        } else {
            resultado = b;
            resultado.setSiguiente(mezclarOrdenado(a, b.getSiguiente()));
        }
        return resultado;
    }

    private Nodo obtenerMedio(Nodo head) {
        if (head == null) return head;

        Nodo lento = head;
        Nodo rapido = head;

        while (rapido.getSiguiente() != null && rapido.getSiguiente().getSiguiente() != null) {
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente().getSiguiente();
        }
        return lento;
    }

    // Método de impresión
    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("[!] La lista de estudiantes está vacía.");
            return;
        }

        Nodo actual = cabeza;
        int i = 0;
        while (actual != null) {
            System.out.println("Posición [" + i + "] -> " + actual.getEstudiante());
            actual = actual.getSiguiente();
            i++;
        }
    }
}