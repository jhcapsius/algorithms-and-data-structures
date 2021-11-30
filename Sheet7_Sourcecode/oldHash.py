import csv
import sys
import time

#Hashtableklasse
class Hashtable(object):

    #constructor
    def __init__(self, size):
        self.size = size
        self.elements = 0
        self.kvp = [None] * self.size

    """Inserts a new entry into an empty slot. If the slot is already occupied, the next empty slot is searched for. If an entry already exists, it will be replaced."""
    def add(self, kvp):
        counter = 0
        hashvalue = self.remainder(kvp[0], counter)
        if self.kvp[hashvalue] is None:
            self.kvp[hashvalue] = kvp
            self.elements += 1
        elif self.kvp[hashvalue][0] == kvp[0]:
            self.kvp[hashvalue] = kvp
        else:
            counter += 1
            nextslot = self.remainder(hashvalue, counter)
            while self.kvp[nextslot] is not None and self.kvp[nextslot][0] != kvp[0]:
                counter += 1
                print(nextslot)
                nextslot = self.remainder(nextslot, counter)
            if self.kvp[nextslot] is None:
                self.kvp[nextslot] = kvp
                self.elements += 1
            elif self.kvp[nextslot][0] == kvp[0]:
                self.kvp[nextslot] = kvp

    #creates an index for the hashtable
    def remainder(self, key, i):

        if i == 0:
            hashkey = self.hashfunc(key)
        else:
            hashkey = key
            # 11,5 minuten (hashkey + round(pow((i / 2), 2) - 1)) % self.size
        return (hashkey + i*i) % self.size

    #creates a hash value for the key
    def hashfunc(self, key):
        result = 0
        for x in range(len(key)):
            result = result + 1
        return result

    #deletes an entry
    def delete(self, key):
        counter = 0
        startslot = self.remainder(key, counter)
        slot = startslot
        exitLoop = False
        while exitLoop is not True:
            if self.kvp[slot] is not None and self.kvp[slot][0] == key:
                self.kvp[slot] = None
                self.elements -= 1
                print("Eintrag wurde gelöscht")
                exitLoop = True
            else:
                counter += 1
                slot = self.remainder(slot, counter)
                if slot == startslot:
                    print("Eintrag nicht gefunden")
                    exitLoop = True

    #changes the last name
    def changeLastName(self, oldkey, newkey):
        counter = 0
        startslot = self.remainder(oldkey, counter)
        slot = startslot
        exitLoop = False
        while exitLoop is not True:
            if self.kvp[slot] is not None and self.kvp[slot][0] == oldkey:
                name = self.kvp[slot][1]
                mrNumber = self.kvp[slot][2]
                newKVP = (newkey, name, mrNumber)
                self.kvp[slot] = None
                self.add(newKVP)
                print("Nachname wurde geändert.")
                exitLoop = True
            else:
                counter += 1
                slot = self.remainder(slot, counter)
                if slot == startslot:
                    print("Eintrag nicht gefunden")
                    exitLoop = True

    #checks if the list is empty
    def isEmpty(self):
        return self.elements == 0

    #checkts if the list is full
    def isfull(self):
        items = 0
        for item in self.kvp:
            if item is not None:
                items += 1
        return items == self.size

    #prints the hashtable
    def printMap(self):
        print("Hashtable: ", self.size, "Slots")
        print("Freie Plätze: ", self.size - self.elements)
        print()
        if self.elements != 0:
            for x in range(self.size):
                if self.kvp[x] is not None:
                    print("Index: ", x)
                    print("Nachname: ", self.kvp[x][0])
                    print("Vorname: ", self.kvp[x][1])
                    print("Matrikelnummer: ", self.kvp[x][2])
                    print("________________________")
        else:
            print("Liste ist leer")
        print()

    #returns size of the hashtable
    def getSize(self):
        return self.size

    #return an element
    def getEntry(self, index):
        return self.kvp[index]

    
    def getElements(self):
        return self.elements

    #close the program
    def endProg(self):
        print("Programm wird beendet")
        sys.exit()

#default values
myHashtable = Hashtable(53)
myTestHashtable = Hashtable(53)
inputFile = "input.csv"
outputFile = "output.csv"
testfile = "test.csv"
name = []
firstname = []
number = []

#menu
while True:
    print("Menü")
    print("[P] Print hashtable")
    print("[D] Delete entry")
    print("[A] Add entry")
    print("[C] Change lastname")
    print("[T] Test")
    print("[K] Print Test")
    print("[R] Read list")
    print("[E] save and end")
    print()
    menue_option = str(input()).upper()
    print()
    if menue_option == "P":
        myHashtable.printMap()
        print()
    elif menue_option == "D":
        if myHashtable.isEmpty():
            print("Liste ist leer")
            print()
        else:
            key = input("Bitte geben sie den Namen der Person ein, die sie entfernen wollen: ")
            myHashtable.delete(key)
            print()

    elif menue_option == "A":
        if myHashtable.isfull():
            print("Liste ist voll, bitte löschen sie einen Eintrag")
            print()
        else:
            data = ()
            key = input("Bitte geben sie den Nachnamen ein: ")
            data1 = input("Bitte geben sie den Vornamen ein: ")
            data2 = input("Bitte geben sie die Matrikelnummer ein: ")
            data = (key, data1, data2)
            myHashtable.add(data)
            print("Eintrag wurde hinzugefügt")
            print()
    elif menue_option == "C":
        if myHashtable.isEmpty():
            print("Liste ist leer")
            print()
        else:
            oldKey = input("Bitte geben sie den Nachnamen ein, den sie ändern wollen: ")
            newkey = input("Bitte geben sie den neuen Nachnamen ein: ")
            myHashtable.changeLastName(oldKey, newkey)
            print()
    elif menue_option == "T":
        try:
            start = time.time()
            with open(testfile) as csv_file:
                csv_reader = csv.DictReader(csv_file)
                for columns in csv_reader:
                    name.append(columns["Name"])
                    firstname.append(columns["Vorname"])
                    number.append(columns["Matrikelnummer"])
            for i in range(len(name)):
                data = (name[i], firstname[i], number[i])
                myTestHashtable.add(data)
                myTestHashtable.printMap()
            end = time.time()
            if myTestHashtable.getElements() == 0:
                print("Liste ist leer")
            else:
                start2 = time.time()
                myTestHashtable.delete(name[-1])
                end2 = time.time()
            print("Laufzeit einlesen und einsortieren:", end - start, "s")
            print("Laufzeit löschen", end2 - start2, "s")
        except(IOError):
            print("File not found")
        print()

    elif menue_option == "K":
        myTestHashtable.printMap()
        print()


    elif menue_option == "R":
        try:
            with open(inputFile) as csv_file:
                csv_reader = csv.DictReader(csv_file)
                for columns in csv_reader:
                    name.append(columns["Name"])
                    firstname.append(columns["Vorname"])
                    number.append(columns["Matrikelnummer"])
            for i in range(len(name)):
                data = (name[i], firstname[i], number[i])
                myHashtable.add(data)
            print("Datei wurde eingelesen")
        except(IOError):
            print("File not found")
        print()

    elif menue_option == "E":
        try:
            with open(outputFile, mode="w") as csv_file:
                fieldnames = ["Name", "Vorname", "Matrikelnummer"]
                csv_writer = csv.DictWriter(csv_file, fieldnames=fieldnames)
                csv_writer.writeheader()
                for x in range(myHashtable.size):
                    data = myHashtable.getEntry(x)
                    if data is not None:
                        name = data[0]
                        firstname = data[1]
                        mnumber = data[2]
                        csv_writer.writerow({"Name": name, "Vorname": firstname, "Matrikelnummer": mnumber})
            myHashtable.endProg()
        except(IOError):
            print("File not found")
            myHashtable.endProg()

    else:
        print("Ungültige Eingabe")
        print()
