import re

def letters(input):
    return ''.join([c for c in input if c.isalpha() or c==" "])

def main():
    file = open("demofile.txt", "r") #deschidere fisier
    string=file.read()  #conversie textIO -> string
    new_str=string;

    #stergere semne de punctuatie
    new_str = re.sub('\.', '', new_str) #stergere .
    new_str = re.sub(',', '', new_str) #stergere ,
    new_str = re.sub('!', '', new_str) #stergere !
    new_str = re.sub('\?', '', new_str) #stergere ?

    print("Selectare alte procesari:\n"
          "1-Stergere spatii multiple\n"
          "2-lower case\n"
          "3-UPPER case\n"
          "4-Filtrare cuvinte cu un anumit nr de litere\n"
          "5-Filtrare numere\n")

    r=0
    while(r!=2):
        x=int(input("Introducere optiune:"))
        r=r+1
        if(x==1):
            new_str = re.sub(' +', ' ', new_str)  # stergere spatii multiple
        elif(x==2):
            new_str = new_str.lower()
        elif(x==3):
            new_str = new_str.upper()
        elif(x==4):
            n=int(input("Cate litere sa aiba cuvintele?"))
            temp=new_str.split() #separa string-ul in lista de cuvinte
            new_str=list(filter(lambda ele: len(ele) != n,temp)) #filtreaza cuvintele care nu au n litere
            new_str=' '.join(new_str) #concateneaza cuvintele intr-un string
        elif(x==5):
            new_str=letters(new_str)

    print(string) #OG
    print(new_str) #notOG
main()