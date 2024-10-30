# 🐍➡️☕ Compilador Básico de Python en Java

Proyecto de **Lenguajes y Autómatas** para construir un compilador simple que analiza código en Python desde Java. Este proyecto realiza análisis léxico, sintáctico y semántico para verificar la estructura y semántica de programas sencillos en Python.

## 📜 Descripción

El objetivo del proyecto es implementar un compilador básico en Java que pueda:
1. **🔍 Análisis Léxico**: Identificar y clasificar tokens (como palabras clave, identificadores y operadores).
2. **📐 Análisis Sintáctico**: Verificar la estructura del código según una gramática básica de Python.
3. **🧠 Análisis Semántico**: Validar reglas semánticas, como tipos de datos y uso de variables.

## 📂 Estructura del Proyecto

La estructura del proyecto está organizada en los siguientes directorios:

- **Files**: Almacena archivos auxiliares o de ejemplo para verificar el funcionamiento.
- **GUI**: Contiene la interfaz gráfica del usuario para interactuar con el compilador.
- **LexerOperations**: Implementa el análisis léxico para identificar tokens en el código Python.
- **ParserOperations**: Contiene el código para realizar el análisis sintáctico, validando la estructura del código.
- **Resources**: Incluye archivos de recursos, como iconos o imagenes adicionales.
- **SemanticOperations**: Implementa el análisis semántico, verificando reglas de tipos y el uso adecuado de variables.
- **Tokens**: Define los tipos de tokens utilizados en el análisis léxico (palabras clave, operadores, identificadores, etc.).

## 🔍 Ejemplo

Dado un archivo Python de entrada como:

```python
class semantic:
    a = 100
    b = 99
    c = 5.5
    d = 123 * (a - 45 + (b - 32))
    e = True
    f = ":D"
    g = "hola" + " mundo " + f

    if (a > 0 and c < 10) or (a == 100 and f == "hola") or e != False:
        print("Correcto")

    for i in range(6, 12):
        print(i)

