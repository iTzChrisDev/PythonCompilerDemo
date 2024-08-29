class example:
    num = 1
    # FOR
    for i in range(150):
        if i % 2 == 0:
            print("El valor es: ", i)

    # IF
    if (num > 0 and num < 10) or (num == 100):
        print("Valor correcto :D")

    # WHILE
    while num != 20:
        print("Posición: ", num)
        num += 1

    # MATCH (SWITCH)
    match 400, 404:
        case 400, 401:
            print("No cumple el segundo valor.")
        case 401, 404:
            print("No cumple el primer valor.")
        case 400, 404:
            print("VALORES CORRECTOS.")
        case _:
            print("Ningun valor cumple.")
