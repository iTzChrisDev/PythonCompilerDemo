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
