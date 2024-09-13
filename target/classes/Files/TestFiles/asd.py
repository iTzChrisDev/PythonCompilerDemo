def validar_indentacion(codigo):
    indent_stack = [
        0
    ]  # Pila para los niveles de indentación, empezando en 0 (sin indentación)
    INDENT = None  # Para guardar el tipo de indentación (tabs o espacios)
    line_number = 0  # Número de línea para propósitos de error

    for linea in codigo.splitlines():
        line_number += 1

        # Ignorar líneas vacías o comentarios
        if linea.strip() == "" or linea.strip().startswith("#"):
            continue

        # Contar la indentación de la línea actual
        stripped_line = linea.lstrip()  # Quitar los espacios o tabs al inicio
        indent_level = len(linea) - len(stripped_line)

        # Detectar si es la primera vez que vemos una indentación
        if indent_level > 0 and INDENT is None:
            INDENT = linea[
                :indent_level
            ]  # Guardar el tipo de indentación (tabs o espacios)

        # Validar que todas las líneas estén usando el mismo tipo de indentación
        if INDENT and not linea.startswith(INDENT * (indent_level // len(INDENT))):
            print(f"Error: mezcla de tipos de indentación en la línea {line_number}")
            return False

        # Si la línea tiene menos indentación, verificar que coincida con la pila
        if indent_level < indent_stack[-1]:
            while indent_stack and indent_stack[-1] > indent_level:
                indent_stack.pop()  # Reducir el nivel de indentación
            if indent_stack[-1] != indent_level:
                print(
                    f"Error: nivel de indentación incorrecto en la línea {line_number}"
                )
                return False

        # Si la línea tiene más indentación, agregar el nivel a la pila
        elif indent_level > indent_stack[-1]:
            indent_stack.append(indent_level)

    print("Indentación correcta")
    return True


# Ejemplo de uso
codigo_python = """
def ejemplo():
    if True:
    print("Indentado correctamente")
    print("Fin del bloque")
"""

validar_indentacion(codigo_python)
