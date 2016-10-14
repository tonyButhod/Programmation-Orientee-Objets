# Ensimag 2A POO - TP 2016/17
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est ici d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     L'archive bin/gui.jar contient les classes de l'interface graphique
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: testInvader testLecture 

TestInvader: src/TestInvader.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestInvader.java

TestLecture: src/TestLecteurDonnees.java
	javac -d bin -sourcepath src src/TestLecteurDonnees.java


TestCase: src/TestCase.java
	javac -d bin -sourcepath src src/TestCase.java

TestCarte: io objects src/TestCarte.java
	javac -d bin -sourcepath src src/TestCarte.java

io: src/io/LecteurDonnees.java
	javac -d bin -sourcepath src $<

objects: src/objects/NatureTerrain.java src/objects/Direction.java src/objects/Case.java src/objects/Carte.java src/objects/DonneesSimulation.java
	javac -d bin -sourcepath src $^

TestIncendie: objects src/TestIncendie.java
	javac -d bin -sourcepath src src/TestIncendie.java

TestDonnees: objects src/TestDonnees.java
	javac -d bin -sourcepath src src/TestDonnees.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin:bin/gui.jar TestInvader
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeInvader
exeInvader: testInvader testLecture
	java -classpath bin:bin/gui.jar TestInvader

exeLecture: 
	java -classpath bin TestLecteurDonnees cartes/carteSujet.map

exeTestCase: 
	java -classpath bin TestCase

exeTestCarte: TestCarte
	java -classpath bin TestCarte

exeTestIncendie:
	java -classpath bin TestIncendie

exeTestDonnees:
	java -classpath bin TestDonnees

clean:
	rm -rf bin/*.class bin/io/*.class bin/objects/*.class  src/*~ src/objects/*~
