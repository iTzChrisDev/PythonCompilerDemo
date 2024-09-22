class completo:
    num = 1
    # FOR
    for i in range(150):
        print("El valor es: ", i)

    # IF
    if (num > 0 and num < 10) or (num == 100):
        print("Correcto")

    # WHILE
    while num != 20:
        print("Posicion: ", num)
        num += 1

    # MATCH (SWITCH)
    match 400:
        case 400:
            print("Cumple - 400")
        case 401:
            print("NO cumple - 401")
        case 404:
            print("No cumple - 404")
