import csv
import sys
import time

#node-class for the linked list
class Node(object):

    #node constructor
    def __init__(self, data):
        self.data = data
        self.nextNode = None

    #returns the data of a node
    def getData(self):
        return self.data

#linked list class
class myLinkedList(object):

    #linked list constructor
    def __init__(self):
        self.head = Node(None)
        self.elements = 0

    #adds new node
    def addNext(self, data):
        if self.head.nextNode is None:
            self.head.nextNode = Node(data)
            self.elements += 1
        else:
            buffer = self.head.nextNode
            self.head.nextNode = Node(data)
            self.head.nextNode.nextNode = buffer
            self.elements += 1

    #deletes node with the key.
    def deleteElement(self, key):
        if self.head.nextNode is None:
            return False
        if self.head.nextNode.getData()[0] == key:
            self.head.nextNode = self.head.nextNode.nextNode
            self.elements -= 1
            return True
        current = self.head.nextNode
        while current is not None:
            if current.nextNode.getData()[0] == key:
                temp = current.nextNode
                current.nextNode = temp.nextNode
                del temp
                self.elements -= 1
                return True
            else:
                current = current.nextNode
        return False

    #return value of a node
    def getData(self, key):
        if self.head.nextNode is None:
            return -1
        if self.head.nextNode.getData()[0] == key:
            return self.head.nextNode.getData()
        current = self.head.nextNode
        while current is not None and current.nextNode is not None:
            if current.nextNode.getData()[0] == key:
                return current.nextNode.getData()
            else:
                current = current.nextNode
        return -1

    #return the amount of elements
    def getElements(self):
        return self.elements


    #fills a list with the datas of the linked list
    def getNodeData(self):
        data = []
        if self.head.nextNode is not None:
            temp = self.head.nextNode
            data = []
            while temp:
                data.append(temp.data)
                temp = temp.nextNode
        return data

   
#hashtable class
class Hashtable(object):

    #constructor
    def __init__(self, size):
        self.size = size
        self.elements = 0
        self.kvp = []
        self.freeSlots = [None] * size
        for x in range(size):
            self.kvp.append(myLinkedList())

    #creates the index fpr the hastable
    def remainder(self, key, i):

        if i == 0:
            hashkey = self.hashfunc(key)
        else:
            hashkey = key

        return (hashkey + round(pow(i, 2))) % self.size

    #creates a hashvalue by using the length of the key
    def hashfunc(self, key):
        result = 0
        for x in range(len(key)):
            result = result + 1
        return result

    #adds an new entry
    def add(self, kvp):
        counter = 0
        hashvalue = self.remainder(kvp[0], counter)
        self.kvp[hashvalue].addNext(kvp)
        self.elements += 1
        if self.freeSlots[hashvalue] is None:
            self.freeSlots[hashvalue] = 1

    #deletes an entry
    def delete(self, key):
        counter = 0
        hashvalue = self.remainder(key, counter)
        if self.kvp[hashvalue] == None:
            print("Eintrag nicht gefunden")
            return False
        else:
            if self.kvp[hashvalue].deleteElement(key):
                self.elements -= 1
                print("Eintrag wurde gelöscht")
                if self.kvp[hashvalue].head.nextNode is None:
                    self.freeSlots[hashvalue] = None
            else:
                print("Eintrag nicht gefunden")

    #changes the lastname
    def changeName(self, oldKey, newKey):
        hashvalue = self.remainder(oldKey, 0)
        data = self.kvp[hashvalue].getData(oldKey)
        if data != -1:
            name = data[1]
            number = data[2]
            self.delete(oldKey)
            kvp = (newKey, name, number)
            self.add(kvp)
            print("Nachname wurde geändert")
        else:
            print("Eintrag nicht gefunden")

    #checks if the list is full
    def isfull(self):
        items = 0
        for item in self.freeSlots:
            if item is not None:
                items += 1
        return self.size == items

    #checks if the list is empty
    def isempty(self):
        return self.elements == 0

    #returns the amount of elemnts
    def getElements(self):
        return self.elements

    #prints the hashmap
    def printMap(self):
        print("Hashtable: ", self.size, "Slots")
        counter = 0
        for x in range(len(self.freeSlots)):
            if self.freeSlots[x] is not None:
                counter += 1
        print("Freie Slots: ", self.size - counter)
        print("Elemente: ", self.elements)
        if self.elements != 0:
            for x in range(self.size):
                if self.kvp[x].head is not None:
                    print()
                    print("Index: ", x)
                    current = self.kvp[x].getNodeData()
                    for y in range(len(current)):
                        print("Nachname: ", current[y][0])
                        print("Vorname: ", current[y][1])
                        print("Matrikelnummer: ", current[y][2])
                        print("--------------------------------")
        else:
            print("Liste ist leer")
        print()

    #beendet das program
    def endProg(self):
        print("Programm wird beendet")
        sys.exit()

#default values
myHashtable = Hashtable(20)
myTestHashtable = Hashtable(53)
inputFile = "input.csv"
outputFile = "output.csv"
testfile = "test.csv"
name = []
firstname = []
number = []

#menu fpr the hashtable
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
        if myHashtable.isempty():
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
        if myHashtable.isempty():
            print("Liste ist leer")
            print()
        else:
            oldKey = input("Bitte geben sie den Nachnamen ein, den sie ändern wollen: ")
            newkey = input("Bitte geben sie den neuen Nachnamen ein: ")
            myHashtable.changeName(oldKey, newkey)
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
                    data = myHashtable.kvp[x].getNodeData()
                    for i in range(len(data)):
                        name = data[i][0]
                        firstname = data[i][1]
                        mnumber = data[i][2]
                        csv_writer.writerow({"Name": name, "Vorname": firstname, "Matrikelnummer": mnumber})
            myHashtable.endProg()
        except(IOError):
            print("File not found")
            myHashtable.endProg()

    else:
        print("Ungültige Eingabe")
        print()

