import sys
import csv
from typing import IO

class HashTable(object):
    #constructor
    def __init__(self, size):
        #hastable size
        self.size = size
        #array for the keys
        self.slots = [None] * self.size
        #array for the values
        self.data = [[] for i in range(self.size)] 
        #packt den vornamen und die matrikelnummer in eine liste die in self.data abgelegt wird
        self.datawrapper = []

    #inserts data in the hashtable
    def put(self, key, data1, data2):
        #the counter counts the number of failed attempts until a key/data pair has found a free slot.
        counter = 0
        #remainder acts as hash value
        hashvalue = self.remainder(key, counter)
        #if a free space is found during the first attempt, the key/data pair is stored at this position
        if self.slots[hashvalue] == None:
            self.slots[hashvalue] = key
            self.datawrapper = [data1, data2]
            self.data[hashvalue].append(self.datawrapper)
        #existiert der key bereits, werden die neuen daten drangehängt
        else:
            if self.slots[hashvalue] == key:
                self.datawrapper = [data1, data2]
                self.data[hashvalue].append(self.datawrapper)
            else:
                #erhöht den counter um 1, da es der erste fehlversuch ist
                counter += 1
                #speichert den neuen rest der remainderfunktion in nextslot
                nextslot = self.remainder(hashvalue, counter)
                #solange die stelle in slots[nextslot] ungleich None ist, y wird der counter um 1 erhöht und ein neuer restwert
                # wird mit der remainderfunktion bestimmt 
                while self.slots[nextslot] != None:
                    counter +=1
                    nextslot = self.remainder(nextslot, counter)
                #ist der nächste verfügbare Slot None, wird das key/data paar an dieser stelle gespeichert    
                if self.slots[nextslot] == None:
                    self.slots[nextslot] = key
                    self.datawrapper = [data1, data2]
                    self.data[nextslot].append(self.datawrapper)
                #existiert der key bereits, werden die neuen daten drangehängt 
                elif self.slots[nextslot] == key:
                    self.datawrapper = [data1, data2]
                    self.data[nextslot].append(self.datawrapper)

    #gibt das ergebnis aus dem key-wert, der mit dem counter-wert i hoch 2 addiert wird, modulo die größe des hastable wieder
    def remainder(self, key, i):
        #hashed_key = hash(key)
        #erstellt beim ersten mal mit der myHashFunction einen hashed_key. ansonsten wird der restwert benutzt
        if i == 0:
            hashed_key = self.myHashFunction(key)
        else:
            hashed_key = key    
            
        return (hashed_key + round(pow(i/4,2))) % self.size     

    #wandelt einen string in einen int um, der aus der summe der einzelnen buchstaben des strings besteht
    def myHashFunction(self, key):
        result = 0
        string_to_char = []
        char_to_int = []
        string = key
        #wandelt den string in chars um und speichert sie in einer liste
        for x in range(len(string)):
            string_to_char.append(string[x])
        #print(string_to_char)    
        #wandelt die chars in ints um und speichert sie in einer liste
        for y in range(len(string_to_char)):
            string2 = string_to_char[y]
            char_to_int.append(ord(string2))    
        #print(char_to_int)    
        #addiert die int-werte der chars auf
        for z in range(len(char_to_int)):
            result = result + char_to_int[z]        
        #print(result)
        #gibt das ergebnis aus den aufaddierten int-werte zurück
        return result

    def print(self):
        print("Studentenliste")
        #zählt anzahl der freien slots
        counter = 0
        for x in range(len(self.slots)):
            if self.slots[x] is None:
                counter += 1
        print("Anzahl freier Keys: " + str(counter))
        print()
        #gibt die key und values aus            
        for x in range(len(self.slots)):
            if self.slots[x] is not None:
                print("Nachname: ",self.slots[x])
            for y in range(len(self.data[x])):
                print(str(y+1) +".Eintrag: " + str(self.data[x][y]))      
            

    #löscht den eintrag
    def deleteEntry(self, key):
        #sucht nach dem key und löscht alle einträge des keys
        for x in range(len(self.slots)):
            if key == self.slots[x]:
                self.slots[x] = None
                self.data[x] = []
                print("Eintrag wurde gelöscht")
                print()
   

    def changeKey(self, old_key, new_key):
        #sucht nach dem alten key und ersetzt ihn mit dem neuen
        for x in range(len((self.slots))):
            if old_key == self.slots[x]:
                self.slots[x] = new_key 

    #checkt ob es den gesuchten eintrag gibt
    def keyCheck(self,key):
        for x in range(len(self.slots)):
            if key == self.slots[x]:
                #gibt true zurück wenn der key gefunden wurde
                return True
        #gibt false zurück wenn der key nicht gefunden wurde        
        return False   
        
    #fragt ab ob die liste voll ist
    def is_full(self):
        items = 0
        #zählt für jeden nicht leeren platz +1 hoch
        for item in self.slots:
            if item is not None:
                items += 1
        #gibt zurück ob die anzahl der einträge = größe der hastable ist n (true/false)
        return items == self.size

    #gibt die values eines keys wieder
    def getEntryData(self, key):
        data = []
        for x in range(len(self.slots)):
            if key == self.slots[x]:
                for y in range(len(self.data[x])):
                    data.append(self.data[x][y])
        return data
    
    #gibt die keys wieder
    def getKeys(self):
        keys = []
        for x in range(len(self.slots)):
            if self.slots[x] is not None:
                keys.append(self.slots[x])
        return keys    
    
#beendet das programm                
def endProgram():
    print("Das Programm wird beendet.")
    sys.exit()

#listen
name = []
firstname = []
number = []
#speichert file name    
file = "studentdata2.csv"
#versuchte die file zu öffnen und liest die namen, vornamen und matrikelnummern in die oben definierte listen ein
try:
    with open(file) as csv_file:
        csv_reader = csv.DictReader(csv_file)
        for columns in csv_reader:
            name.append(columns["Name"])
            firstname.append(columns["Vorname"])
            number.append(columns["Matrikelnummer"])
#Sollte die file nicht gefunden werden, startet man mit einer leeren hashtable            
except(IOError):
    print("File not found")
    
#hashtable object mit größe myhashtablelength wird erstellt
myHashTableLength = len(name)+20
myHashTable = HashTable(myHashTableLength)

#füllt die hashtable mit den daten aus dem dokument
for i in range(len(name)):
    #print(i)
    keys = name[i]
    #print(key_string)
    names = firstname[i]
    numbers = number[i]
    myHashTable.put(keys, names, numbers)

while True:
    #menüinterface
    print("Menü")
    print("[A] Hashtable anzeigen")
    print("[L] Eintrag löschen")
    print("[H] Eintrag hinzufügen")
    print("[N] Nachname aendern")
    print("[E] Speichern und verlassen")
    print()
    #eingabe der Optionen
    menue_option = str(input()).upper()
    print()

    #gibt eingabe = a oder A die hashtable aus
    if(menue_option == "A"):
        myHashTable.print()
        print()

    #löscht bei eingabe = l oder L einen Eintrag   
    elif(menue_option == "L"):
        #gibt die hashtable aus, damit man sehen kann welche einträge gelöscht werden können
        myHashTable.print()
        print()
        #speichert den namen und die matrikelnummer des studenten, dessen eintrag gelöscht werden soll
        key_string = input("Bitte geben sie den Nachnamen der Person, die sie löschen wollen, ein: ")
        #checkt ob die person teil der liste ist und löscht sie wenn vorhanden  
        myHashTable.deleteEntry(key_string)

    #fügt neue einträge hinzu
    elif(menue_option == "H"):
        #eingabe von nachname, vorname und matrikelnummer  
        key_string = str(input("Bitte geben sie einen Nachnamen ein: "))
        data_list = str(input("Bitte geben sie einen Vornamen ein: "))
        data_list2 = str(input("Bitte geben sie eine Matrikelnummer ein: "))
        #checkt ob die liste voll ist. wenn ja können keine weiteren einträge gemacht werden
        
        if myHashTable.is_full():
            print("Hashtable ist voll! Es können keine weiteren Studenten hinzugefügt werden.")
            print()       
        else:
            myHashTable.put(key_string, data_list, data_list2)
            print("Der Student wurde der Liste hinzugefügt")        
        print()

    #ändert vorhandenen nachnamen    
    elif(menue_option == "N"):
        #myHashTable.print2()
        #eingabe von nachname und matrikelnummer
        old_key = str(input("Bitte geben sie den Nachnamen der Person, dessen Nachnamen sie ändern wollen, ein: "))
        #checkt ob es den gesuchten eintrag gibt und weist data den vornamen zu
        if myHashTable.keyCheck(old_key):
            new_key = str(input("Bitte geben sie den neuen Nachnamen ein: "))
            myHashTable.changeKey(old_key, new_key)
            print("Nachname wurde geändert")
            print()
        else:
            print("Der zu änderne Nachname wurde nicht gefunden")
            print()   

    elif (menue_option == "E"):
        #liest die keys und values in listen ein
        keys = myHashTable.getKeys()
        data = []
        for x in range(len(keys)):
            data.append(myHashTable.getEntryData(keys[x]))  
        names = []
        matriculation_numbers = []
        for x in range(len(data)):
            for y in range(len(data[x])):
                for z in range(len(data[x][y])):
                    if z == 0:
                        names.append(data[x][y][z])
                    else:
                        matriculation_numbers.append(data[x][y][z])      
        #gibt an in welcher file es gespeichert wird
        file = "studentdata3.csv"
        #versucht die file zu öffnen und die überschriften und werte der pairs zeilenweise in die zieldatei zu schreiben und beendet danach das programm
        try:
            with open(file, mode = "w") as csv_file:
                fieldnames = ["Name", "Vorname","Matrikelnummer"]
                csv_writer = csv.DictWriter(csv_file, fieldnames = fieldnames)
                csv_writer.writeheader()
                for x in range(len(keys)):
                    lastname = keys[x]
                    name = names[x]
                    matriculation_number = matriculation_numbers[x]
                    csv_writer.writerow({"Name": lastname, "Vorname": name, "Matrikelnummer": matriculation_number})
                endProgram()
        #sollte die zieldatei nicht gefunden werden, wird das programm geschlossen 
        except(IOError):
            print("File not found")
            endProgram()
    else:
        print("Ungültige Eingabe")
        print()        
