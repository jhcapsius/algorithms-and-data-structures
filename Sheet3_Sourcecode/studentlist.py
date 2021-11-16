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
        self.data1 = [None] * self.size
        self.data2 = [None] * self.size

   #inserts data in the hashtable
    def put(self, key, data1, data2):
        #the counter is used to generate a hashvalue
        counter = 0
        #creates a hashvalue
        hashvalue = self.remainder(key, counter)

        #if a free space is found during the first attempt, the key/data pair is stored at this position
        if self.slots[hashvalue] == None:
            self.slots[hashvalue] = key
            self.data1[hashvalue] = data1
            self.data2[hashvalue] = data2

        #if the key already exists, the old data will be replaced with the new data
        elif self.slots[hashvalue] == key:
            self.data1[hashvalue] = data1
            self.data2[hashvalue] = data2
        else:
                counter += 1
                
                #creates a new hashvalue as long as self.slots[nextslot] is not none. if the next slot is none, the key/data pair is stored at this position
                nextslot = self.remainder(hashvalue, counter)
                while self.slots[nextslot] != None:
                    counter +=1
                    nextslot = self.remainder(nextslot, counter)
                if self.slots[nextslot] == None:
                    self.slots[nextslot] = key
                    self.data1[nextslot] = data1
                    self.data2[nextslot] = data2

    #used to generate a hash key with a given key and the counter
    def remainder(self, key, i):
        if i == 0:
            hashed_key = self.myHashFunction(key)
        else:
            hashed_key = key    
        return (hashed_key + round(pow(i/2,2))-1) % self.size     

    #used to create a integer from the key
    def myHashFunction(self, key):
        result = 0
        string_to_char = []
        char_to_int = []
        string = key

        #converts the given string to chars and stores them in the array
        for x in range(len(string)):
            string_to_char.append(string[x])
         
        #converts the given chars to integer and stores them in the array
        for y in range(len(string_to_char)):
            string2 = string_to_char[y]
            char_to_int.append(ord(string2))    
        
        #generates a hashvalue with the integer by add them
        for z in range(len(char_to_int)):
            result = result + char_to_int[z]        
       
        #returns the hashvalue
        return result

    #checks if the list is full
    def is_full(self):
        items = 0
        
        #counts +1 for every not none index in the list
        for item in self.slots:
            if item is not None:
                items += 1
        #returns if amount of items equals to the size of the array        
        return items == self.size

    #checkt ob die matrikelnummer enthalten ist
    def checkMNumber(self, m_number):
        for x in range(len(self.data2)):
            if m_number == self.data2[x]:
                return True
            else:
                return False
    
    #checks if the matriculation number is included
    def entryCheck(self,name, m_number):
        bool1 = False
        bool2 = False
        for x in range(len(self.data2)):
            if name == self.slots[x]:
                bool1 = True
            if m_number == self.data2[x]:
                bool2 = True
        if bool1 and bool2:
            return True        
        else:
            return False       
    
    #deletes entries
    def deleteEntry(self, key, data2):
        #checks if the entry exists, if it doesn't the function exits and returns false
        if self.entryCheck(key, data2) is False:
            return False
            
        
        #If there is an entry, it is iterated until it is found and the entry is resetted.    
        else:
            counter = 0
            run = True
            hashvalue = self.remainder(key, counter)
            while run:
                if self.slots[hashvalue] == key and self.data2[hashvalue] == data2:
                    self.slots[hashvalue] = None
                    self.data1[hashvalue] = None
                    self.data2[hashvalue] = None
                    run = False    
                    return True
                else:
                    counter += 1
                    hashvalue = self.remainder(hashvalue, counter)

    #returns the first name
    def getEntryData1(self, key, data2):
        for x in range(len(self.data1)):
            if key == self.slots[x] and data2 == self.data2[x]:
                return self.data1[x]

    #returns the name at index i
    def getData1(self, i):
        return self.data1[i]

    #returns the matriculation number at index i
    def getData2(self, i):
        return self.data2[i] 

    #returns the key at index i
    def getSlots(self, i):
        return self.slots[i]
              

#ends the program                
def endProgram():
    print("Das Programm wird beendet.")
    sys.exit()

#prints the hashtable
def printTable(hashTable, len):
    counter = 0
    for x in range(len):
        if hashTable.getSlots(x) is None:
            counter += 1
    print("Hashtable: ", len, " Slots")
    print("Freie Plätze:", counter)
    print()
    for x in range(len):
        if hashTable.getSlots(x) is not None:
            print("Key(Nachname): ",hashTable.getSlots(x))
            print("Data1(Vorname): ",hashTable.getData1(x))
            print("Data2(Matrikelnummer): ", hashTable.getData2(x))
            print()

#reads a dokument and store there information in the hashtable
name = []
firstname = []
number = []
file = "studentdata2.csv"
try:
    with open(file) as csv_file:
        csv_reader = csv.DictReader(csv_file)
        for columns in csv_reader:
            name.append(columns["Name"])
            firstname.append(columns["Vorname"])
            number.append(columns["Matrikelnummer"])
except(IOError):
    print("File not found")
myHashTableLength = len(name)+1
myHashTable = HashTable(myHashTableLength)
for i in range(len(name)):
    key_string = name[i]
    data_list1 = firstname[i]
    data_list2 = number[i]
    myHashTable.put(key_string, data_list1, data_list2)


#menuinterface to use the hashtable
while True:
    print("Menü")
    print("[A] Hashtable anzeigen")
    print("[L] Eintrag löschen")
    print("[H] Eintrag hinzufügen")
    print("[N] Nachname aendern")
    print("[E] Speichern und verlassen")
    print()
    menue_option = str(input()).upper()
    print()

 
    if(menue_option == "A"):
        printTable(myHashTable, myHashTableLength)
        print()

    
    elif(menue_option == "L"):
        printTable(myHashTable, myHashTableLength)
        key_string = input("Bitte geben sie die Namen der Person, die sie löschen wollen, ein: ")
        data_string = input("Bitte geben sie die Matrikelnummer der Person, die sie löschen wollen, ein: ")
        if myHashTable.deleteEntry(key_string, data_string):
           print("Der Student wurde aus dem Verzeichnis gelöscht.")
           print()
        else:
            print("Eintrag nicht gefunden")
            print()

    
    elif(menue_option == "H"):
        if myHashTable.is_full():
            print("Hashtable ist voll! Es können keine weiteren Studenten hinzugefügt werden.")
            print()
        else:
            key_string = str(input("Bitte geben sie einen Nachnamen ein: "))
            data_list = str(input("Bitte geben sie einen Vornamen ein: "))
            data_list2 = str(input("Bitte geben sie eine Matrikelnummer ein: "))
            if myHashTable.checkMNumber(data_list2):
                print("Matrikelnummer bereits vergeben")
            else:
                myHashTable.put(key_string, data_list, data_list2)        
        print()

    elif(menue_option == "N"):
        key_string = str(input("Bitte geben sie den Nachnamen der Person, dessen Nachnamen sie ändern wollen, ein: "))
        data_string = str(input("Bitte geben sie die Matrikelnummer der Person, dessen Nachnamen sie ändern wollen, ein: "))
        data = 0
        if myHashTable.entryCheck(key_string, data_string):
            data = myHashTable.getEntryData1(key_string, data_string) 
        if myHashTable.deleteEntry(key_string, data_string):
            key_string2 = str(input("Bitte geben sie den neuen Nachnamen ein: "))
            myHashTable.put(key_string2, data, data_string)
            print()
        else:
            print("Eintrag nicht gefunden")
            print()   

    elif (menue_option == "E"):
        file = "studentdata5.csv"
        try:
            with open(file, mode = "w") as csv_file:
                fieldnames = ["Name", "Vorname","Matrikelnummer"]
                csv_writer = csv.DictWriter(csv_file, fieldnames = fieldnames)
                csv_writer.writeheader()
                for x in range(myHashTableLength):
                    nameString = myHashTable.getSlots(x)
                    dataString = myHashTable.getData1(x)
                    dataString2 = myHashTable.getData2(x)
                    csv_writer.writerow({"Name": nameString, "Vorname": dataString, "Matrikelnummer": dataString2})
                endProgram()
        except(IOError):
            print("File not found")
            endProgram()  

    else:
        print("Ungültige Eingabe")
        print()

