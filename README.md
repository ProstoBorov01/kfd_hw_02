# kfd_hw_02

#Hierarchy_of_classes

Все классы наследуются от абстрактного класса Transport (в нём хранится метод, который присутсвует у всех классов потомков). 
В данной иерархии присутсвует два "суперкласса" - Car, Moto. Их потомки - ElectroCar и ElectroMoto соответсвенно. 

Класс Car наследутся от interface Auto (он высупает в качестве шаблона) и абстрактного класса Transport.
Класс Moto наследутся от interface Motocycle (он высупает в качестве шаблона) и абстрактного класса Transport.

В данной иерархии присутсвет singleton класс, который выступает в роли счётчика. 


Схема - https://drive.google.com/file/d/1PHfLwBQW1j3Rjli8gz2efq_IFKU_vYPc/view?usp=sharing
