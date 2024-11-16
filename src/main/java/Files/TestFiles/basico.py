class ciclo_simple:
    cont = 12
    flag = True
    while flag:
        if cont != 100:
            print("Invalido:", cont)
            cont += 1
        else:
            print("Valido:", cont)
            flag = False
