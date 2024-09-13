class switch_statement:
    num = 400
    match num:
        case 400:
            print("Cumple - 400")
        case 401:
            print("NO cumple - 401")
        case 404:
            print("No cumple - 404")
        case _:
            print("Ningún valor cumple.")
